package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.implementations.guis.inventory.items.StaticItem;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

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
    boolean canSee(InventoryPage page);
    boolean canSeeUnloaded(InventoryPage page);
    boolean canTake(InventoryPage page);
    void canSee(Function<InventoryPage, Boolean> supplier);
    void canSeeUnloaded(Function<InventoryPage, Boolean> supplier);
    void canTake(Function<InventoryPage, Boolean> supplier);
    boolean isLoaded();
    ItemElement getDisplay();
    ItemElement getLoadingDisplay();
    void setDisplay(ItemElement element);
    void setLoadingDisplay(ItemElement element);

}
