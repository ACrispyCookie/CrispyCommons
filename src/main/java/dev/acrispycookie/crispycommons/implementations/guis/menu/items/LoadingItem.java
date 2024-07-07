package dev.acrispycookie.crispycommons.implementations.guis.menu.items;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalItemElement;
import org.bukkit.Bukkit;

public abstract class LoadingItem extends AbstractMenuItem {

    protected abstract GlobalItemElement loadItem();

    public LoadingItem(GlobalItemElement loadingDisplay) {
        super(null, loadingDisplay);
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
}
