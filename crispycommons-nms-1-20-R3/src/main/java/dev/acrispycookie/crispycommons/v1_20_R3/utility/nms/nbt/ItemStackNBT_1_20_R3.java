package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.ItemStackNBT;
import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import net.minecraft.nbt.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemStackNBT_1_20_R3 implements ItemStackNBT {

    public @NotNull ItemStack addTag(@NotNull ItemStack i, @NotNull String identifier, @NotNull BaseTag value) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.u() ? nmsItem.v() : new NBTTagCompound();
        assert tag != null : "NBT tag was null. Contact developer.";
        tag.a(identifier, ((BaseTag_1_20_R3) value).getInternal());
        nmsItem.c(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public @NotNull ItemStack removeTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.u() ? nmsItem.v() : new NBTTagCompound();
        assert tag != null : "NBT tag was null. Contact developer.";
        tag.r(identifier);
        nmsItem.c(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public @Nullable BaseTag getTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier)) {
            return new BaseTag_1_20_R3(compound.c(identifier));
        }
        return null;
    }

    public boolean hasTag(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        if(i.getType() != Material.AIR && i.getItemMeta() != null && nmsItem.u()) {
            return compound.e(identifier);
        }
        return false;
    }

    public int getInt(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        NBTBase baseTag = compound.c(identifier);
        assert baseTag != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier) && baseTag.b() == 3) {
            return ((NBTTagInt) baseTag).g();
        }
        return 0;
    }

    public double getDouble(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        NBTBase baseTag = compound.c(identifier);
        assert baseTag != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier) && baseTag.b() == 6) {
            return ((NBTTagDouble) baseTag).j();
        }
        return 0;
    }

    public long getLong(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        NBTBase baseTag = compound.c(identifier);
        assert baseTag != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier) && baseTag.b() == 4) {
            return ((NBTTagLong) baseTag).f();
        }
        return 0;
    }

    public boolean getBoolean(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        NBTBase baseTag = compound.c(identifier);
        assert baseTag != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier) && baseTag.b() == 3) {
            return ((NBTTagInt) baseTag).g() == 1;
        }
        return false;
    }

    public @Nullable String getString(@NotNull ItemStack i, @NotNull String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsItem.v();
        assert compound != null : "NBT tag was null. Contact developer.";
        if(nmsItem.u() && compound.e(identifier)) {
            return compound.l(identifier);
        }
        return null;
    }
}
