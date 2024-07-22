package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.entity.Player;

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
    public boolean canSee(CrispyMenu menu, Player player) {
        return canSee.apply(menu, player);
    }

    @Override
    public boolean canSeeUnloaded(CrispyMenu menu, Player player) {
        return canSeeUnloaded.apply(menu, player);
    }

    @Override
    public boolean canTake(CrispyMenu menu, Player player) {
        return canTake.apply(menu, player);
    }

    @Override
    public MenuItem setCanSee(BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public MenuItem setCanSeeUnloaded(BiFunction<CrispyMenu, Player, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
        return this;
    }

    @Override
    public MenuItem setCanTake(BiFunction<CrispyMenu, Player, Boolean> supplier) {
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
    public ItemElement<?> getDisplay() {
        return isLoaded ? display : loadingDisplay;
    }

    @Override
    public ItemElement<?> getAlternativeDisplay() {
        return isAlternativeLoaded ? alternativeDisplay : loadingDisplay;
    }

    @Override
    public void setAlternativeDisplay(ItemElement<?> element) {
        this.alternativeDisplay = element;
    }

    @Override
    public void setLoadedDisplay(ItemElement<?> element) {
        this.display = element;
    }

    @Override
    public void setLoadingDisplay(ItemElement<?> element) {
        this.loadingDisplay = element;
    }
}
