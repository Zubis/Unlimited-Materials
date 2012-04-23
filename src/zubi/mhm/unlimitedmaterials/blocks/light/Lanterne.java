package zubi.mhm.unlimitedmaterials.blocks.light;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;

public class Lanterne extends GenericCuboidCustomBlock
{
    public Lanterne(Plugin plugin, Texture texture, String name)
    {
        super(plugin, name, new GenericCuboidBlockDesign(plugin, texture, new int[] {0,1,2,3,4,5}, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setBlockDesign(getBlockDesign().setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F));
        setStepSound(MaterialData.stone.getStepSound());
    }
}