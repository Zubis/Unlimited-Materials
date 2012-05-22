package zubi.mhm.unlimitedmaterials;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.MaterialData;

import zubi.mhm.unlimitedmaterials.utils.Debug;

public class UnlimitedMaterialsCommandExecutor implements CommandExecutor {
	 
	private UnlimitedMaterials plugin;
 
	public UnlimitedMaterialsCommandExecutor(UnlimitedMaterials plugin) {
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
			}else{
				player.sendMessage("try : /um rebuild [size]");
				return false;				
			}
		}
		return false; 
	}
}
