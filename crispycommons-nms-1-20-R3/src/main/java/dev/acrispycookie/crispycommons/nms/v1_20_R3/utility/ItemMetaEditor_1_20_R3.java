package dev.acrispycookie.crispycommons.nms.v1_20_R3.utility;

import dev.acrispycookie.crispycommons.nms.wrappers.utilities.ItemMetaEditor;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMetaEditor_1_20_R3 implements ItemMetaEditor {
    @Override
    public void setUnbreakable(ItemMeta meta, boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
    }
}
