package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;


/**
 * An abstract base class for menu items within the CrispyMenu system.
 * <p>
 * The {@code AbstractMenuItem} class provides the foundational implementation for {@link MenuItem}
 * objects, allowing them to be displayed, clicked, and conditionally interacted with. It supports
 * different displays depending on the loaded state of the item, as well as custom behavior for
 * visibility, interaction, and click handling.
 * </p>
 */
public abstract class AbstractMenuItem implements MenuItem {

    /**
     * Indicates whether the item is loaded.
     */
    protected boolean isLoaded = false;

    /**
     * The consumer to execute when the item is clicked.
     */
    private BiConsumer<CrispyMenu, Player> onClick;

    /**
     * The consumer to execute when the item is clicked while it is unloaded.
     */
    private BiConsumer<CrispyMenu, Player> onClickUnloaded;

    /**
     * A function that determines whether the player can see the item.
     */
    private BiFunction<CrispyMenu, Player, Boolean> canSee;

    /**
     * A function that determines whether the player can take the item.
     */
    private BiFunction<CrispyMenu, Player, Boolean> canTake;

    /**
     * The item element to display when the item is loaded.
     */
    private ItemElement<?> display;

    /**
     * The alternative item element to display when the item is loaded.
     * <p>
     *     This element is displayed instead of the main {@code display} element
     *     when the {@link #canSee(CrispyMenu, Player)} returns {@code false} for a player.
     * </p>
     */
    private ItemElement<?> alternativeDisplay;

    /**
     * The item element to display when the item is loading.
     */
    private ItemElement<?> loadingDisplay;

    /**
     * Constructs an {@code AbstractMenuItem} with the specified display elements.
     *
     * @param display            the main display element for when the item is loaded.
     * @param unloadedDisplay    the display element to show while the item is loading.
     * @param alternativeDisplay the alternative display element for when the item is loaded.
     *
     * @throws NullPointerException if any of the display elements are {@code null}.
     */
    AbstractMenuItem(@NotNull ItemElement<?> display, @NotNull ItemElement<?> unloadedDisplay, @NotNull ItemElement<?> alternativeDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.alternativeDisplay = alternativeDisplay;
        this.canSee = (menu, player) -> true;
        this.canTake = (menu, player) -> false;
        this.onClick = (menu, player) -> {};
        this.onClickUnloaded = (menu, player) -> {};
    }

    /**
     * Determines whether the player can see this menu item.
     *
     * @param menu   the menu containing this item.
     * @param player the player interacting with the menu.
     * @return {@code true} if the player can see the item, {@code false} otherwise.
     */
    @Override
    public boolean canSee(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canSee.apply(menu, player);
    }

    /**
     * Determines whether the player can take this menu item.
     *
     * @param menu   the menu containing this item.
     * @param player the player interacting with the menu.
     * @return {@code true} if the player can take the item, {@code false} otherwise.
     */
    @Override
    public boolean canTake(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canTake.apply(menu, player);
    }

    /**
     * Handles the event when the item is clicked.
     *
     * @param menu   the menu containing this item.
     * @param player the player who clicked the item.
     */
    @Override
    public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
        onClick.accept(menu, player);
    }

    /**
     * Handles the event when the item is clicked while it is unloaded.
     *
     * @param menu   the menu containing this item.
     * @param player the player who clicked the item.
     */
    @Override
    public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
        onClickUnloaded.accept(menu, player);
    }

    /**
     * Sets the function that determines whether the player can see this item.
     *
     * @param supplier the function to determine item visibility.
     * @return this {@code MenuItem} instance, for method chaining.
     */
    @Override
    public @NotNull MenuItem setCanSee(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    /**
     * Sets the function that determines whether the player can take this item.
     *
     * @param supplier the function to determine item availability.
     * @return this {@code MenuItem} instance, for method chaining.
     */
    @Override
    public @NotNull MenuItem setCanTake(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canTake = supplier;
        return this;
    }

    /**
     * Sets the consumer to execute when the item is clicked.
     *
     * @param consumer the click handler consumer.
     * @return this {@code MenuItem} instance, for method chaining.
     */
    @Override
    public @NotNull MenuItem setOnClick(@NotNull BiConsumer<CrispyMenu, Player> consumer) {
        this.onClick = consumer;
        return this;
    }

    /**
     * Sets the consumer to execute when the item is clicked while it is unloaded.
     *
     * @param consumer the click handler function for unloaded state.
     * @return this {@code MenuItem} instance, for method chaining.
     */
    @Override
    public @NotNull MenuItem setOnClickUnloaded(@NotNull BiConsumer<CrispyMenu, Player> consumer) {
        this.onClickUnloaded = consumer;
        return this;
    }

    /**
     * Checks whether the item is fully loaded.
     *
     * @return {@code true} if the item is loaded, {@code false} otherwise.
     */
    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Returns the display element for this item.
     * <p>
     * If the item is loaded, it returns the loaded display; otherwise, it returns the loading display.
     * </p>
     *
     * @return the display element.
     */
    @Override
    public @NotNull ItemElement<?> getDisplay() {
        return isLoaded ? display : loadingDisplay;
    }

    /**
     * Returns the alternative display element for this item.
     * <p>
     * If the item is loaded, it returns the alternative display; otherwise, it returns the loading display.
     * </p>
     *
     * @return the alternative display element.
     */
    @Override
    public @NotNull ItemElement<?> getAlternativeDisplay() {
        return isLoaded ? alternativeDisplay : loadingDisplay;
    }

    /**
     * Sets the alternative display element for this item.
     *
     * @param element the new alternative display element.
     */
    @Override
    public void setAlternativeDisplay(@NotNull ItemElement<?> element) {
        this.alternativeDisplay = element;
    }

    /**
     * Sets the display element for this item when it is loaded.
     *
     * @param element the new loaded display element.
     */
    @Override
    public void setLoadedDisplay(@NotNull ItemElement<?> element) {
        this.display = element;
    }

    /**
     * Sets the display element for this item when it is loading.
     *
     * @param element the new loading display element.
     */
    @Override
    public void setLoadingDisplay(@NotNull ItemElement<?> element) {
        this.loadingDisplay = element;
    }
}

