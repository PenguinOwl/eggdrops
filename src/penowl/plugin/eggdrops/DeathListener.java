package penowl.plugin.eggdrops;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DeathListener implements Listener {

	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public DeathListener(Plugin plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void deathListener(EntityDeathEvent event) {
		
		if (!Main.elgi.containsKey(event.getEntity().getEntityId())) {
			
			int val = plugin.getConfig().getInt(event.getEntityType().toString()+".chance");

			if(val == 0) {
				val = 200;
			}
			
			double chance = val;
			
			String mob = event.getEntity().getType().toString();
			
			if(plugin.getConfig().getString(event.getEntityType().toString()+".eggtype") != "null") {
				mob = plugin.getConfig().getString(event.getEntityType().toString()+".eggtype");
			}
			
			if (Math.random()*chance<1) {
				
				ItemStack egg = new ItemStack(Material.valueOf(mob+"_SPAWN_EGG"));
				
				event.getDrops().add(egg);
			}
			
			Main.elgi.remove(event.getEntity().getEntityId());
			
		}
		
	}
	
}
