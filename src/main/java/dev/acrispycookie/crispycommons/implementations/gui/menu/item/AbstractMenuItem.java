package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;


public abstract class AbstractMenuItem implements MenuItem {

    protected boolean isLoaded = false;
    protected boolean isAlternativeLoaded = false;
    private BiFunction<CrispyMenu, Player, Boolean> canSee;
    private BiFunction<CrispyMenu, Player, Boolean> canSeeUnloaded;
    private BiFunction<CrispyMenu, Player, Boolean> canTake;
    private ItemElement<?> display;
    private ItemElement<?> alternativeDisplay;
    private ItemElement<?> loadingDisplay;

    AbstractMenuItem(ItemElement<?> display, ItemElement<?> unloadedDisplay, ItemElement<?> alternativeDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.alternativeDisplay = alternativeDisplay;
        this.canSee = (page, player) -> true;
        this.canSeeUnloaded = (page, player) -> true;
        this.canTake = (page, player) -> false;
    }

    @Override
    public boolean canSee(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canSee.apply(menu, player);
    }

    @Override
    public boolean canSeeUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canSeeUnloaded.apply(menu, player);
    }

    @Override
    public boolean canTake(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canTake.apply(menu, player);
    }

    @Override
    public @NotNull MenuItem setCanSee(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public @NotNull MenuItem setCanSeeUnloaded(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
        return this;
    }

    @Override
    public @NotNull MenuItem setCanTake(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canTake = supplier;
        return this;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public boolean isAlternativeLoaded() {
        return isAlternativeLoaded;
    }

    @Override
    public @NotNull ItemElement<?> getDisplay() {
        return isLoaded ? display : loadingDisplay;
    }

    @Override
    public @NotNull ItemElement<?> getAlternativeDisplay() {
        return isAlternativeLoaded ? alternativeDisplay : loadingDisplay;
    }

    @Override
    public void setAlternativeDisplay(@NotNull ItemElement<?> element) {
        this.alternativeDisplay = element;
    }

    @Override
    public void setLoadedDisplay(@NotNull ItemElement<?> element) {
        this.display = element;
    }

    @Override
    public void setLoadingDisplay(@NotNull ItemElement<?> element) {
        this.loadingDisplay = element;
    }
}
