package penowl.plugin.eggdrops;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryBuilder {
	
	public static ItemStack named(Material material, String name) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack named(Material material, int amount, String name) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
		
	public static Inventory spawnerInventory(CreatureSpawner spawner) {
		String title = spawner.getSpawnedType().toString();
		title = title.replaceAll("_", " ");
		title = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + title + ChatColor.RESET + " Spawner";
		Inventory inventory = Bukkit.createInventory(new FakeHolder(spawner), 45, title);
		ItemStack blank = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = blank.getItemMeta();
		meta.setDisplayName(" ");
		blank.setItemMeta(meta);
		for(int x = 0; x < 45; x = x + 1) {
			inventory.setItem(x, blank.clone());
		}
		ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
		ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
		spawnerItemMeta.setDisplayName(title);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA+"Current stats:");
		lore.add(ChatColor.GOLD+"Power: "+ChatColor.GRAY+"1");
		lore.add(ChatColor.GOLD+"Time: "+ChatColor.GRAY+"1");
		lore.add(ChatColor.GOLD+"Mind: "+ChatColor.GRAY+"1");
		lore.add(ChatColor.GOLD+"Soul: "+ChatColor.GRAY+"1");
		lore.add(" ");
		lore.add(ChatColor.RED+"Right-click to pick up this spawner.");
		spawnerItemMeta.setLore(lore);
		spawnerItem.setItemMeta(spawnerItemMeta);
		inventory.setItem(13, spawnerItem);
		inventory.setItem(15, named(Material.COAL, ChatColor.GRAY+"Fuel Menu"));
		inventory.setItem(11, named(Material.CLOCK, ChatColor.BLUE+"Mobs Per Hour: "));
		inventory.setItem(28, named(Material.PURPLE_DYE, 1, ChatColor.LIGHT_PURPLE + "Power Menu"));
		inventory.setItem(30, named(Material.LIME_DYE, 1, ChatColor.GREEN + "Time Menu"));
		inventory.setItem(32, named(Material.YELLOW_DYE, 1, ChatColor.YELLOW + "Mind Menu"));
		inventory.setItem(34, named(Material.ORANGE_DYE, 1, ChatColor.GOLD + "Soul Menu"));
		return inventory;
	}
	
}
