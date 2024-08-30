package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.inventory.ItemStack;

public interface ItemStackNBT {

    ItemStackNBT instance = VersionManager.createInstance(ItemStackNBT.class, new MappedVersions());

    static ItemStackNBT newInstance() {
        return instance;
    }

    ItemStack addTag(ItemStack i, String identifier, BaseTag value);
    ItemStack removeTag(ItemStack i, String identifier);
    BaseTag getTag(ItemStack i, String identifier);
    boolean hasTag(ItemStack i, String identifier);
    int getInt(ItemStack i, String identifier);
    double getDouble(ItemStack i, String identifier);
    long getLong(ItemStack i, String identifier);
    boolean getBoolean(ItemStack i, String identifier);
    String getString(ItemStack i, String identifier);
}
