package zubi.mhm.unlimitedmaterials;

import lib.PatPeter.SQLibrary.SQLite;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.block.SpoutBlock;
import zubi.mhm.unlimitedmaterials.utils.Debug;

public class BlockListener implements Listener{
	
	public UnlimitedMaterials plugin;
	SQLite bddLite;
	
	public BlockListener(UnlimitedMaterials instance) {
	plugin = instance;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{	
		SpoutBlock BlockPlaced = (SpoutBlock) event.getBlockPlaced();
		Player player = event.getPlayer();
		
		if(BlockPlaced.isCustomBlock()){
			String query = "INSERT INTO positions (player, world, X, Y, Z, block_name, block_id, date) VALUES (\'"+player.getName()+"\', \'"+ BlockPlaced.getWorld().getName() +"\' ,\'"+ BlockPlaced.getX()+"\', \'"+ BlockPlaced.getY()+"\', \'"+ BlockPlaced.getZ()+"\', \'"+ BlockPlaced.getCustomBlock().getName() +"\', \'"+ BlockPlaced.getCustomBlock().getBlockId() +"\', date('now'))";
			this.plugin.sqlite.query(query);
			Debug.debug("Insert value into BDD : " + player.getName() + " " + BlockPlaced.getWorld().getName()+ " "+ BlockPlaced.getCustomBlock().getName() + " " + BlockPlaced.getX() + ":" + BlockPlaced.getY() + ":" + BlockPlaced.getZ());
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{	
		SpoutBlock BlockBreaked = (SpoutBlock) event.getBlock();
		
		if(BlockBreaked.isCustomBlock()){
			String query = "DELETE FROM positions WHERE world = \'"+ BlockBreaked.getWorld().getName() +"\' AND X = " +BlockBreaked.getX()+ " AND Y = "+ BlockBreaked.getY() +" AND Z = "+ BlockBreaked.getZ();
			this.plugin.sqlite.query(query);
			Debug.debug("Delete value into BDD : " + event.getPlayer().getName() + " " + BlockBreaked.getWorld().getName()+ " "+ BlockBreaked.getCustomBlock().getName() + " " + BlockBreaked.getX() + ":" + BlockBreaked.getY() + ":" + BlockBreaked.getZ());
		}
	}
}
