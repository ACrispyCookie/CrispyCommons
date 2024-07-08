package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalItemElement;
import org.bukkit.entity.Player;

public abstract class LoadedItem extends AbstractMenuItem {

    public LoadedItem(ItemElement display) {
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
