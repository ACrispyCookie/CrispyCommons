package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalItemElement;
import org.bukkit.entity.Player;

public abstract class LoadedItem extends AbstractMenuItem {

    public LoadedItem(GlobalItemElement display) {
        super(display, display);
    }

    @Override
    public void onClickUnloaded(MenuData data, Player player) {

    }

    @Override
    public void load(Runnable onLoad) {
        this.isLoaded = true;
    }
}
