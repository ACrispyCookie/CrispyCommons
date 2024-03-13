package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public abstract class AbstractItem<T> implements InventoryItem<T> {

    protected boolean isLoaded = false;
    private BiFunction<InventoryPage, Player, Boolean> canSee;
    private BiFunction<InventoryPage, Player, Boolean> canSeeUnloaded;
    private BiFunction<InventoryPage, Player, Boolean> canTake;
    private ItemElement display;
    private ItemElement loadingDisplay;

    public AbstractItem(ItemElement display, ItemElement unloadedDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.canSee = (page, player) -> true;
        this.canSeeUnloaded = (page, player) -> true;
        this.canTake = (page, player) -> false;
    }

    @Override
    public boolean canSee( InventoryPage page, Player player) {
        return canSee.apply(page, player);
    }

    @Override
    public boolean canSeeUnloaded(InventoryPage page, Player player) {
        return canSeeUnloaded.apply(page, player);
    }

    @Override
    public boolean canTake(InventoryPage page, Player player) {
        return canTake.apply(page, player);
    }

    @Override
    public InventoryItem<T> setCanSee(BiFunction<InventoryPage, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public InventoryItem<T> setCanSeeUnloaded(BiFunction<InventoryPage, Player, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
        return this;
    }

    @Override
    public InventoryItem<T> setCanTake(BiFunction<InventoryPage, Player, Boolean> supplier) {
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
