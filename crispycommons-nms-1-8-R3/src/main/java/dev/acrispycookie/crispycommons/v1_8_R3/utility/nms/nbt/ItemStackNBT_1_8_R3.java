package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.ItemStackNBT;
import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemStackNBT_1_8_R3 implements ItemStackNBT {

    public @NotNull ItemStack addTag(@NotNull ItemStack i, @NotNull String identifier, @NotNull BaseTag value) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.set(identifier, ((BaseTag_1_8_R3) value).getInternal());
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public @NotNull ItemStack removeTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.remove(identifier);
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public @Nullable BaseTag getTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier)) {
            return new BaseTag_1_8_R3(nmsItem.getTag().get(identifier));
        }
        return null;
    }

    public boolean hasTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(i.getType() != Material.AIR && i.getItemMeta() != null && nmsItem.hasTag()) {
            return nmsItem.getTag().hasKey(identifier);
        }
        return false;
    }

    public int getInt(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 3) {
            return ((NBTTagInt) nmsItem.getTag().get(identifier)).d();
        }
        return 0;
    }

    public double getDouble(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 6) {
            return ((NBTTagDouble) nmsItem.getTag().get(identifier)).g();
        }
        return 0;
    }

    public long getLong(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 4) {
            return ((NBTTagLong) nmsItem.getTag().get(identifier)).c();
        }
        return 0;
    }

    public boolean getBoolean(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 3) {
            return ((NBTTagInt) nmsItem.getTag().get(identifier)).d() == 1;
        }
        return false;
    }

    public @Nullable String getString(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier)) {
            return nmsItem.getTag().getString(identifier);
        }
        return null;
    }
}
