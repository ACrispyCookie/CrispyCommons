package dev.acrispycookie.crispycommons.utility.entity;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface VersionItem extends VersionEntity {

    static VersionItem newInstance(Location location) {
        return VersionManager.createInstance(VersionItem.class, new MappedVersions(), new ArgPair<>(Location.class, location));
    }

    static MappedVersions getRemapped() {
        return new MappedVersions();
    }

    void setDespawnDelay(int delay);
    void setItemStack(ItemStack itemStack);
}
