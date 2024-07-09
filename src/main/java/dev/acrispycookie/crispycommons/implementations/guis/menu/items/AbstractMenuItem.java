package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public abstract class AbstractMenuItem implements MenuItem {

    protected boolean isLoaded = false;
    private BiFunction<MenuData, Player, Boolean> canSee;
    private BiFunction<MenuData, Player, Boolean> canSeeUnloaded;
    private BiFunction<MenuData, Player, Boolean> canTake;
    private ItemElement display;
    private ItemElement loadingDisplay;

    AbstractMenuItem(ItemElement display, ItemElement unloadedDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.canSee = (page, player) -> true;
        this.canSeeUnloaded = (page, player) -> true;
        this.canTake = (page, player) -> false;
    }

    @Override
    public boolean canSee(MenuData data, Player player) {
        return canSee.apply(data, player);
    }

    @Override
    public boolean canSeeUnloaded(MenuData data, Player player) {
        return canSeeUnloaded.apply(data, player);
    }

    @Override
    public boolean canTake(MenuData data, Player player) {
        return canTake.apply(data, player);
    }

    @Override
    public MenuItem setCanSee(BiFunction<MenuData, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public MenuItem setCanSeeUnloaded(BiFunction<MenuData, Player, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
        return this;
    }

    @Override
    public MenuItem setCanTake(BiFunction<MenuData, Player, Boolean> supplier) {
        this.canTake = supplier;
        return this;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public ItemElement getDisplay() {
        return isLoaded ? display : loadingDisplay;
    }

    @Override
    public void setLoadedDisplay(ItemElement element) {
        this.display = element;

    }

    @Override
    public void setLoadingDisplay(ItemElement element) {
        this.loadingDisplay = element;
    }
}
