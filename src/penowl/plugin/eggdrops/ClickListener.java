package penowl.plugin.eggdrops;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("unused")
public class ClickListener implements Listener {

	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public ClickListener(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void someoneKnocked(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			if (event.getClickedInventory().getHolder() != null) {
				if (event.getClickedInventory().getHolder() instanceof FakeHolder && event.getCurrentItem() != null) {
					if (((FakeHolder)event.getClickedInventory().getHolder()).spawner != null) {
						boolean refresh = false;
						CreatureSpawner spawner = ((FakeHolder)event.getClickedInventory().getHolder()).spawner;
						if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
							event.setCancelled(true);
						}
						if (event.getCurrentItem().getType() == Material.SPAWNER) {
							event.setCancelled(true);
							ItemStack spawnerItem = event.getCurrentItem();
							ItemMeta meta = spawnerItem.getItemMeta();
							BlockStateMeta blockMeta = (BlockStateMeta) meta;
							BlockState blockstate = blockMeta.getBlockState();
							CreatureSpawner blockspawner = (CreatureSpawner) blockstate;
							blockspawner.setSpawnedType(spawner.getSpawnedType());
							blockMeta.setBlockState(blockstate);
							ArrayList<String> lore = (ArrayList<String>) meta.getLore();
							lore.remove(lore.size()-1);
							lore.remove(lore.size()-1);
							meta.setLore(lore);
							spawnerItem.setItemMeta(meta);
							event.getWhoClicked().closeInventory();
							spawner.getBlock().setType(Material.AIR);
							spawner.getBlock().getWorld().dropItemNaturally(spawner.getBlock().getLocation().add(0.5, 0, 0.5), spawnerItem);
						}
						if (refresh) {
							event.getInventory().setContents(InventoryBuilder.spawnerInventory(spawner).getContents());
						}
					}
				}
			}
		}
	}
}
