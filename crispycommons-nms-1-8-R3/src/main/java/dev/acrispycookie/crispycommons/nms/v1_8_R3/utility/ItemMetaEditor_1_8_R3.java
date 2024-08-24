package dev.acrispycookie.crispycommons.nms.v1_8_R3.utility;

import dev.acrispycookie.crispycommons.nms.utility.ItemMetaEditor;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMetaEditor_1_8_R3 implements ItemMetaEditor {
    @Override
    public void setUnbreakable(ItemMeta meta, boolean unbreakable) {
        meta.spigot().setUnbreakable(unbreakable);
    }
}
