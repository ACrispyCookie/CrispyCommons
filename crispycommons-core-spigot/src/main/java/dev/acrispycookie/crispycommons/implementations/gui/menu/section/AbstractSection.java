package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract base class for a section within a menu that supports dynamic items.
 * <p>
 * The {@code AbstractSection} class implements the {@link Section} interface and provides a framework
 * for rendering and updating dynamic items within a menu section. It manages the association of
 * {@link ItemElement}s with their corresponding inventories and handles their updates.
 * </p>
 */
public abstract class AbstractSection implements Section {

    /**
     * A map that associates dynamic {@link ItemElement}s with the set of {@link InventoryInfo} objects
     * representing the inventories where these items are displayed.
     */
    protected final HashMap<ItemElement<?>, Set<InventoryInfo>> dynamicItems = new HashMap<>();

    /**
     * A map that associates {@link Inventory} objects with the set of dynamic {@link ItemElement}s
     * they contain.
     */
    protected final HashMap<Inventory, Set<ItemElement<?>>> dynamicItemInventories = new HashMap<>();

    /**
     * Updates the specified dynamic item in all inventories where it is displayed.
     * <p>
     * This method retrieves all inventories containing the item and updates the item at its respective slot.
     * </p>
     *
     * @param item the {@link ItemElement} to be updated.
     */
    protected void updateItem(@NotNull ItemElement<?> item) {
        Set<InventoryInfo> inventories = dynamicItems.get(item);

        inventories.forEach((info) -> {
            info.getInventory().setItem(info.getPasteSlot(), item.getFromContext(OfflinePlayer.class, info.getPlayer()));

            Player player = info.getPlayer().getPlayer();
            if (player != null)
                player.updateInventory();
        });
    }

