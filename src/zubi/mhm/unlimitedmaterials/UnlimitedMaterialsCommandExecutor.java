package zubi.mhm.unlimitedmaterials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnlimitedMaterialsCommandExecutor implements CommandExecutor {
	 
	private UnlimitedMaterials plugin;
 
	public UnlimitedMaterialsCommandExecutor(UnlimitedMaterials plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("um")){ 
			plugin.loadConfig();
			return true;
		} 
		return false; 
	}
}
