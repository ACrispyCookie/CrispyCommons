package dev.acrispycookie.crispycommons.utility;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ItemMetaEditor implements Versioned {

    private static final ItemMetaEditor instance = VersionManager.createInstance(ItemMetaEditor.class, new MappedVersions());

    public static ItemMetaEditor newInstance() {
        return instance;
    }

    public abstract void setUnbreakable(ItemMeta meta, boolean unbreakable);

    public static MappedVersions getRemapped() {
        return new MappedVersions();
    }
}
