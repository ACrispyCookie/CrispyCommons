package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractSection implements Section {

    protected final HashMap<ItemElement<?>, Set<InventoryInfo>> dynamicItems = new HashMap<>();
    protected final HashMap<Inventory, Set<ItemElement<?>>> dynamicItemInventories = new HashMap<>();

    protected void updateItem(ItemElement<?> item) {
        Set<InventoryInfo> inventories = dynamicItems.get(item);

        inventories.forEach((info) -> info.getInventory().setItem(info.getPasteSlot(), item.getFromContext(OfflinePlayer.class, info.getPlayer())));
    }

    @Override
    public void renderItem(Player player, CrispyMenu menu, Inventory toRender, int pasteSlot, int startingIndex) {
        if (!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        renderValidItem(player, menu, toRender, pasteSlot, startingIndex);
    }

    public void onClose(Inventory inventory) {
        if (!dynamicItemInventories.containsKey(inventory))
            return;

        dynamicItemInventories.get(inventory).forEach(item -> {
            Set<InventoryInfo> inventories = dynamicItems.get(item).stream()
                    .filter((i) -> !i.getInventory().equals(inventory))
                    .collect(Collectors.toSet());
            dynamicItems.put(item, inventories);

            if(inventories.isEmpty()) {
                item.stop();
                dynamicItems.remove(item);
            }
        });
    }

    protected void renderValidItem(Player player, CrispyMenu menu, Inventory toRender, int pasteSlot, int startingIndex) {
        MenuItem item = getItem(startingIndex);
        if (item == null)
            return;
        if (!item.canSee(menu, player)) {
            item.loadAlternative(() -> renderValidItem(player, menu, toRender, pasteSlot, startingIndex));
            toRender.setItem(pasteSlot, item.getAlternativeDisplay().getFromContext(OfflinePlayer.class, player));
            return;
        }

        if (item.getDisplay().isDynamic()) {
            addDynamicItem(item, player, toRender, pasteSlot);
        }

        item.load(() -> renderValidItem(player, menu, toRender, pasteSlot, startingIndex));
        toRender.setItem(pasteSlot, item.getDisplay().getFromContext(OfflinePlayer.class, player));
    }

    protected void addDynamicItem(MenuItem item, Player player, Inventory toRender, int pasteSlot) {
        boolean started = dynamicItems.containsKey(item.getDisplay());
        Set<InventoryInfo> elements = started ? dynamicItems.get(item.getDisplay()) : new HashSet<>();
        elements.add(new InventoryInfo(player, toRender, pasteSlot));
        dynamicItems.put(item.getDisplay(), elements);

        Set<ItemElement<?>> items = dynamicItemInventories.getOrDefault(toRender, new HashSet<>());
        items.add(item.getDisplay());
        dynamicItemInventories.put(toRender, items);

        if(!started) {
            item.getDisplay().setUpdate(() -> updateItem(item.getDisplay()));
            item.getDisplay().start();
        }
    }

    protected static class InventoryInfo {
        private final OfflinePlayer player;
        private final Inventory inventory;
        private final int pasteSlot;

        public InventoryInfo(OfflinePlayer player, Inventory inventory, int pasteSlot) {
            this.player = player;
            this.inventory = inventory;
            this.pasteSlot = pasteSlot;
        }

        public OfflinePlayer getPlayer() {
            return player;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public int getPasteSlot() {
            return pasteSlot;
        }
    }
}
