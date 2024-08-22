package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.ItemStackNBT;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemStackNBT_1_8_R3 implements ItemStackNBT {

    public ItemStack addTag(ItemStack i, String identifier, NBTBase value) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.set(identifier, ((NBTBase_1_8_R3) value).getInternal());
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public ItemStack removeTag(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.remove(identifier);
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public NBTBase getTag(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier)) {
            return new NBTBase_1_8_R3(nmsItem.getTag().get(identifier));
        }
        return null;
    }

    public boolean hasTag(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(i != null && i.getType() != Material.AIR && i.getItemMeta() != null && nmsItem.hasTag()) {
            return nmsItem.getTag().hasKey(identifier);
        }
        return false;
    }

    public int getInt(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 3) {
            return ((NBTTagInt) nmsItem.getTag().get(identifier)).d();
        }
        return 0;
    }

    public double getDouble(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 6) {
            return ((NBTTagDouble) nmsItem.getTag().get(identifier)).g();
        }
        return 0;
    }

    public long getLong(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 4) {
            return ((NBTTagLong) nmsItem.getTag().get(identifier)).c();
        }
        return 0;
    }

    public boolean getBoolean(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier) && nmsItem.getTag().get(identifier).getTypeId() == 3) {
            return ((NBTTagInt) nmsItem.getTag().get(identifier)).d() == 1;
        }
        return false;
    }

    public String getString(ItemStack i, String identifier) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.hasTag() && nmsItem.getTag().hasKey(identifier)) {
            return nmsItem.getTag().getString(identifier);
        }
        return null;
    }
}
