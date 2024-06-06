package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.implementations.guis.menu.items.StaticItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public interface MenuItem {

    static StaticItem staticItem(ItemElement display, BiFunction<MenuData, Player, Void> onClick) {
        return new StaticItem(display) {
            @Override
            public void onClick(MenuData data, Player player) {
                onClick.apply(data, player);
            }
        };
    }

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
