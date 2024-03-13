package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractInventory;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;

public interface CrispyInventory extends CrispyVisual {

    static InventoryBuilder builder() {
        return new InventoryBuilder();
    }

    void changePage(Player player, int newIndex);
    InventoryPage getCurrentPage(Player p);
    InventoryPage getPage(int index);
    List<InventoryPage> getPages();
    void addPage(int index, InventoryPage page);
    void addPage(InventoryPage page);
    void setPage(int index, InventoryPage page);
    void removePage(int index);
    void forEachPage(Consumer<InventoryPage> consumer);

    class InventoryBuilder extends AbstractVisualBuilder<CrispyInventory> {

        private final InventoryData data = new InventoryData(0);

        public InventoryBuilder setStartingPage(int pageIndex) {
            data.setStartingPage(pageIndex);
            return this;
        }

        public InventoryBuilder addEmptyPage(int index, String title, int rows) {
            data.addPage(index, new AbstractPage(title, rows));
            return this;
        }

        public InventoryBuilder setItem(int pageIndex, int slot, InventoryItem<?> item) {
            if (pageIndex < 0 || pageIndex > data.getTotalPages() - 1)
                return this;

            data.getPages().get(pageIndex).setItem(slot, item);
            return this;
        }

        public InventoryBuilder setItem(int pageIndex, int x, int y, InventoryItem<?> item) {
            return setItem(pageIndex, y * 9 + x, item);
        }

        @Override
        public CrispyInventory build() {
            toBuild = new AbstractInventory(data, receivers, timeToLive);
            return toBuild;
        }
    }

}
