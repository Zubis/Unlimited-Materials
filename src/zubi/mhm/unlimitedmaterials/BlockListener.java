package zubi.mhm.unlimitedmaterials;

import lib.PatPeter.SQLibrary.SQLite;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.block.SpoutBlock;

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
		
		if(BlockPlaced.isCustomBlock()){
			String query = "INSERT INTO positions (player, X, Y, Z, block_name, block_id, date) VALUES (\'"+event.getPlayer().getName()+"\', \'"+ BlockPlaced.getX()+"\', \'"+ BlockPlaced.getY()+"\', \'"+ BlockPlaced.getZ()+"\', \'"+ BlockPlaced.getCustomBlock().getName() +"\', \'"+ BlockPlaced.getCustomBlockId() +"\', date('now'))";
			this.plugin.sqlite.query(query);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{	
		
		SpoutBlock BlockBreaked = (SpoutBlock) event.getBlock();
		
		if(BlockBreaked.isCustomBlock()){
			String query = "DELETE FROM positions WHERE X = " +BlockBreaked.getX()+ " AND Y = "+ BlockBreaked.getY() +" AND Z = "+ BlockBreaked.getZ();
			this.plugin.sqlite.query(query);
		}
		
		/*if(BlockBreaked.isCustomBlock()){
			System.out.println("DELETE FROM positions WHERE X = " +BlockBreaked.getX()+ " AND Y = "+ BlockBreaked.getY() +" AND Z = "+ BlockBreaked.getZ());
			
			//player VARCHAR(40), X INT, Y INT, Z INT, type VARCHAR(30)
			
			bddLite = new SQLite(plugin.log, "[UM]", "blocks", "plugins/" + plugin.plugdisc.getName());
			bddLite.getConnection();
			//ResultSet resultat = null;
			ResultSet rs = plugin.bddLite.query("DELETE FROM positions WHERE X = " +BlockBreaked.getX()+ " AND Y = "+ BlockBreaked.getY() +" AND Z = "+ BlockBreaked.getZ());
		    try
		    {
		        rs.close();
		    }
		    catch (SQLException e)
		    {
		        plugin.log.info(e.getMessage());
		    }
		
		}*/
	}
}
