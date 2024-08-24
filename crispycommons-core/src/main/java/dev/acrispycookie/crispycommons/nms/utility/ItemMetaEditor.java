package dev.acrispycookie.crispycommons.nms.utility;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import org.bukkit.inventory.meta.ItemMeta;

public interface ItemMetaEditor {

    ItemMetaEditor instance = VersionManager.get().createInstance(ItemMetaEditor.class, new MappedVersions());

    static ItemMetaEditor newInstance() {
        return instance;
    }

    void setUnbreakable(ItemMeta meta, boolean unbreakable);
}
