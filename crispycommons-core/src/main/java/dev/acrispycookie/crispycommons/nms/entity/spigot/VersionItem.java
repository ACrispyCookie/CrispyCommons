package dev.acrispycookie.crispycommons.nms.entity.spigot;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface VersionItem extends VersionEntity {

    static VersionItem newInstance(Location location, CrispyItemStack itemStack) {
        return VersionManager.get().createInstance(VersionItem.class, new MappedVersions(), new ArgPair<>(Location.class, location), new ArgPair<>(CrispyItemStack.class, itemStack));
    }

    void setDespawnDelay(int delay);
    void setItemStack(ItemStack itemStack);
}
