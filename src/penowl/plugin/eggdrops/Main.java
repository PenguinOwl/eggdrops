package penowl.plugin.eggdrops;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	DeathListener deathListener = new DeathListener(this);
	SpawnListener spawnListener = new SpawnListener(this);
	ClickListener clickListener = new ClickListener(this);
	InteractListener interactListener = new InteractListener(this);
	File rawData = new File(getDataFolder(), "data.yml");
	public YamlConfiguration dataFile = YamlConfiguration.loadConfiguration(rawData);
	
	public void saveData() {
		try {
			dataFile.save(rawData);
			dataFile = YamlConfiguration.loadConfiguration(rawData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(deathListener, this);
		pm.registerEvents(clickListener, this);
		pm.registerEvents(spawnListener, this);
		pm.registerEvents(interactListener, this);
		getLogger().info("EggDrops has been enabled!");
		saveData();
	}

	@Override
	public void onDisable() {
		getLogger().info("EggDrops has been disabled!");
	}
	
	public static HashMap<Integer,Boolean> elgi = new HashMap<Integer,Boolean>();
	
}