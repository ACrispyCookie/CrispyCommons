package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.nms.entity.custom.CustomItem;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an in-game item entity that can be spawned, updated, and destroyed.
 * <p>
 * This class extends {@link ClickableEntity} and provides specific implementation details for
 * handling item entities in the game world. It includes methods to manage the entity's lifecycle
 * (spawn, update, destroy) and its visual representation for a player.
 * </p>
 */
public class ItemEntity extends ClickableEntity<ItemElement<?>> {

    /**
     * The version independent instance of a custom item.
     * <p>
     * This instance manages the spawning, updating and removing of the custom item with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private CustomItem item;

    /**
     * Constructs an {@code ItemEntity} with the specified item element.
     *
     * @param element the item element associated with this entity.
     */
    public ItemEntity(@NotNull ItemElement<?> element) {
        super(element);
    }

    /**
     * Returns the offset per line for this entity when displayed.
     *
     * @return the offset per line, typically used for adjusting the position multiple entities in a hologram.
     */
    @Override
    public double offsetPerLine() {
        return -item.offsetPerLine();
    }

    /**
     * Spawns the item entity at the specified location for the given player.
     * <p>
     * This method creates and sends packets to the client to render the item entity and its associated
     * armor stand in the game world.
     * </p>
     *
     * @param location the location where the entity should be spawned.
     * @param player the player for whom the entity will be spawned.
     */
    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);
        item = CustomItem.newInstance(location.subtract(0, 0.05, 0), elementValue);
        item.spawn(location.subtract(0, 0.05, 0), player);
    }

    /**
     * Destroys the item entity for the given player.
     * <p>
     * This method removes the entity from the game world and sends packets to the client to remove
     * the visual representation of the entity.
     * </p>
     *
     * @param player the player for whom the entity will be destroyed.
     */
    @Override
    public void destroy(@NotNull Player player) {
        item.destroy(player);
    }

    /**
     * Updates the item entity's location and item stack for the given player.
     * <p>
     * This method adjusts the entity's position and updates the displayed item stack by sending
     * the necessary packets to the client.
     * </p>
     *
     * @param location the new location for the entity.
     * @param player the player for whom the entity will be updated.
     */
    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        CrispyItemStack item = element.getFromContext(OfflinePlayer.class, player);

        this.item.setDisplay(item);
        this.item.update(location.subtract(0, 0.05, 0), player);
    }

    /**
     * Returns the current location of the entity.
     *
     * @return the {@link Location} of the entity in the game world.
     */
    @Override
    public @NotNull Location getLocation() {
        return item.getLocation();
    }
}

