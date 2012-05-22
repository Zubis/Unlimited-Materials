package zubi.mhm.unlimitedmaterials.manager;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.CustomBlock;
import zubi.mhm.unlimitedmaterials.UnlimitedMaterials;
import zubi.mhm.unlimitedmaterials.blocks.Cubes;
import zubi.mhm.unlimitedmaterials.blocks.Slabs;
import zubi.mhm.unlimitedmaterials.utils.Debug;

public class BlockManager {

	public static ArrayList<CustomBlock> BlockArray = new ArrayList<CustomBlock>(); 
	public CustomBlock block;
	public Texture blockTexture;
	
	public BlockManager(UnlimitedMaterials plugin, ConfigurationSection blocksSection) {
		
		int i = 1;
		
		while (blocksSection.contains(String.valueOf(i))){
			//System.out.println("[BlockManager] name : " + blocksSection.getString(String.valueOf(i)+".name"));
			//System.out.println("[BlockManager] type : " + blocksSection.getString(String.valueOf(i)+".type"));
			//System.out.println("[BlockManager] size : " + blocksSection.getString(String.valueOf(i)+".size"));
			//System.out.println("[BlockManager] url : " + blocksSection.getString(String.valueOf(i)+".url"));
			
			String blockType = blocksSection.getString(String.valueOf(i)+".type", "block");
			String blockName = blocksSection.getString(String.valueOf(i)+".name");
			Integer blockFaces = blocksSection.getInt(String.valueOf(i)+".faces");
			Integer blockParentId = blocksSection.getInt(String.valueOf(i)+".parent");
			Integer blockSize = blocksSection.getInt(String.valueOf(i)+".size");
			String blockUrl = blocksSection.getString(String.valueOf(i)+".url", "http://dl.dropbox.com/u/68635183/UnlimitedMaterials/bloc.png");
			
			
			int temp;
			
			if(blockFaces == 3){
				temp = 4;
			}else{
				temp = blockFaces;
			}
			
			blockTexture = new Texture(plugin, blockUrl, blockSize * temp, blockSize, blockSize);
			
			int[] faces;
			
			switch(blockFaces){
				case 1: faces = new int[] {0,0,0,0,0,0};
					break;
				case 2: faces = new int[] {0,1,1,1,1,0};
					break;
				case 3: faces = new int[] {2,1,1,1,1,0};
					break;
				case 4: faces = new int[] {2,1,3,3,3,0};
					break;
				default: faces = new int[] {0,0,0,0,0,0};
			}
			
			if(blockType.equalsIgnoreCase("slab")){
				block = new Slabs(plugin, blockTexture, blockName, blockParentId, faces);
			}else if(blockType.equalsIgnoreCase("block")){
				block = new Cubes(plugin, blockTexture, blockName, blockParentId, faces);
			}else{
				block = new Cubes(plugin, blockTexture, blockName, blockParentId, faces);
			}
			

			Debug.debug("New block : " + blockName + ", parent("+blockParentId+"), faces("+ blockType + ")");
			BlockArray.add(block);
			i++;
		}
		

	}
}
