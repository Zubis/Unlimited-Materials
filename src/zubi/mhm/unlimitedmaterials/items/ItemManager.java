package zubi.mhm.unlimitedmaterials.items;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.CustomItem;

import zubi.mhm.unlimitedmaterials.UnlimitedMaterials;
import zubi.mhm.unlimitedmaterials.utils.Debug;

public class ItemManager {
	public ArrayList<CustomItem> itemArray = new ArrayList<CustomItem>(); 
	public static CustomItem item;
	public static Texture itemTexture;
	
	public ItemManager(UnlimitedMaterials plugin, ConfigurationSection itemsSection) {
		
		int i = 1;
		
		while (itemsSection.contains(String.valueOf(i))){
			String itemName = itemsSection.getString(String.valueOf(i)+".name");
			String itemUrl = itemsSection.getString(String.valueOf(i)+".url", "http://dl.dropbox.com/u/68635183/UnlimitedMaterials/bloc.png");
			item = new Coins(plugin, itemName, itemUrl);
			Debug.debug("New item : " + itemName);
			
			itemArray.add(item);
			i++;
		}
	}
}
