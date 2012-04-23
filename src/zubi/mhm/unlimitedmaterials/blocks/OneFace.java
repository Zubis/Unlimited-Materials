package zubi.mhm.unlimitedmaterials.blocks;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;

public class OneFace extends GenericCuboidCustomBlock
{
    public OneFace(Plugin plugin, Texture texture, String blockName, Integer blockParentId)
    {
        super(plugin, blockName, blockParentId, new GenericCuboidBlockDesign(plugin, texture, new int[] {0,0,0,0,0,0}, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setBlockDesign(getBlockDesign().setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        //setStepSound(MaterialData.leaves.getStepSound());
        setHardness(MaterialData.getBlock(blockParentId).getHardness());
        setLightLevel(MaterialData.getBlock(blockParentId).getLightLevel());
        //setFriction(MaterialData.netherrack.getFriction());
    }
}