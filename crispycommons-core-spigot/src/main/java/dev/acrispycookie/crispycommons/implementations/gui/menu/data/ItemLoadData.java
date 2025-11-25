package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * A data class representing the necessary information to load an item in a {@link CrispyMenu}.
 * <p>
 * {@code ItemLoadData} encapsulates the display elements, visibility logic, and click handling logic
 * for an item in a menu. It is typically used by classes that manage the loading of menu items dynamically.
 * </p>
 */
public class ItemLoadData {

    /**
     * The primary {@link ItemElement} to be displayed.
     */
    private final ItemElement<?> display;

    /**
     * The alternative {@link ItemElement} to be displayed, typically used for a different state or fallback.
     */
    private final ItemElement<?> alternativeDisplay;

    /**
     * A function that determines whether the item is visible to the player.
     */
    private final BiFunction<CrispyMenu, Player, Boolean> canSee;

    /**
     * A function defining the action to be performed when the item is clicked.
     */
    private final BiConsumer<CrispyMenu, Player> onClick;

    /**
     * Constructs an {@code ItemLoadData} instance with the specified display elements and functional logic.
     *
     * @param display the primary {@link ItemElement} to be displayed.
     * @param alternativeDisplay the alternative {@link ItemElement} to be displayed.
     * @param canSee a {@link BiFunction} determining whether the item is visible to the player.
     * @param onClick a {@link BiConsumer} defining the action to be performed when the item is clicked.
     *
     * @throws NullPointerException if any of the parameters are {@code null}.
     */
    public ItemLoadData(@NotNull ItemElement<?> display, @NotNull ItemElement<?> alternativeDisplay, @NotNull BiFunction<CrispyMenu, Player, Boolean> canSee, @NotNull BiConsumer<CrispyMenu, Player> onClick) {
        this.display = display;
        this.alternativeDisplay = alternativeDisplay;
        this.canSee = canSee;
        this.onClick = onClick;
    }

    /**
     * Retrieves the primary {@link ItemElement} to be displayed.
     *
     * @return the primary display {@link ItemElement}.
     */
    public @NotNull ItemElement<?> getDisplay() {
        return display;
    }

    /**
     * Retrieves the alternative {@link ItemElement} to be displayed.
     *
     * @return the alternative display {@link ItemElement}.
     */
    public @NotNull ItemElement<?> getAlternativeDisplay() {
        return alternativeDisplay;
    }

    /**
     * Retrieves the function that determines whether the item is visible to the player.
     *
     * @return a {@link BiFunction} that returns {@code true} if the item is visible, otherwise {@code false}.
     */
    public @NotNull BiFunction<CrispyMenu, Player, Boolean> getCanSee() {
        return canSee;
    }

    /**
     * Retrieves the function that defines the action to be performed when the item is clicked.
     *
     * @return a {@link BiConsumer} that defines the onClick behavior.
     */
    public @NotNull BiConsumer<CrispyMenu, Player> getOnClick() {
        return onClick;
    }
}

