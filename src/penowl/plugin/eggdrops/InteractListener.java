package penowl.plugin.eggdrops;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class InteractListener implements Listener {

	private Plugin plugin;

	public InteractListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void someoneBlocked(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
			if (event.getClickedBlock().getType() == Material.SPAWNER) {
				if (event.getClickedBlock() != null) {
					if (event.getItem() == null) {
						event.setCancelled(true);
						CreatureSpawner spawner = ((CreatureSpawner) event.getClickedBlock().getState());
						event.getPlayer().openInventory(InventoryBuilder.spawnerInventory(spawner));
					} else if (!event.getItem().getType().toString().contains("SPAWN_EGG")) {
						event.setCancelled(true);
						CreatureSpawner spawner = ((CreatureSpawner) event.getClickedBlock().getState());
						event.getPlayer().openInventory(InventoryBuilder.spawnerInventory(spawner));
					}
				}
			}
		}
	}
}
