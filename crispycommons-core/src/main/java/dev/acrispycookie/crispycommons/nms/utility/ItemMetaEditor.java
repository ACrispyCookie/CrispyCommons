package dev.acrispycookie.crispycommons.nms.utility;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.inventory.meta.ItemMeta;

public interface ItemMetaEditor {

    ItemMetaEditor instance = VersionManager.get().createInstance(ItemMetaEditor.class);

    static ItemMetaEditor newInstance() {
        return instance;
    }

    void setUnbreakable(ItemMeta meta, boolean unbreakable);
}
