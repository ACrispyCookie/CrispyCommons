package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;


public abstract class AbstractMenuItem implements MenuItem {

    protected boolean isLoaded = false;
    private BiFunction<CrispyMenu, Player, Void> onClick;
    private BiFunction<CrispyMenu, Player, Void> onClickUnloaded;
    private BiFunction<CrispyMenu, Player, Boolean> canSee;
    private BiFunction<CrispyMenu, Player, Boolean> canTake;
    private ItemElement<?> display;
    private ItemElement<?> alternativeDisplay;
    private ItemElement<?> loadingDisplay;

    AbstractMenuItem(ItemElement<?> display, ItemElement<?> unloadedDisplay, ItemElement<?> alternativeDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.alternativeDisplay = alternativeDisplay;
        this.canSee = (page, player) -> true;
        this.canTake = (page, player) -> false;
        this.onClick = (page, player) -> null;
        this.onClickUnloaded = (page, player) -> null;
    }

    @Override
    public boolean canSee(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canSee.apply(menu, player);
    }

    @Override
    public boolean canTake(@NotNull CrispyMenu menu, @NotNull Player player) {
        return canTake.apply(menu, player);
    }

    @Override
    public void onClick(@NotNull CrispyMenu menu, @NotNull Player player) {
        onClick.apply(menu, player);
    }

    @Override
    public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {
        onClickUnloaded.apply(menu, player);
    }

    @Override
    public @NotNull MenuItem setCanSee(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public @NotNull MenuItem setCanTake(@NotNull BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canTake = supplier;
        return this;
    }

    @Override
    public @NotNull MenuItem setOnClick(@NotNull BiFunction<CrispyMenu, Player, Void> function) {
        this.onClick = function;
        return this;
    }

    @Override
    public @NotNull MenuItem setOnClickUnloaded(@NotNull BiFunction<CrispyMenu, Player, Void> supplier) {
        this.onClickUnloaded = supplier;
        return this;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public @NotNull ItemElement<?> getDisplay() {
        return isLoaded ? display : loadingDisplay;
    }

    @Override
    public @NotNull ItemElement<?> getAlternativeDisplay() {
        return isLoaded ? alternativeDisplay : loadingDisplay;
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
