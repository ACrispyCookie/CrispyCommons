package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;

public interface CrispyInventory extends CrispyVisual {

    void setStaticPages();
    void setDynamicPages();

    enum InventoryType {
        STATIC_PAGES,
        DYNAMIC_PAGES
    }

}
