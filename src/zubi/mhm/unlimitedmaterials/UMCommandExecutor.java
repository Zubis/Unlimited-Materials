package zubi.mhm.unlimitedmaterials;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;

import zubi.mhm.unlimitedmaterials.manager.ItemManager;
import zubi.mhm.unlimitedmaterials.utils.Debug;

public class UMCommandExecutor implements CommandExecutor {
	 
	private UnlimitedMaterials plugin;
 
	public UMCommandExecutor(UnlimitedMaterials plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if(command.getName().equalsIgnoreCase("um")){
			if(args[0].equalsIgnoreCase("rebuild")){ 
				String query = "SELECT * FROM positions";
				ResultSet result = this.plugin.sqlite.query(query);
				try {
					while(result.next()){
						World world = Bukkit.getWorld(result.getString("world"));
						Double x = result.getDouble("x");
						Double y = result.getDouble("y");
						Double z = result.getDouble("z");
						
						Location loc = new Location(world, x, y, z);
						SpoutBlock b = (SpoutBlock) world.getBlockAt(loc);
						
						b.setCustomBlock( MaterialData.getCustomBlock(result.getInt("block_id")));
						Debug.debug("rebuild type : " + b.getTypeId());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return true;
			}else if(args[0].equalsIgnoreCase("give")){
				if (args.length > 4) {
					sender.sendMessage(ChatColor.RED + "Too many arguments!");
					sender.sendMessage(ChatColor.RED + "/um give <player> <item> <quantity>");
					return false;
				} else if (args.length < 4) {
					sender.sendMessage(ChatColor.RED + "Not enough arguments!");
					sender.sendMessage(ChatColor.RED + "/um give <player> <item> <quantity>");
					return false;

				} else {
					
					CustomItem item = null; 
					
					//plugin.log.info("[UM]" + args[2] +" "+ item.getName() + " to " + target.getName() + " ");
					if(args[1].toLowerCase().startsWith("bro")){
						item = ItemManager.itemArray.get(0);
					}else if(args[1].toLowerCase().startsWith("arg")){
						item = ItemManager.itemArray.get(1);
					}else if(args[1].toLowerCase().startsWith("or")){
						item = ItemManager.itemArray.get(2);
					}else if(args[1].toLowerCase().startsWith("dia")){
						item = ItemManager.itemArray.get(3);
					}else{
						sender.sendMessage(ChatColor.RED + "invalid item " + args[1].toLowerCase());
						return false;
					}
					
					Player target = player.getServer().getPlayer(args[0]);
					if(target != null){
						ItemStack items = new SpoutItemStack(item, new Integer(args[2]));
						target.getInventory().addItem(items);
						
						sender.sendMessage(ChatColor.DARK_GREEN + "Vous venez de recevoir " + args[2] + " " + item.getName());
						Debug.debug(String.format("[%s v%s] Give %s %s to %s", plugin.getDescription().getName(), plugin.getDescription().getVersion(), args[2], item.getName(), target.getName()));

					}else{
						sender.sendMessage(ChatColor.RED + "invalid player name " + args[0].toLowerCase());
						return false;
					}
					
					
					return true;
				}	
			}else{
				player.sendMessage("invalid command /um");
				return false;				
			}
		}
		return false; 
	}
}
