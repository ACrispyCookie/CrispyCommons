package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.Bukkit;

public abstract class LoadingItem extends AbstractMenuItem {

    protected abstract ItemElement<?> loadItem();
    protected abstract ItemElement<?> loadAlternativeItem();

    public LoadingItem(ItemElement<?> loadingDisplay) {
        super(null, loadingDisplay, null);
    }

    @Override
    public void load(Runnable onLoad) {
        if(isLoaded)
            return;
        
        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            setLoadedDisplay(loadItem());
            this.isLoaded = true;
            onLoad.run();
        });
    }

    @Override
    public void loadAlternative(Runnable onLoad) {
        if(isAlternativeLoaded)
            return;

        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            setAlternativeDisplay(loadAlternativeItem());
            this.isAlternativeLoaded = true;
            onLoad.run();
        });
    }
}
