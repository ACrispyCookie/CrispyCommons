package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;

public abstract class LoadedItem extends AbstractMenuItem {

    public LoadedItem(ItemElement<?> display, ItemElement<?> alternativeDisplay) {
        super(display, display, alternativeDisplay);
    }

    @Override
    public void onClickUnloaded(MenuData data, Player player) {

    }

    @Override
    public void load(Runnable onLoad) {
        this.isLoaded = true;
    }

    @Override
    public void loadAlternative(Runnable onLoad) {
        this.isAlternativeLoaded = true;
    }
}
