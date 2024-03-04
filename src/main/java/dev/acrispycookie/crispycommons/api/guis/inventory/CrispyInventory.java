package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractInventory;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.function.Consumer;

public interface CrispyInventory extends CrispyVisual, Listener {

    static InventoryBuilder builder() {
        return new InventoryBuilder();
    }

    InventoryPage getPage(int index);
    List<InventoryPage> getPages();
    void addPage(InventoryPage page, int index);
    void addPage(InventoryPage page);
    void removePage(int index);
    void consumePages(Consumer<InventoryPage> consumer);

    class InventoryBuilder extends AbstractVisualBuilder<CrispyInventory> {

        private final InventoryData data = new InventoryData(0);

        public InventoryBuilder setStartingPage(int pageIndex) {
            data.setCurrentPage(pageIndex);
            return this;
        }

        public InventoryBuilder addPage(InventoryPage page) {
            data.addPage(page);
            return this;
        }

        public InventoryBuilder addPage(int index, InventoryPage page) {
            data.addPage(index, page);
            return this;
        }

        public InventoryBuilder setPage(int index, InventoryPage page) {
            data.addPage(index, page);
            return this;
        }

        @Override
        public CrispyInventory build() {
            toBuild = new AbstractInventory(data, receivers, timeToLive);
            return toBuild;
        }
    }

}
