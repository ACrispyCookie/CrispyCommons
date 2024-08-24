package dev.acrispycookie.crispycommons.api.itemstack;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import dev.acrispycookie.crispycommons.nms.nbt.BaseTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a customizable item.
 * <p>
 * The {@code CrispyItem} interface defines methods for setting various properties of an item, including its name,
 * amount, material, and other attributes. Implementations of this interface allow for detailed customization of
 * item characteristics and behavior.
 * </p>
 *
 */
public interface CrispyItem {

    /**
     * Sets the name of the item.
     *
     * @param newName the new name for the item.
     * @return the updated {@link CrispyItem} instance with the new name set.
     */
    @NotNull CrispyItem name(@NotNull String newName);

    /**
     * Sets the amount of the item.
     *
     * @param amount the amount to set for the item.
     * @return the updated {@link CrispyItem} instance with the specified amount set.
     */
    @NotNull CrispyItem amount(int amount);

    /**
     * Sets the material type of the item.
     *
     * @param mat the {@link Material} to set for the item.
     * @return the updated {@link CrispyItem} instance with the specified material set.
     */
    @NotNull CrispyItem material(@NotNull XMaterial mat);

    /**
     * Sets the durability of the item.
     *
     * @param dur the durability value to set for the item.
     * @return the updated {@link CrispyItem} instance with the specified durability set.
     */
    @NotNull CrispyItem durability(short dur);

    /**
     * Sets the lore (description) of the item.
     *
     * @param s the lore string to set for the item.
     * @return the updated {@link CrispyItem} instance with the specified lore set.
     */
    @NotNull CrispyItem lore(@NotNull String s);

    /**
     * Adds an enchantment to the item.
     *
     * @param enchantment the {@link XEnchantment} to add to the item.
     * @param level the level of the enchantment to apply.
     * @return the updated {@link CrispyItem} instance with the enchantment added.
     */
    @NotNull CrispyItem addEnchant(@NotNull XEnchantment enchantment, int level);

    /**
     * Adds an item flag to the item.
     *
     * @param flag the {@link ItemFlag} to add to the item.
     * @return the updated {@link CrispyItem} instance with the item flag added.
     */
    @NotNull CrispyItem addFlag(@NotNull ItemFlag flag);

    /**
     * Removes an item flag from the item.
     *
     * @param flag the {@link ItemFlag} to remove from the item.
     * @return the updated {@link CrispyItem} instance with the item flag removed.
     */
    @NotNull CrispyItem removeFlag(@NotNull ItemFlag flag);

    /**
     * Sets whether the item is unbreakable.
     *
     * @param unb {@code true} if the item should be unbreakable; {@code false} otherwise.
     * @return the updated {@link CrispyItem} instance with the unbreakable setting applied.
     */
    @NotNull CrispyItem unbreakable(boolean unb);

    /**
     * Sets whether to hide the item attributes from the item’s display.
     *
     * @param hide {@code true} to hide the attributes; {@code false} to show them.
     * @return the updated {@link CrispyItem} instance with the hide attributes setting applied.
     */
    @NotNull CrispyItem hideAttributes(boolean hide);

    /**
     * Sets whether to apply a glint effect to the item.
     *
     * @param gl {@code true} to apply the glint effect; {@code false} to remove it.
     * @return the updated {@link CrispyItem} instance with the glint effect applied.
     */
    @NotNull CrispyItem glint(boolean gl);

    /**
     * Adds a custom tag to the item.
     *
     * @param identifier the identifier for the custom tag.
     * @param value the {@link BaseTag} value to associate with the identifier.
     * @return the updated {@link CrispyItem} instance with the custom tag added.
     */
    @NotNull CrispyItem addTag(@NotNull String identifier, @NotNull BaseTag value);

    /**
     * Removes a custom tag from the item.
     *
     * @param identifier the identifier for the custom tag to remove.
     * @return the updated {@link CrispyItem} instance with the custom tag removed.
     */
    @NotNull CrispyItem removeTag(@NotNull String identifier);
}
