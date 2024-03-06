package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.implementations.guis.inventory.items.StaticItem;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public interface InventoryItem<T> {

    static InventoryItem<?> staticItem(ItemElement display) {
        return new StaticItem(display) {
            @Override
            public void onClick(InventoryPage page, Player player) {

            }
        };
    }

    static InventoryItem<?> staticItem(ItemElement display, BiFunction<InventoryPage, Player, Void> onClick) {
        return new StaticItem(display) {
            @Override
            public void onClick(InventoryPage page, Player player) {
                onClick.apply(page, player);
            }
        };
    }

    void onClick(InventoryPage page, Player p);
    void onClickUnloaded(InventoryPage page, Player p);
    boolean canSee(InventoryPage page, Player p);
    boolean canSeeUnloaded(InventoryPage page, Player p);
    boolean canTake(InventoryPage page, Player p);
    InventoryItem<T> setCanSee(BiFunction<InventoryPage, Player, Boolean> supplier);
    InventoryItem<T> setCanSeeUnloaded(BiFunction<InventoryPage, Player, Boolean> supplier);
    InventoryItem<T> setCanTake(BiFunction<InventoryPage, Player, Boolean> supplier);
    boolean isLoaded();
    ItemElement getDisplay();
    ItemElement getLoadingDisplay();
    void setDisplay(ItemElement element);
    void setLoadingDisplay(ItemElement element);

}