    /**
     * Renders an item into the specified inventory for the given player.
     * <p>
     * If the slot is not valid for either the section or the inventory, the method returns without rendering.
     * </p>
     *
     * @param player       the player for whom the item is being rendered.
     * @param menu         the menu that contains this section.
     * @param toRender     the inventory where the item will be rendered.
     * @param pasteSlot    the slot in the inventory where the item will be placed.
     * @param startingIndex the index of the item in the section to render.
     */
    @Override
    public void renderItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex) {
        if (!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        renderValidItem(player, menu, toRender, pasteSlot, startingIndex);
    }

    /**
     * Handles the closing of an inventory by removing dynamic items associated with it.
     * <p>
     * If the inventory contains any dynamic items, they are stopped and removed from tracking if no other
     * inventories contain the item.
     * </p>
     *
     * @param inventory the inventory that is being closed.
     */
    public void onClose(@NotNull Inventory inventory) {
        if (!dynamicItemInventories.containsKey(inventory))
            return;

        dynamicItemInventories.get(inventory).forEach(item -> {
            Set<InventoryInfo> inventories = dynamicItems.get(item).stream()
                    .filter((i) -> i.getInventory().equals(inventory))
                    .collect(Collectors.toSet());
            inventories.forEach((i) -> item.removeUpdateAction(i.getInventory(), i.getPlayer().getUniqueId() + String.valueOf(i.getPasteSlot())));
            Set<InventoryInfo> changed = dynamicItems.getOrDefault(item, new HashSet<>());
            changed.removeAll(inventories);
            dynamicItems.put(item, changed);

            if (inventories.isEmpty()) {
                dynamicItems.remove(item);
            }
        });
    }

    /**
     * Renders a valid item into the specified inventory for the given player.
     * <p>
     * If the item cannot be seen by the player, it is loaded and an alternative display is rendered.
     * If the item is dynamic, it is added to the list of dynamic items for continuous updates.
     * </p>
     *
     * @param player       the player for whom the item is being rendered.
     * @param menu         the menu that contains this section.
     * @param toRender     the inventory where the item will be rendered.
     * @param pasteSlot    the slot in the inventory where the item will be placed.
     * @param startingIndex the index of the item in the section to render.
     */
    protected void renderValidItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex) {
        if (!menu.isPlayerViewing(player))
            return;
        MenuItem item = getItem(startingIndex);
        if (!item.canSee(menu, player)) {
            item.load(() -> renderValidItem(player, menu, toRender, pasteSlot, startingIndex));
            toRender.setItem(pasteSlot, item.getAlternativeDisplay().getFromContext(OfflinePlayer.class, player));
            return;
        }

        if (item.getDisplay().isDynamic()) {
            addDynamicItem(item, player, toRender, pasteSlot);
        }

        item.load(() -> renderValidItem(player, menu, toRender, pasteSlot, startingIndex));
        toRender.setItem(pasteSlot, item.getDisplay().getFromContext(OfflinePlayer.class, player));
    }

    /**
     * Adds a dynamic item to the section, tracking its updates in the specified inventory and slot.
     * <p>
     * If the item has not been started yet, it is started and set up for periodic updates.
     * </p>
     *
     * @param item        the {@link MenuItem} containing the dynamic item to be added.
     * @param player      the player for whom the item is being rendered.
     * @param toRender    the inventory where the item will be rendered.
     * @param pasteSlot   the slot in the inventory where the item will be placed.
     */
    protected void addDynamicItem(@NotNull MenuItem item, @NotNull Player player, @NotNull Inventory toRender, int pasteSlot) {
        boolean started = dynamicItems.containsKey(item.getDisplay());
        Set<InventoryInfo> elements = started ? dynamicItems.get(item.getDisplay()) : new HashSet<>();
        elements.add(new InventoryInfo(player, toRender, pasteSlot));
        dynamicItems.put(item.getDisplay(), elements);

        Set<ItemElement<?>> items = dynamicItemInventories.getOrDefault(toRender, new HashSet<>());
        items.add(item.getDisplay());
        dynamicItemInventories.put(toRender, items);

        item.getDisplay().addUpdateAction(toRender, player.getUniqueId() + String.valueOf(pasteSlot), () -> updateItem(item.getDisplay()));
        item.getDisplay().startUpdateAction(toRender, player.getUniqueId() + String.valueOf(pasteSlot));
    }

    /**
     * Represents information about an inventory where a dynamic item is displayed.
     * <p>
     * {@code InventoryInfo} encapsulates the player, inventory, and slot information for a dynamic item.
     * </p>
     */
    protected static class InventoryInfo {

        /**
         * The player associated with this inventory info.
         */
        private final OfflinePlayer player;

        /**
         * The inventory where the dynamic item is displayed.
         */
        private final Inventory inventory;

        /**
         * The slot in the inventory where the dynamic item is displayed.
         */
        private final int pasteSlot;

        /**
         * Constructs an {@code InventoryInfo} instance with the specified player, inventory, and slot.
         *
         * @param player the player associated with this inventory info.
         * @param inventory the inventory where the dynamic item is displayed.
         * @param pasteSlot the slot in the inventory where the dynamic item is displayed.
         */
        public InventoryInfo(@NotNull OfflinePlayer player, @NotNull Inventory inventory, int pasteSlot) {
            this.player = player;
            this.inventory = inventory;
            this.pasteSlot = pasteSlot;
        }

        /**
         * Retrieves the player associated with this inventory info.
         *
         * @return the {@link OfflinePlayer} associated with this info.
         */
        public @NotNull OfflinePlayer getPlayer() {
            return player;
        }

        /**
         * Retrieves the inventory where the dynamic item is displayed.
         *
         * @return the {@link Inventory} where the item is displayed.
         */
        public @NotNull Inventory getInventory() {
            return inventory;
        }

        /**
         * Retrieves the slot in the inventory where the dynamic item is displayed.
         *
         * @return the slot index in the inventory.
         */
        public int getPasteSlot() {
            return pasteSlot;
        }
    }
}

