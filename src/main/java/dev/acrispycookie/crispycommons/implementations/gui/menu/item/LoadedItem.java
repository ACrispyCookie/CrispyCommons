package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class LoadedItem extends AbstractMenuItem {

    public LoadedItem(ItemElement<?> display, ItemElement<?> alternativeDisplay) {
        super(display, display, alternativeDisplay);
    }

    @Override
    public void onClickUnloaded(@NotNull CrispyMenu menu, @NotNull Player player) {

    }

    @Override
    public void load(@NotNull Runnable onLoad) {
        this.isLoaded = true;
    }
}
