package zubi.mhm.unlimitedmaterials.blocks;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.CustomBlock;
import zubi.mhm.unlimitedmaterials.UnlimitedMaterials;

public class BlockManager {

	public ArrayList<CustomBlock> BlockArray = new ArrayList<CustomBlock>(); 
	public static CustomBlock block;
	public static Texture blockTexture;

	public BlockManager(UnlimitedMaterials plugin, ConfigurationSection blocksSection) {
		
		int i = 1;
		
		while (blocksSection.contains(String.valueOf(i))){
			//System.out.println("[BlockManager] name : " + blocksSection.getString(String.valueOf(i)+".name"));
			//System.out.println("[BlockManager] type : " + blocksSection.getString(String.valueOf(i)+".type"));
			//System.out.println("[BlockManager] size : " + blocksSection.getString(String.valueOf(i)+".size"));
			//System.out.println("[BlockManager] url : " + blocksSection.getString(String.valueOf(i)+".url"));
			
			String blockName = blocksSection.getString(String.valueOf(i)+".name");
			Integer blockType = blocksSection.getInt(String.valueOf(i)+".faces");
			Integer blockParentId = blocksSection.getInt(String.valueOf(i)+".parent");
			Integer blockSize = blocksSection.getInt(String.valueOf(i)+".size");
			String blockUrl = blocksSection.getString(String.valueOf(i)+".url", "http://dl.dropbox.com/u/68635183/UnlimitedMaterials/bloc.png");
			
			
			int temp;
			
			if(blockType == 3){
				temp = 4;
			}else{
				temp = blockType;
			}
			
			blockTexture = new Texture(plugin, blockUrl, blockSize * temp, blockSize, blockSize);
			
			if(blockType == 1){
				System.out.println("[UM BlockManager] Initialize a block in type 1 face ("+ blockName +")");
				block = new OneFace(plugin, blockTexture, blockName, blockParentId);				
			}else if(blockType == 2){
				System.out.println("[UM BlockManager] Initialize a block in type 2 faces ("+ blockName +")");
				block = new TwoFace(plugin, blockTexture, blockName, blockParentId);		
			}else if(blockType == 3){
				System.out.println("[UM BlockManager] Initialize a block in type 3 faces ("+ blockName +")");
				block = new ThreeFace(plugin, blockTexture, blockName, blockParentId);		
			}else if(blockType == 4){
				System.out.println("[UM BlockManager] Initialize a block in type 4 faces ("+ blockName +")");
				block = new FourFace(plugin, blockTexture, blockName, blockParentId);		
			}
			
			BlockArray.add(block);
			i++;
		}
		

	}
}
