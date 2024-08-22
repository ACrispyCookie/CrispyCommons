package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.inventory.ItemStack;

public interface ItemStackNBT {

    static ItemStackNBT newInstance() {
        return VersionManager.get().createInstance(ItemStackNBT.class);
    }

    ItemStack addTag(ItemStack i, String identifier, NBTBase value);
    ItemStack removeTag(ItemStack i, String identifier);
    NBTBase getTag(ItemStack i, String identifier);
    boolean hasTag(ItemStack i, String identifier);
    int getInt(ItemStack i, String identifier);
    double getDouble(ItemStack i, String identifier);
    long getLong(ItemStack i, String identifier);
    boolean getBoolean(ItemStack i, String identifier);
    String getString(ItemStack i, String identifier);
}
