package penowl.plugin.eggdrops;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;

public class SpawnListener implements Listener {

	@SuppressWarnings("unused")
	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public SpawnListener(Plugin plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void spawnlistener(CreatureSpawnEvent event) {
		
		if(event.getSpawnReason()==SpawnReason.SPAWNER) {
			Main.elgi.putIfAbsent(event.getEntity().getEntityId(), true);
		}
		
	}
	
}
