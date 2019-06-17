package penowl.plugin.eggdrops;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class FakeHolder implements InventoryHolder{
	
	public CreatureSpawner spawner;
	
	@Override
    public Inventory getInventory() {
        return null;
    }
	
	public FakeHolder(CreatureSpawner ctrspawner) {
		spawner = ctrspawner;
		
	}
	
}
