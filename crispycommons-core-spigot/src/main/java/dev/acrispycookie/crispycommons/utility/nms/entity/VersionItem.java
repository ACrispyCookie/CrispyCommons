package dev.acrispycookie.crispycommons.utility.nms.entity;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface VersionItem extends VersionEntity {

    static @NotNull VersionItem newInstance(@NotNull Location location) {
        return VersionManager.createInstance(VersionItem.class, new MappedVersions(), new ArgPair<>(Location.class, location));
    }

    static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }

    void setDespawnDelay(int delay);
    void setItemStack(@NotNull ItemStack itemStack);
}
