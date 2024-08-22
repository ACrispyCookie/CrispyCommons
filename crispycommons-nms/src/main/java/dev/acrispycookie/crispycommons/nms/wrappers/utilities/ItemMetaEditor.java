package dev.acrispycookie.crispycommons.nms.wrappers.utilities;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.inventory.meta.ItemMeta;

public interface ItemMetaEditor {

    static ItemMetaEditor newInstance() {
        return VersionManager.get().createInstance(ItemMetaEditor.class);
    }

    void setUnbreakable(ItemMeta meta, boolean unbreakable);
}
