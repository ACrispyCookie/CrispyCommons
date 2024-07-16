package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.items.LoadedItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.items.LoadingItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface MenuItem {

    static LoadedItem loadedItem(ItemElement<?> display, ItemElement<?> altDisplay, BiFunction<MenuData, Player, Void> onClick) {
        return new LoadedItem(display, altDisplay) {
            @Override
            public void onClick(MenuData data, Player player) {
                onClick.apply(data, player);
            }
        };
    }

    static LoadedItem loadedItem(ItemElement<?> display, BiFunction<MenuData, Player, Void> onClick) {
        return new LoadedItem(display, ItemElement.simple(new CrispyItemStack(Material.AIR))) {
            @Override
            public void onClick(MenuData data, Player player) {
                onClick.apply(data, player);
            }
        };
    }

    static LoadingItem loadingItem(ItemElement<?> display, BiFunction<MenuData, Player, Void> onClick,
                                   BiFunction<MenuData, Player, Void> onClickUnloaded, Supplier<ItemElement<?>> elementSupplier, Supplier<ItemElement<?>> altDisplaySupplier) {
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
            public ItemElement<?> loadItem() {
                return elementSupplier.get();
            }

            @Override
            public ItemElement<?> loadAlternativeItem() {
                return altDisplaySupplier.get();
            }
        };
    }

    static LoadingItem loadingItem(ItemElement<?> display, BiFunction<MenuData, Player, Void> onClick, BiFunction<MenuData, Player, Void> onClickUnloaded, Supplier<ItemElement<?>> elementSupplier) {
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
            public ItemElement<?> loadItem() {
                return elementSupplier.get();
            }

            @Override
            public ItemElement<?> loadAlternativeItem() {
                return ItemElement.simple(new CrispyItemStack(Material.AIR));
            }
        };
    }

    void load(Runnable onLoad);
    void loadAlternative(Runnable onLoad);
    void onClick(MenuData data, Player player);
    void onClickUnloaded(MenuData data, Player player);
    boolean canSee(MenuData data, Player player);
    boolean canSeeUnloaded(MenuData data, Player player);
    boolean canTake(MenuData data, Player player);
    MenuItem setCanSee(BiFunction<MenuData, Player, Boolean> supplier);
    MenuItem setCanSeeUnloaded(BiFunction<MenuData, Player, Boolean> supplier);
    MenuItem setCanTake(BiFunction<MenuData, Player, Boolean> supplier);
    boolean isLoaded();
    boolean isAlternativeLoaded();
    ItemElement<?> getDisplay();
    ItemElement<?> getAlternativeDisplay();
    void setAlternativeDisplay(ItemElement<?> element);
    void setLoadedDisplay(ItemElement<?> element);
    void setLoadingDisplay(ItemElement<?> element);
}
