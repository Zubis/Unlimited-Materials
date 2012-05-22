package zubi.mhm.unlimitedmaterials.blocks;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;

public class Tapestry extends GenericCuboidCustomBlock
{
	
    public Tapestry(Plugin plugin, Texture texture, String blockName, int[] faces)
    {
    	 super(plugin, blockName, 106, new GenericCuboidBlockDesign(plugin, texture, faces, 0.0F, 0.0F, 0.0F, 0.1F, 1.0F, 1.0F));
    	 this.setBlockDesign(getBlockDesign().setBoundingBox(0.0F, 0.0F, 0.0F, 0.1F, 1.0F, 1.0F));
         this.setStepSound(MaterialData.whiteWool.getStepSound());
         this.setHardness(MaterialData.whiteWool.getHardness());
         this.setFriction(MaterialData.whiteWool.getFriction());
         this.setLightLevel(MaterialData.whiteWool.getLightLevel());
    }
}