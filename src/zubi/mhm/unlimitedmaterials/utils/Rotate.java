package zubi.mhm.unlimitedmaterials.utils;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.block.SpoutBlock;

public class Rotate {

	public Rotate(Player player,  SpoutBlock block) {
		 Float value = player.getEyeLocation().getYaw();
	
		 if((value >= 0 && value < 45) || (value >= 315 && value < 360)){
			 Debug.debug("sud");
		 } else if(value >= 45 && value < 135){
			
			 block.getLocation().setYaw(135);
				Debug.debug("ouest");
		 } else if(value >= 135 && value < 225){
			 block.getLocation().setYaw(135);
				Debug.debug("nord");
		 } else if(value >= 225 && value < 315){
			 block.getLocation().setYaw(135);
				Debug.debug("est");
		 }
	}
	
}
