package dev.acrispycookie.crispycommons.implementations.wrappers.itemstack;

import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CrispyItemStack extends ItemStack implements CrispyItem {

    public CrispyItemStack(ItemStack itemStack) {
        super(itemStack);
    }

    public CrispyItemStack(Material material) {
        super(material);
    }

    public CrispyItemStack(Material mat, int amount, short durability) {
        super(mat, amount, durability);
    }

    public CrispyItemStack material(Material mat) {
        this.setType(mat);
        return this;
    }

    public CrispyItemStack name(String s) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
        this.setItemMeta(meta);
        return this;
    }

    public CrispyItemStack amount(int amount) {
        this.setAmount(amount);
        return this;
    }

    public CrispyItemStack durability(short dur) {
        this.setDurability(dur);
        return this;
    }

    public CrispyItemStack lore(String s) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(new ArrayList<>());

        ArrayList<String> lore = new ArrayList<>();
        String[] list = s.split("\n");
        for(String ss : list) {
            lore.add(ChatColor.translateAlternateColorCodes('&', ss));
        }
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }

    public CrispyItemStack addEnchant(Enchantment ench, int level) {
        ItemMeta meta = this.getItemMeta();
        meta.addEnchant(ench, level, true);
        this.setItemMeta(meta);
        return this;
    }

    public CrispyItemStack addFlag(ItemFlag flag) {
        ItemMeta meta = this.getItemMeta();
        meta.addItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }

    public CrispyItemStack removeFlag(ItemFlag flag) {
        ItemMeta meta = this.getItemMeta();
        meta.removeItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }


    public CrispyItemStack unbreakable(boolean unb) {
        ItemMeta meta = this.getItemMeta();
        meta.spigot().setUnbreakable(unb);
        this.setItemMeta(meta);
        return this;
    }

    public CrispyItemStack hideAttributes(boolean hide) {
        if(hide) {
            addFlag(ItemFlag.HIDE_ATTRIBUTES);
        } else {
            removeFlag(ItemFlag.HIDE_ATTRIBUTES);
        }
        return this;
    }

    public CrispyItemStack glint(boolean gl) {
        if(gl && !hasTag("ench")) {
            addTag("ench", new NBTTagList());
        }
        return this;
    }

    public CrispyItemStack addTag(String identifier, NBTBase value) {
        ItemStackNBT.addTag(this, identifier, value);
        return this;
    }

    public CrispyItemStack removeTag(String identifier) {
        ItemStackNBT.removeTag(this, identifier);
        return this;
    }

    public boolean hasFlag(ItemFlag flag) {
        return this.getItemMeta().hasItemFlag(flag);
    }

    public NBTBase getTag(String identifier) {
        return ItemStackNBT.getTag(this, identifier);
    }

    public int getTagInt(String identifier) {
        return ItemStackNBT.getInt(this, identifier);
    }

    public double getTagDouble(String identifier) {
        return ItemStackNBT.getDouble(this, identifier);
    }

    public long getTagLong(String identifier) {
        return ItemStackNBT.getLong(this, identifier);
    }

    public boolean getTagBoolean(String identifier) {
        return ItemStackNBT.getBoolean(this, identifier);
    }

    public String getTagString(String identifier) {
        return ItemStackNBT.getString(this, identifier);
    }

    public boolean hasTag(String identifier) {
        return ItemStackNBT.hasTag(this, identifier);
    }
}
