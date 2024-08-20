package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
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
     * The in-game item entity associated with this {@code ItemEntity}.
     * <p>
     * This entity represents the actual item in the game world and is used to display the item stack.
     * It is initialized when the entity is spawned and remains {@code null} until then.
     * </p>
     */
    private EntityItem ei = null;

    /**
     * The invisible armor stand used to hold and position the item entity in the game world.
     * <p>
     * This armor stand is utilized to adjust the visual position of the item entity and is
     * configured to be non-interactive and invisible. It is initialized when the entity is spawned
     * and remains {@code null} until then.
     * </p>
     */
    private EntityArmorStand as = null;

    /**
     * Constructs an {@code ItemEntity} with the specified item element.
     *
     * @param element the item element associated with this entity.
     */
    public ItemEntity(ItemElement<?> element) {
        super(element);
    }

    /**
     * Returns the offset per line for this entity when displayed.
     *
     * @return the offset per line, typically used for adjusting the position multiple entities in a hologram.
     */
    @Override
    public double offsetPerLine() {
        return -0.25;
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
        if (as == null) {
            as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            as.setInvisible(true);
            as.n(true); // Disables interaction
            as.setBasePlate(true);
            as.setGravity(false);
            as.setCustomNameVisible(false);
            as.setSmall(true);
        }

        PacketPlayOutSpawnEntityLiving spawnArmorStand = new PacketPlayOutSpawnEntityLiving(as);
        PacketPlayOutEntityMetadata metadataArmorStand = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawnArmorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadataArmorStand);

        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        if (ei == null) {
            ei = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(elementValue));
            ei.a(Integer.MAX_VALUE); // Makes the item entity persistent (doesn't despawn)
        }

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(ei, 2, 1);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ei.getId(), ei.getDataWatcher(), true);
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(0, ei, as);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(attach);
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
        if (as != null) {
            as.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
        if (ei != null) {
            ei.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(ei.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
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

        if (as != null && ei != null) {
            ei.setItemStack(CraftItemStack.asNMSCopy(item));
            as.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ei.getId(), ei.getDataWatcher(), true);
            PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(as);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
        }
    }

    /**
     * Returns the current location of the entity.
     *
     * @return the {@link Location} of the entity in the game world.
     */
    @Override
    public @NotNull Location getLocation() {
        return as.getBukkitEntity().getLocation();
    }
}

