package zubi.mhm.unlimitedmaterials;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

public class RecipeManager {
	
	private SpoutItemStack itemStack;
	private SpoutShapedRecipe blockRecipe;

	public RecipeManager(UnlimitedMaterials plugin, ConfigurationSection recipeSection) {
		
		CustomBlock[] blocks = MaterialData.getCustomBlocks();
		
		int i = 1;
		while (recipeSection.contains(String.valueOf(i))){
			
			
			String AType = recipeSection.getString(String.valueOf(i)+".A.type");
			int AId = recipeSection.getInt(String.valueOf(i)+".A.id");
			Short ASubId = Short.valueOf(recipeSection.getString(String.valueOf(i)+".A.subId", "0"));
			
			String BType = recipeSection.getString(String.valueOf(i)+".B.type");
			int BId = recipeSection.getInt(String.valueOf(i)+".B.id");
			Short BSubId = Short.valueOf(recipeSection.getString(String.valueOf(i)+".B.subId", "0"));
			
			String CType = recipeSection.getString(String.valueOf(i)+".C.type");
			int CId = recipeSection.getInt(String.valueOf(i)+".C.id");
			Short CSubId = Short.valueOf(recipeSection.getString(String.valueOf(i)+".C.subId", "0"));
			
			String DType = recipeSection.getString(String.valueOf(i)+".D.type");
			int DId = recipeSection.getInt(String.valueOf(i)+".D.id");
			Short DSubId = Short.valueOf(recipeSection.getString(String.valueOf(i)+".D.subId", "0"));
			
			String blockType = recipeSection.getString(String.valueOf(i)+ ".item.type").toLowerCase();
			int blockId = recipeSection.getInt(String.valueOf(i)+ ".item.id");
			Short blockSubId = Short.valueOf(recipeSection.getString(String.valueOf(i)+".block.subId", "0"));
			int blockQuantity = recipeSection.getInt(String.valueOf(i)+ ".item.quantity");
			
			List<String> recipeLayout = recipeSection.getStringList(String.valueOf(i)+ ".layout");
			String[] layoutString = (String[])recipeLayout.toArray(new String[recipeLayout.size()]);

			
			/**
			 *  Declaration du bloc a creer et sa quantite
			 */
			
			if(blockType != null){
				if( blockType.equalsIgnoreCase("Custom")){
					//System.out.println("[UnlimitedMaterials] item type : " + blockType);
					//System.out.println("[UnlimitedMaterials] item : " + blocks[blockId-1]);
					itemStack = new SpoutItemStack( blocks[blockId-1], blockQuantity);
				}else if(blockType.equalsIgnoreCase("Original")){
					//System.out.println("[UnlimitedMaterials] item type : " + blockType);
					//System.out.println("[UnlimitedMaterials] item : " + MaterialData.getItem(blockId));
					itemStack = new SpoutItemStack( MaterialData.getMaterial(blockId, blockSubId), blockQuantity);
				}else{
					//System.out.println("[UnlimitedMaterials] Unknown item type : " + blockType);
					itemStack = new SpoutItemStack( MaterialData.getMaterial(1), blockQuantity);
				}
			}
			
			if(itemStack != null){
				blockRecipe = new SpoutShapedRecipe(itemStack);
			}
			if(layoutString != null){
				blockRecipe.shape( layoutString);
			}
			
			/**
			 * Declaration de l'item A utilise pour le craft
			 */
			if(AType != null){
				if( AType.equalsIgnoreCase("custom")){
					blockRecipe.setIngredient('A', blocks[AId-1]);
					System.out.println("Custom A :"+blocks[AId-1]);
				}else if(AType.equalsIgnoreCase("original")){
					blockRecipe.setIngredient('A', MaterialData.getMaterial(AId, ASubId));
					System.out.println("Original A :"+ MaterialData.getMaterial(AId, ASubId).getName());
				}
			}
			/**
			 * Declaration de l'item B utilise pour le craft
			 */
			
			if(BType != null){
				if( BType.equalsIgnoreCase("custom")){
					blockRecipe.setIngredient('B', blocks[BId-1]);
					System.out.println("Custom B :"+blocks[BId-1]);
					
				}else if(BType.equalsIgnoreCase("original")){
					blockRecipe.setIngredient('B', MaterialData.getMaterial(BId, BSubId));
					System.out.println("Original B :"+MaterialData.getMaterial(BId, BSubId ).getName());
				}
			}
			
			/**
			 * Declaration de l'item C utilise pour le craft
			 */
			if(CType != null){
				if( CType.equalsIgnoreCase("custom")){
					blockRecipe.setIngredient('C', blocks[CId-1]);
				}else if(CType.equalsIgnoreCase("original")){
					blockRecipe.setIngredient('C', MaterialData.getMaterial(CId, CSubId));
					System.out.println("Original C :"+MaterialData.getMaterial(CId, CSubId ).getName());
				}
			}
			
			/**
			 * Declaration de l'item D utilise pour le craft
			 */
			if(DType != null){
				if( DType.equalsIgnoreCase("custom")){
					blockRecipe.setIngredient('D', blocks[DId-1]);
				}else if(DType.equalsIgnoreCase("original")){
					blockRecipe.setIngredient('D', MaterialData.getMaterial(DId, DSubId));
					System.out.println("Original D :"+MaterialData.getMaterial(DId, DSubId ).getName());
				}
			}
			
			SpoutManager.getMaterialManager().registerSpoutRecipe(blockRecipe);
			System.out.println("[UM RecipeManager] Adding the craft : " + recipeSection.getString(String.valueOf(i)+".desc"));
			
			i++;
		}
	}
}