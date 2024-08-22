package dev.acrispycookie.crispycommons.nms.wrappers.entity;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface EntityItem extends Entity {

    static EntityItem newInstance(Location location, ItemStack itemStack) {
        EntityItem item = VersionManager.get().createInstance(EntityItem.class);
        item.init(location, itemStack);
        return item;
    }

    void init(Location location, ItemStack itemStack);
    void setDespawnDelay(int delay);
    void setItemStack(ItemStack itemStack);
}
