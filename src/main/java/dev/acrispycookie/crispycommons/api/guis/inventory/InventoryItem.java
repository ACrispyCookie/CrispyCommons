package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.implementations.guis.inventory.items.StaticItem;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.utility.inventories.Function3P;
import org.bukkit.entity.Player;

public interface InventoryItem<T> {

    static InventoryItem<?> staticItem(ItemElement display) {
        return new StaticItem(display) {
            @Override
            public void onClick(CrispyInventory inventory, InventoryPage page, Player player) {

            }
        };
    }

    static InventoryItem<?> staticItem(ItemElement display, Function3P<CrispyInventory, InventoryPage, Player, Void> onClick) {
        return new StaticItem(display) {
            @Override
            public void onClick(CrispyInventory inventory, InventoryPage page, Player player) {
                onClick.apply(inventory, page, player);
            }
        };
    }

    void onClick(CrispyInventory inventory, InventoryPage page, Player p);
    void onClickUnloaded(CrispyInventory inventory, InventoryPage page, Player p);
    boolean canSee(CrispyInventory inventory, InventoryPage page, Player p);
    boolean canSeeUnloaded(CrispyInventory inventory, InventoryPage page, Player p);
    boolean canTake(CrispyInventory inventory, InventoryPage page, Player p);
    InventoryItem<T> setCanSee(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier);
    InventoryItem<T> setCanSeeUnloaded(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier);
    InventoryItem<T> setCanTake(Function3P<CrispyInventory, InventoryPage, Player, Boolean> supplier);
    boolean isLoaded();
    ItemElement getDisplay();
    void setLoadedDisplay(ItemElement element);
    void setLoadingDisplay(ItemElement element);

}
