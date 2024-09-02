package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms;

import dev.acrispycookie.crispycommons.utility.nms.ItemMetaEditor;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ItemMetaEditor_1_8_R3 extends ItemMetaEditor {
    @Override
    public void setUnbreakable(@NotNull ItemMeta meta, boolean unbreakable) {
        meta.spigot().setUnbreakable(unbreakable);
    }
}
