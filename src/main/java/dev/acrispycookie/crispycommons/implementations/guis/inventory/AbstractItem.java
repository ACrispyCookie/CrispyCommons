package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractItem<T> implements InventoryItem<T> {

    protected boolean isLoaded = false;
    private Function<InventoryPage, Boolean> canSee;
    private Function<InventoryPage, Boolean> canSeeUnloaded;
    private Function<InventoryPage, Boolean> canTake;
    private ItemElement display;
    private ItemElement loadingDisplay;

    public AbstractItem(ItemElement display, ItemElement unloadedDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.canSee = (page) -> true;
        this.canSeeUnloaded = (page) -> true;
        this.canTake = (page) -> false;
    }

    @Override
    public boolean canSee(InventoryPage page) {
        return canSee.apply(page);
    }

    @Override
    public boolean canSeeUnloaded(InventoryPage page) {
        return canSeeUnloaded.apply(page);
    }

    @Override
    public boolean canTake(InventoryPage page) {
        return canTake.apply(page);
    }

    @Override
    public void canSee(Function<InventoryPage, Boolean> supplier) {
        this.canSee = supplier;
    }

    @Override
    public void canSeeUnloaded(Function<InventoryPage, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
    }

    @Override
    public void canTake(Function<InventoryPage, Boolean> supplier) {
        this.canTake = supplier;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public ItemElement getDisplay() {
        return display;
    }

    @Override
    public ItemElement getLoadingDisplay() {
        return loadingDisplay;
    }

    @Override
    public void setDisplay(ItemElement element) {

    }

    @Override
    public void setLoadingDisplay(ItemElement element) {

    }
}
