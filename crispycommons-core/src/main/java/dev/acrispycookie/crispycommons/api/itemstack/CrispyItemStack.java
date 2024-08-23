package dev.acrispycookie.crispycommons.api.itemstack;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import dev.acrispycookie.crispycommons.nms.wrappers.utilities.ItemMetaEditor;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.ItemStackNBT;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A specialized {@link ItemStack} implementation that allows for fluent and extended customization
 * of Minecraft items.
 * <p>
 * The {@link CrispyItemStack} class provides various methods to customize item properties, such as
 * setting the name, material, durability, lore, and more. It also supports adding and removing
 * enchantments, item flags, and custom NBT tags.
 * </p>
 */
public class CrispyItemStack extends ItemStack implements CrispyItem {

    /**
     * Constructs a {@code CrispyItemStack} from an existing {@link ItemStack}.
     *
     * @param itemStack the existing {@link ItemStack} to wrap.
     */
    public CrispyItemStack(@NotNull ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Constructs a {@code CrispyItemStack} with the specified {@link Material} and sets the default amount to 1.
     *
     * @param material the {@link Material} to set for this item stack.
     */
    public CrispyItemStack(@NotNull XMaterial material) {
        super(material.parseMaterial() != null ? material.parseMaterial() : Material.STONE);
        amount(1);
    }

    /**
     * Constructs a {@code CrispyItemStack} with the specified material, amount, and durability.
     *
     * @param material the {@link Material} to set for this item stack.
     * @param amount the amount of items in this stack.
     * @param durability the durability value to set for this item stack.
     */
    public CrispyItemStack(@NotNull XMaterial material, int amount, short durability) {
        super(material.parseMaterial() != null ? material.parseMaterial() : Material.STONE, amount, durability);
    }

    /**
     * Sets the material of this item stack.
     *
     * @param material the {@link Material} to set.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack material(@NotNull XMaterial material) {
        this.setType(material.parseMaterial() != null ? material.parseMaterial() : Material.STONE);
        return this;
    }

    /**
     * Sets the display name of this item stack.
     *
     * @param s the new display name, supporting color codes with {@code &}.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack name(@NotNull String s) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the amount of items in this stack.
     *
     * @param amount the amount to set.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack amount(int amount) {
        this.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability of this item stack.
     *
     * @param dur the durability value to set.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack durability(short dur) {
        this.setDurability(dur);
        return this;
    }

    /**
     * Sets the lore of this item stack, with each line separated by a newline character.
     *
     * @param s the lore string, with each line separated by {@code \n}.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack lore(@NotNull String s) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(new ArrayList<>());

        ArrayList<String> lore = new ArrayList<>();
        String[] list = s.split("\n");
        for (String ss : list) {
            lore.add(ChatColor.translateAlternateColorCodes('&', ss));
        }
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an enchantment to this item stack.
     *
     * @param enchantment the {@link Enchantment} to add.
     * @param level the level of the enchantment to apply.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack addEnchant(@NotNull Enchantment enchantment, int level) {
        ItemMeta meta = this.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Adds an item flag to this item stack.
     *
     * @param flag the {@link ItemFlag} to add.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack addFlag(@NotNull ItemFlag flag) {
        ItemMeta meta = this.getItemMeta();
        meta.addItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Removes an item flag from this item stack.
     *
     * @param flag the {@link ItemFlag} to remove.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack removeFlag(@NotNull ItemFlag flag) {
        ItemMeta meta = this.getItemMeta();
        meta.removeItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the unbreakable status of this item stack.
     *
     * @param unb {@code true} to make the item unbreakable, {@code false} otherwise.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack unbreakable(boolean unb) {
        ItemMeta meta = this.getItemMeta();
        ItemMetaEditor.newInstance().setUnbreakable(meta, unb);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Sets whether the item attributes should be hidden in this item stack's tooltip.
     *
     * @param hide {@code true} to hide attributes, {@code false} to show them.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack hideAttributes(boolean hide) {
        if (hide) {
            addFlag(ItemFlag.HIDE_ATTRIBUTES);
        } else {
            removeFlag(ItemFlag.HIDE_ATTRIBUTES);
        }
        return this;
    }

    /**
     * Applies or removes a glint effect on this item stack.
     * <p>
     * If the glint is added and the item does not have an enchantment, a dummy enchantment is applied to create
     * the effect.
     * </p>
     *
     * @param gl {@code true} to add a glint effect, {@code false} to remove it.
     * @return the updated {@link CrispyItemStack} instance.
     */
    public @NotNull CrispyItemStack glint(boolean gl) {
        if (gl && !hasTag("ench")) {
            if (this.getType() == Material.BOW)
                addEnchant(XEnchantment.FEATHER_FALLING.getEnchant() != null ? XEnchantment.FEATHER_FALLING.getEnchant() : Enchantment.INFINITY, 1);
            else
                addEnchant(XEnchantment.INFINITY.getEnchant() != null ? XEnchantment.INFINITY.getEnchant() : Enchantment.INFINITY, 1);
            addFlag(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    /**
     * Adds a custom NBT tag to this item stack.
     *
     * @param identifier the identifier for the tag.
     * @param value the {@link NBTBase} value to associate with the tag.
     * @return a new {@link CrispyItemStack} instance with the tag applied.
     */
    public @NotNull CrispyItemStack addTag(@NotNull String identifier, @NotNull NBTBase value) {
        return new CrispyItemStack(ItemStackNBT.newInstance().addTag(this, identifier, value));
    }

    /**
     * Removes a custom NBT tag from this item stack.
     *
     * @param identifier the identifier of the tag to remove.
     * @return a new {@link CrispyItemStack} instance with the tag removed.
     */
    public @NotNull CrispyItemStack removeTag(@NotNull String identifier) {
        return new CrispyItemStack(ItemStackNBT.newInstance().removeTag(this, identifier));
    }

    /**
     * Checks if this item stack has the specified item flag.
     *
     * @param flag the {@link ItemFlag} to check for.
     * @return {@code true} if the flag is present, {@code false} otherwise.
     */
    public boolean hasFlag(ItemFlag flag) {
        return this.getItemMeta().hasItemFlag(flag);
    }

    /**
     * Retrieves a custom NBT tag from this item stack.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the {@link NBTBase} value associated with the identifier, or {@code null} if not present.
     */
    public NBTBase getTag(String identifier) {
        return ItemStackNBT.newInstance().getTag(this, identifier);
    }

    /**
     * Retrieves an integer value from a custom NBT tag.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the integer value associated with the identifier.
     */
    public int getTagInt(String identifier) {
        return ItemStackNBT.newInstance().getInt(this, identifier);
    }

    /**
     * Retrieves a double value from a custom NBT tag.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the double value associated with the identifier.
     */
    public double getTagDouble(String identifier) {
        return ItemStackNBT.newInstance().getDouble(this, identifier);
    }

    /**
     * Retrieves a long value from a custom NBT tag.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the long value associated with the identifier.
     */
    public long getTagLong(String identifier) {
        return ItemStackNBT.newInstance().getLong(this, identifier);
    }

    /**
     * Retrieves a boolean value from a custom NBT tag.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the boolean value associated with the identifier.
     */
    public boolean getTagBoolean(String identifier) {
        return ItemStackNBT.newInstance().getBoolean(this, identifier);
    }

    /**
     * Retrieves a string value from a custom NBT tag.
     *
     * @param identifier the identifier of the tag to retrieve.
     * @return the string value associated with the identifier.
     */
    public String getTagString(String identifier) {
        return ItemStackNBT.newInstance().getString(this, identifier);
    }

    /**
     * Checks if this item stack has a custom NBT tag with the specified identifier.
     *
     * @param identifier the identifier of the tag to check.
     * @return {@code true} if the tag is present, {@code false} otherwise.
     */
    public boolean hasTag(String identifier) {
        return ItemStackNBT.newInstance().hasTag(this, identifier);
    }
}
