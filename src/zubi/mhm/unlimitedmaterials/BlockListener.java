package zubi.mhm.unlimitedmaterials;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.CustomBlock;

public class BlockListener implements Listener{
	
	public UnlimitedMaterials plugin;
	public BlockListener(UnlimitedMaterials instance) {
	plugin = instance;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{	
		//SpoutBlock BlockPlaced = (SpoutBlock) event.getBlockPlaced()
		//if(B)
		
		/*SpoutBlock BlockAgainst = (SpoutBlock) event.getBlockAgainst();
		SpoutBlock BlockPlaced = (SpoutBlock) event.getBlockPlaced();

		if(BlockAgainst.isCustomBlock() && 
		   BlockPlaced.isCustomBlock() &&
		   BlockAgainst.getX() == BlockPlaced.getX() &&
		   BlockAgainst.getZ() == BlockPlaced.getZ())
		{
			CustomBlock CustomBlockAgainst = BlockAgainst.getCustomBlock();
			CustomBlock CustomBlockPlaced = BlockPlaced.getCustomBlock(); 
				
			if(CustomBlockAgainst.getClass() == CustomBlockPlaced.getClass())
			{
				if(CustomBlockAgainst.getClass() == zubi.mhm.unlimitedmaterials.slabs.Wool_yellow.class)
				{
					BlockBreakEvent blockbreakevent = new BlockBreakEvent(BlockAgainst, event.getPlayer());
					plugin.getServer().getPluginManager().callEvent(blockbreakevent);
					BlockAgainst.setType(Material.WOOL);
					BlockAgainst.setData((byte) 0);
					event.setCancelled(true);
				}
			}
		}*/
	}
}
