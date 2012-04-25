package zubi.mhm.unlimitedmaterials;

import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.SQLite;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.CustomBlock;
import zubi.mhm.unlimitedmaterials.manager.*;
import zubi.mhm.unlimitedmaterials.utils.Debug;

public class UnlimitedMaterials extends JavaPlugin{
	private final BlockListener blockListener = new BlockListener(this);
	public static UnlimitedMaterials instance;
	
	public final Logger log = Logger.getLogger("Minecraft");

	FileConfiguration config = null;

	PluginDescriptionFile plugdisc;
	public String logPrefix = "[Unlimited-Materials] "; // Prefix to go in front of all log entries
	
	public static CustomBlock Wool_yellowSlab;
	public static Texture Wool_yellowTexture;
	public SQLite sqlite;
	private UnlimitedMaterialsCommandExecutor myExecutor;
	
	
	@Override
	public void onDisable() {
		log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " disabled.");		
	}

	@Override
	public void onEnable() {
		plugdisc = this.getDescription();
		
		instance = this;
		this.log.info(this.logPrefix + "SQLite Initializing");

		// Declare SQLite handler
		this.sqlite = new SQLite(this.log, this.logPrefix, "blocks", "plugins/" + plugdisc.getName());
		this.sqlite.open();

		// Check if the table exists, if it doesn't create it
		if (!this.sqlite.checkTable("blocks")) {
			this.log.info(this.logPrefix + "Creating table blocks");
			String query = "CREATE TABLE IF NOT EXISTS positions (id integer NOT NULL PRIMARY KEY AUTOINCREMENT, player VARCHAR(40), world VARCHAR(20), X INT, Y INT, Z INT, block_name VARCHAR(30) , block_id INT, date DATETIME);";
			this.sqlite.createTable(query); // Use SQLite.createTable(query) to create tables 
		}
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(blockListener, this);
		
		config = this.getConfig();
		loadConfig();

		if(config.getBoolean("config.debug")){
			Debug.debug("Debug mode enabled");
		}
		
		ConfigurationSection blocksSection = config.getConfigurationSection("blocks");
		ConfigurationSection recipesSection = config.getConfigurationSection("recipes");
		ConfigurationSection itemsSection = config.getConfigurationSection("items");
				
		new BlockManager(this, blocksSection);		
		new ItemManager(this, itemsSection);
		new RecipeManager(this, recipesSection);
		
		myExecutor = new UnlimitedMaterialsCommandExecutor(this);
		getCommand("um").setExecutor(myExecutor);
		
		log.info("[" + plugdisc.getName() + "] Version " + plugdisc.getVersion() + " enabled.");	
	}
	
	public void loadConfig()
	{
		config.addDefault("config.debug", "true");
		
		config.addDefault("blocks.1.name", "Pierre Blanche");
		config.addDefault("blocks.1.faces", 1);
		config.addDefault("blocks.1.url", "http://dl.dropbox.com/u/68635183/UnlimitedMaterials/pierreblanche.png");
		config.addDefault("blocks.1.size",32);
		
		config.addDefault("blocks.2.name", "Caisse en bois");
		config.addDefault("blocks.2.faces", 1);
		config.addDefault("blocks.2.url", "http://dl.dropbox.com/u/68635183/UnlimitedMaterials/bloccaisse.png");
		config.addDefault("blocks.2.size",32);
		
		config.addDefault("recipes.1.desc", "recette pierre Blanche");
		config.addDefault("recipes.1.item.type", "Custom");
		config.addDefault("recipes.1.item.id", 1);
		config.addDefault("recipes.1.item.quantity", 1);
		config.addDefault("recipes.1.A.type", "Original");
		config.addDefault("recipes.1.A.id", 1);
		config.addDefault("recipes.1.B.type", "Original");
		config.addDefault("recipes.1.B.id", 351);
		config.addDefault("recipes.1.layout", "AB");
		
		
		config.options().copyDefaults(true);
		saveConfig();
	}
}
