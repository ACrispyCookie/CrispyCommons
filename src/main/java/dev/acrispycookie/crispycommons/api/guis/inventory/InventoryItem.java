package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public interface InventoryItem<T> {

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
    void setLoadedDisplay(ItemElement element);
    void setLoadingDisplay(ItemElement element);

}
