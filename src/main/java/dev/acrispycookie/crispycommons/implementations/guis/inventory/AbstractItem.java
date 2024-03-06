package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.utility.inventories.Function3P;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractItem<T> implements InventoryItem<T> {

    protected boolean isLoaded = false;
    private Function3P<CrispyInventory, InventoryPage, Player, Boolean> canSee;
    private Function3P<CrispyInventory, InventoryPage, Player, Boolean> canSeeUnloaded;
    private Function3P<CrispyInventory, InventoryPage, Player, Boolean> canTake;
    private ItemElement display;
    private ItemElement loadingDisplay;

    public AbstractItem(ItemElement display, ItemElement unloadedDisplay) {
        this.display = display;
        this.loadingDisplay = unloadedDisplay;
        this.canSee = (inv, page, player) -> true;
        this.canSeeUnloaded = (inv, page, player) -> true;
        this.canTake = (inv, page, player) -> false;
    }

    @Override
    public boolean canSee(CrispyInventory inventory, InventoryPage page, Player player) {
        return canSee.apply(inventory, page, player);
    }

    @Override
    public boolean canSeeUnloaded(CrispyInventory inventory, InventoryPage page, Player player) {
        return canSeeUnloaded.apply(inventory, page, player);
    }

    @Override
    public boolean canTake(CrispyInventory inventory, InventoryPage page, Player player) {
        return canTake.apply(inventory, page, player);
    }

    @Override
    public InventoryItem<T> setCanSee(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier) {
        this.canSee = supplier;
        return this;
    }

    @Override
    public InventoryItem<T> setCanSeeUnloaded(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier) {
        this.canSeeUnloaded = supplier;
        return this;
    }

    @Override
    public InventoryItem<T> setCanTake(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier) {
        this.canTake = supplier;
        return this;
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
        this.display = element;

    }

    @Override
    public void setLoadingDisplay(ItemElement element) {
        this.loadingDisplay = element;
    }
}
