package zubi.mhm.unlimitedmaterials.blocks;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;

public class Cubes extends GenericCuboidCustomBlock
{
    public Cubes(Plugin plugin, Texture texture, String blockName, Integer blockParentId, int[] faces)
    {
        super(plugin, blockName, blockParentId, new GenericCuboidBlockDesign(plugin, texture, faces, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setBlockDesign(getBlockDesign().setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setHardness(MaterialData.getBlock(blockParentId).getHardness());
        setLightLevel(MaterialData.getBlock(blockParentId).getLightLevel());
    }
}