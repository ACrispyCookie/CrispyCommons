package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public abstract class LoadingItem extends AbstractMenuItem {

    protected abstract ItemElement<?> loadItem();
    protected abstract ItemElement<?> loadAlternativeItem();

    public LoadingItem(ItemElement<?> loadingDisplay) {
        super(null, loadingDisplay, null);
    }

    @Override
    public void load(@NotNull Runnable onLoad) {
        if(isLoaded)
            return;
        
        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            setLoadedDisplay(loadItem());
            this.isLoaded = true;
            onLoad.run();
        });
    }

    @Override
    public void loadAlternative(@NotNull Runnable onLoad) {
        if(isAlternativeLoaded)
            return;

        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            setAlternativeDisplay(loadAlternativeItem());
            this.isAlternativeLoaded = true;
            onLoad.run();
        });
    }
}
