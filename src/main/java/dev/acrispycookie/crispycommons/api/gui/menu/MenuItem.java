package dev.acrispycookie.crispycommons.api.gui.menu;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.gui.menu.item.LoadedItem;
import dev.acrispycookie.crispycommons.implementations.gui.menu.item.LoadingItem;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Represents an item that can be added into a menu and can be interacted with.
 * A {@link MenuItem} describes the display and behavior associated with a single item within a
 * user interface menu.
 */
public interface MenuItem {

    /**
     * Creates a {@link LoadedItem} with the specified display elements and click handler.
     *
     * @param display the primary {@link ItemElement} to display.
     * @param altDisplay the secondary {@link ItemElement} to display when {@link #canSee(CrispyMenu, Player)} returns false.
     * @param onClick a {@link BiFunction} that handles click events on the item. It receives a {@link CrispyMenu}
     *                and a {@link Player}, and returns nothing.
     * @return a new {@link LoadedItem} instance with the given display elements and click handler.
     * @throws NullPointerException if {@code display}, {@code altDisplay}, or {@code onClick} is {@code null}.
     */
    static @NotNull LoadedItem loadedItem(@NotNull ItemElement<?> display, @NotNull ItemElement<?> altDisplay, @NotNull BiFunction<CrispyMenu, Player, Void> onClick) {
        return new LoadedItem(display, altDisplay) {
            @Override
            public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClick.apply(menu, player);
            }
        };
    }

    /**
     * Creates a {@link LoadedItem} with the specified display element and a click handler.
     * The {@link LoadedItem} is initialized with the provided {@code display} and a default secondary display element
     * created with {@link ItemElement#simple(CrispyItemStack)} using an {@link CrispyItemStack} with {@link Material#AIR}.
     *
     * @param display the primary {@link ItemElement} to display.
     * @param onClick a {@link BiFunction} that handles click events on the item. It receives a {@link CrispyMenu}
     *                and a {@link Player}, and returns nothing.
     * @return a new {@link LoadedItem} instance with the given display element and click handler.
     * @throws NullPointerException if {@code display} or {@code onClick} is {@code null}.
     */
    static @NotNull LoadedItem loadedItem(@NotNull ItemElement<?> display, @NotNull BiFunction<CrispyMenu, Player, Void> onClick) {
        return new LoadedItem(display, ItemElement.simple(new CrispyItemStack(Material.AIR))) {
            @Override
            public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClick.apply(menu, player);
            }
        };
    }

    /**
     * Creates a {@link LoadingItem} with the specified unloaded {@code unloadedDisplay} element, click handlers, and item element suppliers.
     * The {@link LoadingItem}'s initial unloadedDisplay is the given {@code unloadedDisplay} element. When loaded the item's display and alternative display
     * are obtained using the {@code displaySupplier} and {@code altDisplaySupplier}, respectively.
     *
     * @param unloadedDisplay the primary {@link ItemElement} to unloadedDisplay when the item is unloaded.
     * @param onClick a {@link BiFunction} that handles click events on the item when it is loaded. It receives a
     *                {@link CrispyMenu} and a {@link Player}, and returns nothing.
     * @param onClickUnloaded a {@link BiFunction} that handles click events on the item when it is unloaded. It
     *                        receives a {@link CrispyMenu} and a {@link Player}, and returns nothing. Must
     *                        not be {@code null}.
     * @param displaySupplier a {@link Supplier} that provides the {@link ItemElement} to be loaded.
     * @param altDisplaySupplier a {@link Supplier} that provides the alternative {@link ItemElement}.
     * @return a new {@link LoadingItem} instance with the given unloadedDisplay element, click handlers, and item element suppliers.
     * @throws NullPointerException if {@code unloadedDisplay}, {@code onClick}, {@code onClickUnloaded}, {@code displaySupplier},
     *                              or {@code altDisplaySupplier} is {@code null}.
     */
    static @NotNull LoadingItem loadingItem(@NotNull ItemElement<?> unloadedDisplay, @NotNull BiFunction<CrispyMenu, Player, Void> onClick,
                                            @NotNull BiFunction<CrispyMenu, Player, Void> onClickUnloaded, @NotNull Supplier<ItemElement<?>> displaySupplier, @NotNull Supplier<ItemElement<?>> altDisplaySupplier) {
        return new LoadingItem(unloadedDisplay) {
            @Override
            public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClickUnloaded.apply(menu, player);
            }

            @Override
            public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClick.apply(menu, player);
            }

            @Override
            public ItemElement<?> loadItem() {
                return displaySupplier.get();
            }

            @Override
            public ItemElement<?> loadAlternativeItem() {
                return altDisplaySupplier.get();
            }
        };
    }

    /**
     * Creates a {@link LoadingItem} with the specified unloaded {@code unloadedDisplay} element, click handlers, and item element suppliers.
     * The {@link LoadingItem}'s initial unloadedDisplay is the given {@code unloadedDisplay} element. When loaded the item's display is
     * obtained using the {@code displaySupplier} and the alternative display is created with {@link ItemElement#simple(CrispyItemStack)}
     * using an {@link CrispyItemStack} with {@link Material#AIR}.
     *
     * @param unloadedDisplay the primary {@link ItemElement} to unloadedDisplay when the item is unloaded.
     * @param onClick a {@link BiFunction} that handles click events on the item when it is loaded. It receives a
     *                {@link CrispyMenu} and a {@link Player}, and returns nothing.
     * @param onClickUnloaded a {@link BiFunction} that handles click events on the item when it is unloaded. It
     *                        receives a {@link CrispyMenu} and a {@link Player}, and returns nothing. Must
     *                        not be {@code null}.
     * @param displaySupplier a {@link Supplier} that provides the {@link ItemElement} to be loaded.
     * @return a new {@link LoadingItem} instance with the given unloadedDisplay element, click handlers, and display supplier.
     * @throws NullPointerException if {@code unloadedDisplay}, {@code onClick}, {@code onClickUnloaded}, {@code displaySupplier},
     *                              or {@code altDisplaySupplier} is {@code null}.
     */
    static LoadingItem loadingItem(@NotNull ItemElement<?> unloadedDisplay, @NotNull BiFunction<CrispyMenu, Player, Void> onClick, @NotNull BiFunction<CrispyMenu, Player, Void> onClickUnloaded, @NotNull Supplier<ItemElement<?>> displaySupplier) {
        return new LoadingItem(unloadedDisplay) {
            @Override
            public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClickUnloaded.apply(menu, player);
            }

            @Override
            public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
                onClick.apply(menu, player);
            }

            @Override
            public ItemElement<?> loadItem() {
                return displaySupplier.get();
            }

            @Override
            public ItemElement<?> loadAlternativeItem() {
                return ItemElement.simple(new CrispyItemStack(Material.AIR));
            }
        };
    }

    /**
     * Starts loading the {@link MenuItem}'s primary display and executes the provided {@link Runnable} when the display is done loading.
     * <p>
     * The {@code onLoad} {@link Runnable} will be invoked after the loading process of the item, allowing for
     * any necessary updates on the primary display of the item.
     * </p>
     *
     * @param onLoad a {@link Runnable} to execute when the item is loaded.
     * @throws NullPointerException if {@code onLoad} is {@code null}.
     */
    void load(@NotNull Runnable onLoad);

    /**
     * Starts loading the {@link MenuItem}'s alternative display and executes the provided {@link Runnable} when the alternative display is done loading.
     * <p>
     * The {@code onLoad} {@link Runnable} will be invoked after the loading process of the item, allowing for
     * any necessary updates on the alternative display of the item.
     * </p>
     *
     * @param onLoad a {@link Runnable} to execute when the item is loaded.
     * @throws NullPointerException if {@code onLoad} is {@code null}.
     */
    void loadAlternative(@NotNull Runnable onLoad);

    /**
     * Handles the click event for the item when it's loaded.
     * <p>
     * This method is invoked when the item is clicked and it is loaded.
     * Implementations should define the behavior that occurs in response to the click event.
     * </p>
     *
     * @param menu the {@link CrispyMenu} in which the item was clicked.
     * @param player the {@link Player} who clicked the item.
     * @throws NullPointerException if {@code menu} or {@code player} is {@code null}.
     */
    void onClick(@NotNull CrispyMenu menu, @NotNull Player player);

    /**
     * Handles the click event for the item when it hasn't yet loaded.
     * <p>
     * This method is invoked when the item is clicked and it is not loaded.
     * Implementations should define more generic behaviour that gives feedback about the
     * item's loading process.
     * </p>
     *
     * @param menu the {@link CrispyMenu} in which the item was clicked.
     * @param player the {@link Player} who clicked the item.
     * @throws NullPointerException if {@code menu} or {@code player} is {@code null}.
     */
    void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player);

    /**
     * Determines whether the item can be seen by the specified player in the given menu.
     * <p>
     * This method is used to check the visibility of the item based on the context of the {@link CrispyMenu} and
     * the {@link Player}. Implementations should provide the logic to decide if the item should be visible.
     * </p>
     *
     * @param menu the {@link CrispyMenu} in which the visibility of the item is being checked.
     * @param player the {@link Player} whose ability to see the item is being assessed.
     * @return {@code true} if the item can be seen by the player; {@code false} otherwise.
     * @throws NullPointerException if {@code menu} or {@code player} is {@code null}.
     */
    boolean canSee(@NotNull CrispyMenu menu, @NotNull Player player);

    /**
     * Determines whether the item can be seen by the specified player in the given menu while it hasn't yet loaded.
     * <p>
     * This method is used to check the visibility of the item while it is still loading based on the context of the {@link CrispyMenu} and
     * the {@link Player}. Implementations should provide the logic to decide if the item should be visible.
     * </p>
     *
     * @param menu the {@link CrispyMenu} in which the visibility of the item is being checked.
     * @param player the {@link Player} whose ability to see the item is being assessed.
     * @return {@code true} if the item can be seen by the player; {@code false} otherwise.
     * @throws NullPointerException if {@code menu} or {@code player} is {@code null}.
     */
    boolean canSeeUnloaded(@NotNull CrispyMenu menu, @NotNull Player player);

    /**
     * Determines whether the item can be taken by the specified player from the given menu.
     * <p>
     * This method checks whether the item can be interacted with or removed by the player based on the context
     * of the {@link CrispyMenu} and the {@link Player}. Implementations should provide the logic to decide if
     * the player has permission or ability to take the item.
     * </p>
     *
     * @param menu the {@link CrispyMenu} from which the item is being considered for removal or interaction.
     * @param player the {@link Player} whose ability to take the item is being assessed.
     * @return {@code true} if the item can be taken by the player; {@code false} otherwise.
     * @throws NullPointerException if {@code menu} or {@code player} is {@code null}.
     */
    boolean canTake(@NotNull CrispyMenu menu, @NotNull Player player);

    /**
     * Defines the behaviour of the {@link #canSee(CrispyMenu, Player)} function.
     * <p>
     * The {@code supplier} {@link BiFunction} will be used to evaluate the visibility of the item based on the
     * context of the {@link CrispyMenu} and the {@link Player}. This allows for dynamic control over the item's
     * visibility.
     * </p>
     *
     * @param supplier a {@link BiFunction} that returns {@code true} if the item can be seen by the player in the
     *                 given menu, or {@code false} otherwise. Must not be {@code null}.
     * @return the current {@link MenuItem} instance with the updated visibility logic.
     * @throws NullPointerException if {@code supplier} is {@code null}.
     */
    @NotNull MenuItem setCanSee(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier);

    /**
     * Defines the behaviour of the {@link #canSeeUnloaded(CrispyMenu, Player)} function.
     * <p>
     * The {@code supplier} {@link BiFunction} will be used to evaluate the visibility of the item while it is still loading
     * based on the context of the {@link CrispyMenu} and the {@link Player}. This allows for dynamic control over the item's
     * visibility.
     * </p>
     *
     * @param supplier a {@link BiFunction} that returns {@code true} if the item can be seen by the player in the
     *                 given menu, or {@code false} otherwise. Must not be {@code null}.
     * @return the current {@link MenuItem} instance with the updated visibility logic.
     * @throws NullPointerException if {@code supplier} is {@code null}.
     */
    @NotNull MenuItem setCanSeeUnloaded(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier);

    /**
     * Defines the behaviour of the {@link #canTake(CrispyMenu, Player)} function.
     * <p>
     * The {@code supplier} {@link BiFunction} will be used to evaluate if the item can be interacted with or
     * removed by the player based on the context of the {@link CrispyMenu} and the {@link Player}. This allows
     * for dynamic control over the item's accessibility.
     * </p>
     *
     * @param supplier a {@link BiFunction} that returns {@code true} if the item can be taken by the player in
     *                 the given menu, or {@code false} otherwise. Must not be {@code null}.
     * @return the current {@link MenuItem} instance with the updated access control logic.
     * @throws NullPointerException if {@code supplier} is {@code null}.
     */
    @NotNull MenuItem setCanTake(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier);

    /**
     * Checks if the item's primary display is currently loaded.
     *
     * @return {@code true} if the item's primary display is loaded; {@code false} otherwise.
     */
    boolean isLoaded();

    /**
     * Checks if the item's alternative display is currently loaded.
     *
     * @return {@code true} if the item's alternative display is loaded; {@code false} otherwise.
     */
    boolean isAlternativeLoaded();

    /**
     * Retrieves the primary display element for the item based on its current state.
     * <p>
     * This method returns the {@link ItemElement} representing the item's primary display. If the item is loaded, it returns
     * the primary display element for the loaded state. If the item is not loaded, it returns the display element for the
     * unloaded state.
     * </p>
     *
     * @return the {@link ItemElement} representing the item's current primary display based on its loaded or unloaded state.
     */
    @NotNull ItemElement<?> getDisplay();

    /**
     * Retrieves the alternative display element for the item based on its current state.
     * <p>
     * This method returns the {@link ItemElement} representing the item's alternative display. If the item is loaded, it returns
     * the alternative display element for the loaded state. If the item is not loaded, it returns the display element for the
     * unloaded state.
     * </p>
     *
     * @return the {@link ItemElement} representing the item's current alternative display based on its loaded or unloaded state.
     */
    @NotNull ItemElement<?> getAlternativeDisplay();

    /**
     * Sets the alternative display element for the item when it is loaded.
     *
     * @param element the {@link ItemElement} to set as the alternative display for the loaded state.
     * @throws NullPointerException if {@code element} is {@code null}.
     */
    void setAlternativeDisplay(@NotNull ItemElement<?> element);

    /**
     * Sets the primary display element for the item when it is loaded.
     *
     * @param element the {@link ItemElement} to set as the display for the loaded state.
     * @throws NullPointerException if {@code element} is {@code null}.
     */
    void setLoadedDisplay(@NotNull ItemElement<?> element);

    /**
     * Sets the display element for the item when it is in a loading state.
     *
     * @param element the {@link ItemElement} to set as the display for the loading state.
     * @throws NullPointerException if {@code element} is {@code null}.
     */
    void setLoadingDisplay(@NotNull ItemElement<?> element);
}
