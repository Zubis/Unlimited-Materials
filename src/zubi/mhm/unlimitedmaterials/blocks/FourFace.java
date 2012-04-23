package zubi.mhm.unlimitedmaterials.blocks;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public class FourFace extends GenericCuboidCustomBlock
{
    public FourFace(Plugin plugin, Texture texture, String blockName, Integer blockParentId)
    {
    	super(plugin, blockName, blockParentId, new GenericCuboidBlockDesign(plugin, texture, new int[] {2,1,3,3,3,0}, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setBlockDesign(getBlockDesign().setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        //setStepSound(MaterialData.leaves.getStepSound());
        ;
        setLightLevel(MaterialData.getBlock(blockParentId).getLightLevel());
        //setFriction(MaterialData.netherrack.getFriction());
		/*
		A = dessus
		B = devant
		C = dessous
		D = cotés et dos
		
		*
		*Et pour l'afficher dans la bonne direction regarde la fonction onBlockPlaceBy dans la class des escaliers. Et rager le render des escaliers dans la class RenderBlock.
		*
		*
		*/
    }
    
    public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player){
    	
    	
    	
    	return true;
    }
}