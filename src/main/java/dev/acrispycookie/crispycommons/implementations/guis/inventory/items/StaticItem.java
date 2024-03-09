package dev.acrispycookie.crispycommons.implementations.guis.inventory.items;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractItem;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import org.bukkit.entity.Player;

public abstract class StaticItem extends AbstractItem<Object> {

    public StaticItem(ItemElement display) {
        super(display, display);
        this.isLoaded = true;
    }

    @Override
    public void onClickUnloaded(InventoryPage page, Player player) {

    }
}
