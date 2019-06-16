package penowl.plugin.eggdrops;

import java.util.HashMap;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	DeathListener deathListener = new DeathListener(this);
	SpawnListener spawnListener = new SpawnListener(this);
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(deathListener, this);
		pm.registerEvents(spawnListener, this);
		getLogger().info("EggDrops has been enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("EggDrops has been disabled!");
	}
	
	public static HashMap<Integer,Boolean> elgi = new HashMap<Integer,Boolean>();
	
}