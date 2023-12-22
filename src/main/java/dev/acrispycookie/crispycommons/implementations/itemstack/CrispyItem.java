package dev.acrispycookie.crispycommons.implementations.itemstack;

import net.minecraft.server.v1_8_R3.NBTBase;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

public interface CrispyItem {

    CrispyItem name(String newName);
    CrispyItem amount(int amount);
    CrispyItem material(Material mat);
    CrispyItem durability(short dur);
    CrispyItem lore(String s);
    CrispyItem addEnchant(Enchantment ench, int level);
    CrispyItem addFlag(ItemFlag flag);
    CrispyItem removeFlag(ItemFlag flag);
    CrispyItem unbreakable(boolean unb);
    CrispyItem hideAttributes(boolean hide);
    CrispyItem glint(boolean gl);
    CrispyItem addTag(String identifier, NBTBase value);
    CrispyItem removeTag(String identifier);
}
