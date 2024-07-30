package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.ItemLoadData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public abstract class LoadingItem extends AbstractMenuItem {

    protected abstract ItemLoadData loadData();

    public LoadingItem(ItemElement<?> loadingDisplay) {
        super(null, loadingDisplay, null);
    }

    @Override
    public void load(@NotNull Runnable onLoad) {
        if(isLoaded)
            return;
        
        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            ItemLoadData data = loadData();
            setLoadedDisplay(data.getDisplay());
            setAlternativeDisplay(data.getAlternativeDisplay());
            setCanSee(data.getCanSee());
            this.isLoaded = true;
            onLoad.run();
        });
    }
}
