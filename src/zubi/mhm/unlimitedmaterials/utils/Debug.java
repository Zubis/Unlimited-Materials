package zubi.mhm.unlimitedmaterials.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import zubi.mhm.unlimitedmaterials.UnlimitedMaterials;

public class Debug {

	public static final String tag = UnlimitedMaterials.instance.logPrefix;

	public static void debug(String debugText) {
		if (UnlimitedMaterials.instance.getConfig().getBoolean("config.debug")) {
			//Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED +tag + debugText);
			Bukkit.getServer().broadcastMessage(ChatColor.RED +tag + debugText);
		}
	}

	public static void debug(Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(allText);
	}

	public static void debug(Player player, String debugText) {
		if (UnlimitedMaterials.instance.getConfig().getBoolean("config.debug")) {
			player.sendMessage(ChatColor.RED +tag+debugText);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED +tag + player.getName() + debugText);
		}
	}

	public static void debug(Player player, Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(player, allText);
	}

}
