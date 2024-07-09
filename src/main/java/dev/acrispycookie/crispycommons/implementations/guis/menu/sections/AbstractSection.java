package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalItemElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalItemElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractSection implements Section {

    protected final HashMap<ItemElement, Set<InventoryInfo>> dynamicItems = new HashMap<>();
    protected final HashMap<Inventory, Set<ItemElement>> dynamicItemInventories = new HashMap<>();

    protected void updateItem(ItemElement item) {
        Set<InventoryInfo> inventories = dynamicItems.get(item);

        if (item instanceof GlobalItemElement)
            inventories.forEach((info) -> info.getInventory().setItem(info.getPasteSlot(), ((GlobalItemElement) item).getRaw()));
        else
            inventories.forEach((info) -> info.getInventory().setItem(info.getPasteSlot(), ((PersonalItemElement) item).getRaw(info.getPlayer())));
    }

    @Override
    public void renderItem(Player player, MenuData data, Inventory toRender, int pasteSlot, int startingIndex) {
        if (!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        renderValidItem(player, data, toRender, pasteSlot, startingIndex);
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

    protected void renderValidItem(Player player, MenuData data, Inventory toRender, int pasteSlot, int startingIndex) {
        MenuItem item = getItem(startingIndex);
        if (item == null)
            return;
        if (!item.canSee(data, player))
            return;

        if (item.getDisplay().isDynamic()) {
            addDynamicItem(item, player, toRender, pasteSlot);
        }

        item.load(() -> renderValidItem(player, data, toRender, pasteSlot, startingIndex));
        if (item.getDisplay() instanceof GlobalItemElement)
            toRender.setItem(pasteSlot, ((GlobalItemElement) item.getDisplay()).getRaw());
        else
            toRender.setItem(pasteSlot, ((PersonalItemElement) item.getDisplay()).getRaw(player));
    }

    protected void addDynamicItem(MenuItem item, Player player, Inventory toRender, int pasteSlot) {
        boolean started = dynamicItems.containsKey(item.getDisplay());
        Set<InventoryInfo> elements = started ? dynamicItems.get(item.getDisplay()) : new HashSet<>();
        elements.add(new InventoryInfo(player, toRender, pasteSlot));
        dynamicItems.put(item.getDisplay(), elements);

        Set<ItemElement> items = dynamicItemInventories.getOrDefault(toRender, new HashSet<>());
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
