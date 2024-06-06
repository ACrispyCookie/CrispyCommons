package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.entity.Player;

public abstract class StaticItem extends AbstractMenuItem {

    public StaticItem(ItemElement display) {
        super(display, display);
        this.isLoaded = true;
    }

    @Override
    public void onClickUnloaded(MenuData data, Player player) {

    }
}
