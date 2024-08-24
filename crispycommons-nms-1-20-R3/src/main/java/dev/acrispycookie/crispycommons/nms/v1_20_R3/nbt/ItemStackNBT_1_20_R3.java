package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.ItemStackNBT;
import dev.acrispycookie.crispycommons.nms.nbt.BaseTag;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemStackNBT_1_20_R3 implements ItemStackNBT {

    public ItemStack addTag(ItemStack i, String identifier, BaseTag value) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.u() ? nmsItem.v() : new NBTTagCompound();
        tag.a(identifier, ((BaseTag_1_20_R3) value).getInternal());
        nmsItem.c(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public ItemStack removeTag(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        NBTTagCompound tag = nmsItem.u() ? nmsItem.v() : new NBTTagCompound();
        tag.r(identifier);
        nmsItem.c(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public BaseTag getTag(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier)) {
            return new BaseTag_1_20_R3(nmsItem.v().c(identifier));
        }
        return null;
    }

    public boolean hasTag(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(i != null && i.getType() != Material.AIR && i.getItemMeta() != null && nmsItem.u()) {
            return nmsItem.v().e(identifier);
        }
        return false;
    }

    public int getInt(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier) && nmsItem.v().c(identifier).b() == 3) {
            return ((NBTTagInt) nmsItem.v().c(identifier)).g();
        }
        return 0;
    }

    public double getDouble(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier) && nmsItem.v().c(identifier).b() == 6) {
            return ((NBTTagDouble) nmsItem.v().c(identifier)).j();
        }
        return 0;
    }

    public long getLong(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier) && nmsItem.v().c(identifier).b() == 4) {
            return ((NBTTagLong) nmsItem.v().c(identifier)).f();
        }
        return 0;
    }

    public boolean getBoolean(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier) && nmsItem.v().c(identifier).b() == 3) {
            return ((NBTTagInt) nmsItem.v().c(identifier)).g() == 1;
        }
        return false;
    }

    public String getString(ItemStack i, String identifier) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(nmsItem.u() && nmsItem.v().e(identifier)) {
            return nmsItem.v().l(identifier);
        }
        return null;
    }
}
