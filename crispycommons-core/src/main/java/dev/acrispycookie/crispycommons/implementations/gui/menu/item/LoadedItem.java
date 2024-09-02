package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A base class for menu items that are always considered loaded.
 * <p>
 * The {@code LoadedItem} class extends {@link AbstractMenuItem} and provides a simplified implementation
 * where the item is always treated as loaded. This class disables the handling of unloaded states
 * by overriding the {@link #onClickUnloaded(CrispyMenu, Player)} method with an empty implementation.
 * </p>
 */
public abstract class LoadedItem extends AbstractMenuItem {

    /**
     * Constructs a {@code LoadedItem} with the specified display and alternative display elements.
     *
     * @param display the main display element for this item.
     * @param alternativeDisplay the alternative display element for this item.
     *
     * @throws NullPointerException if {@code display} or {@code alternativeDisplay} is {@code null}.
     */
    public LoadedItem(@NotNull ItemElement<?> display, @NotNull ItemElement<?> alternativeDisplay) {
        super(display, display, alternativeDisplay);
    }

    /**
     * Handles the event when the item is clicked while it is unloaded.
     * <p>
     * Since this item is always loaded, this method is overridden with an empty implementation.
     * </p>
     *
     * @param menu the menu containing this item.
     * @param player the player who clicked the item.
     */
    @Override
    public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
        // No action needed since the item is always loaded
    }

    /**
     * Marks the item as loaded.
     * <p>
     * This method sets the {@code isLoaded} flag to {@code true}
     * </p>
     *
     * @param onLoad this runnable is not run in this type of item.
     */
    @Override
    public void load(@NotNull Runnable onLoad) {
        this.isLoaded = true;
    }
}

