package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.items.LoadedItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.items.LoadingItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface MenuItem {

    static LoadedItem loadedItem(ItemElement display, BiFunction<MenuData, Player, Void> onClick) {
        return new LoadedItem(display) {
            @Override
            public void onClick(MenuData data, Player player) {
                onClick.apply(data, player);
            }
        };
    }

    static LoadingItem loadingItem(ItemElement display, BiFunction<MenuData, Player, Void> onClick, BiFunction<MenuData, Player, Void> onClickUnloaded, Supplier<ItemElement> elementSupplier) {
        return new LoadingItem(display) {
            @Override
            public void onClickUnloaded(MenuData data, Player player) {
                onClickUnloaded.apply(data, player);
            }

            @Override
            public void onClick(MenuData data, Player player) {
                onClick.apply(data, player);
            }

            @Override
            public ItemElement loadItem() {
                return elementSupplier.get();
            }
        };
    }

    void load(Runnable onLoad);
    void onClick(MenuData data, Player player);
    void onClickUnloaded(MenuData data, Player player);
    boolean canSee(MenuData data, Player player);
    boolean canSeeUnloaded(MenuData data, Player player);
    boolean canTake(MenuData data, Player player);
    MenuItem setCanSee(BiFunction<MenuData, Player, Boolean> supplier);
    MenuItem setCanSeeUnloaded(BiFunction<MenuData, Player, Boolean> supplier);
    MenuItem setCanTake(BiFunction<MenuData, Player, Boolean> supplier);
    boolean isLoaded();
    ItemElement getDisplay();
    void setLoadedDisplay(ItemElement element);
    void setLoadingDisplay(ItemElement element);
}
