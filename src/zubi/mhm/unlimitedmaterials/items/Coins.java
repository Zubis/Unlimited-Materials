package zubi.mhm.unlimitedmaterials.items;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class Coins extends GenericCustomItem {

	public Coins(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}

	public Coins(Plugin plugin) {
		super(plugin, "Piece en Bronze",
				"http://img11.imageshack.us/img11/9292/itemcoinbronze.png");
	}

}
