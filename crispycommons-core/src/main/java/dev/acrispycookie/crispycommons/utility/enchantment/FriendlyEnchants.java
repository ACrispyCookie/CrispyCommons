package dev.acrispycookie.crispycommons.utility.enchantment;

import com.cryptomorin.xseries.XEnchantment;

/**
 * An enumeration of enchantments in Minecraft, providing user-friendly names for display purposes.
 * <p>
 * Each enchantment is mapped to its corresponding friendly name, which is often more intuitive and
 * recognizable to players. Additionally, this enum can identify if an enchantment is considered a curse.
 * </p>
 */
public enum FriendlyEnchants {

    ARROW_DAMAGE("Power"),
    ARROW_FIRE("Flame"),
    ARROW_INFINITE("Infinite"),
    ARROW_KNOCKBACK("Punch"),
    BINDING_CURSE("Curse of Binding",true),
    CHANNELING("Channeling"),
    DAMAGE_ALL("Sharpness"),
    DAMAGE_ARTHROPODS("Bane of Arthropods"),
    DAMAGE_UNDEAD("Smite"),
    DEPTH_STRIDER("Depth Strider"),
    DIG_SPEED("Efficiency"),
    DURABILITY("Unbreaking"),
    FIRE_ASPECT("Fire Aspect"),
    FROST_WALKER("Frost Walker"),
    IMPALING("Impaling"),
    KNOCKBACK("Knockback"),
    LOOT_BONUS_BLOCKS("Fortune"),
    LOOT_BONUS_MOBS("Looting"),
    LOYALTY("Loyalty"),
    LUCK("Luck of the Sea"),
    LURE("Lure"),
    MENDING("Mending"),
    MULTISHOT("Multishot"),
    OXYGEN("Respiration"),
    PIERCING("Piercing"),
    PROTECTION_ENVIRONMENTAL("Protection"),
    PROTECTION_EXPLOSIONS("Blast Protection"),
    PROTECTION_FALL("Feather Falling"),
    PROTECTION_FIRE("Fire Protection"),
    PROTECTION_PROJECTILE("Projectile Protection"),
    QUICK_CHARGE("Quick Charge"),
    RIPTIDE("Riptide"),
    SILK_TOUCH("Silk Touch"),
    SOUL_SPEED("Soul Speed"),
    SWEEPING_EDGE("Sweeping Edge"),
    THORNS("Thorns"),
    VANISHING_CURSE("Curse of Vanishing",true),
    WATER_WORKER("Aqua Affinity");


    /**
     * The friendly name of the enchantment.
     */
    private final String friendlyName;

    /**
     * Boolean that describes if the enchantment is considered a curse.
     */
    private final boolean isCurse;

    /**
     * Constructs a {@code FriendlyEnchants} enum with the specified friendly name and curse status.
     *
     * @param friendlyName the user-friendly name of the enchantment.
     * @param isCurse      {@code true} if the enchantment is a curse, otherwise {@code false}.
     */
    FriendlyEnchants(String friendlyName,boolean isCurse) {
        this.friendlyName = friendlyName;
        this.isCurse = isCurse;
    }

    /**
     * Constructs a {@code FriendlyEnchants} enum with the specified friendly name.
     * The curse status is set to {@code false} by default.
     *
     * @param friendlyName the user-friendly name of the enchantment.
     */
    FriendlyEnchants(String friendlyName) {
        this(friendlyName,false);
    }

    /**
     * Returns the {@code FriendlyEnchants} corresponding to the given Minecraft enchantment.
     *
     * @param enchant the Minecraft {@link XEnchantment} to look up.
     * @return the corresponding {@code FriendlyEnchants} enum.
     */
    public static FriendlyEnchants getFriendlyEnchantment(XEnchantment enchant) {
        return FriendlyEnchants.valueOf(enchant.name());
    }

    /**
     * Gets the user-friendly name of the enchantment.
     *
     * @return the friendly name of the enchantment.
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Checks if the enchantment is a curse.
     *
     * @return {@code true} if the enchantment is a curse, otherwise {@code false}.
     */
    public boolean isCurse() {
        return isCurse;
    }

}