package dev.acrispycookie.crispycommons.utility.nms;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class ItemMetaEditor implements Versioned {

    private static final ItemMetaEditor instance = VersionManager.createInstance(ItemMetaEditor.class, new MappedVersions());

    public static @NotNull ItemMetaEditor newInstance() {
        return instance;
    }

    public abstract void setUnbreakable(@NotNull ItemMeta meta, boolean unbreakable);

    public static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }
}
