package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemStackNBT {

    ItemStackNBT instance = VersionManager.createInstance(ItemStackNBT.class, new MappedVersions());

    static @NotNull ItemStackNBT newInstance() {
        return instance;
    }

    @NotNull ItemStack addTag(@NotNull ItemStack i, @NotNull String identifier, @NotNull BaseTag value);
    @NotNull ItemStack removeTag(@NotNull ItemStack i, @NotNull String identifier);
    @Nullable BaseTag getTag(@NotNull ItemStack i, @NotNull String identifier);
    boolean hasTag(@NotNull ItemStack i, @NotNull String identifier);
    int getInt(@NotNull ItemStack i, @NotNull String identifier);
    double getDouble(@NotNull ItemStack i, @NotNull String identifier);
    long getLong(@NotNull ItemStack i, @NotNull String identifier);
    boolean getBoolean(@NotNull ItemStack i, @NotNull String identifier);
    @Nullable String getString(@NotNull ItemStack i, @NotNull String identifier);
}
