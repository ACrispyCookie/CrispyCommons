package dev.acrispycookie.crispycommons.implementations.entity;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an in-game text entity that can be spawned, updated, and destroyed.
 * <p>
 * This class extends {@link ClickableEntity} and provides specific implementation details for
 * handling text entities in the game world. It includes methods to manage the entity's lifecycle
 * (spawn, update, destroy) and its visual representation for a player.
 * </p>
 */
public class TextEntity extends ClickableEntity<TextElement<?>> {

    /**
     * The invisible armor stand used to display and position the text entity in the game world.
     * <p>
     * This armor stand serves as the anchor for the text, allowing it to be positioned and rendered
     * in the world. It is configured to be non-interactive and invisible, showing only the custom
     * text assigned to it. The armor stand is initialized when the entity is spawned and remains
     * {@code null} until then.
     * </p>
     */
    private EntityArmorStand as = null;

    /**
     * Constructs a {@code TextEntity} with the specified text element.
     *
     * @param element the text element associated with this entity.
     */
    public TextEntity(TextElement<?> element) {
        super(element);
    }

    /**
     * Returns the offset per line for this entity when displayed.
     *
     * @return the offset per line, typically used for adjusting the position multiple entities in a hologram.
     */
    @Override
    public double offsetPerLine() {
        return -0.23;
    }

    /**
     * Spawns the text entity at the specified location for the given player.
     * <p>
     * This method creates and sends packets to the client to render the text entity and its associated
     * armor stand in the game world.
     * </p>
     *
     * @param location the location where the entity should be spawned.
     * @param player the player for whom the entity will be spawned.
     */
    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        Component elementValue = element.getFromContext(OfflinePlayer.class, player);
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(
                elementValue == null ? Component.text("") : elementValue
        );

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

        if (as == null) {
            as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            as.setInvisible(true);
            as.n(true); // Disables interaction
            as.setBasePlate(true);
            as.setGravity(false);
            as.setCustomNameVisible(true);
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
            as.setSmall(true);
        }

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(as, 78);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    /**
     * Destroys the text entity for the given player.
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
    }

    /**
     * Updates the text entity's location and text content for the given player.
     * <p>
     * This method adjusts the entity's position and updates the displayed text by sending
     * the necessary packets to the client.
     * </p>
     *
     * @param location the new location for the entity.
     * @param player the player for whom the entity will be updated.
     */
    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        Component text = element.getFromContext(OfflinePlayer.class, player);
        String content = LegacyComponentSerializer.legacyAmpersand().serialize(text);

        String name = StringUtils.isEmptyOrWhitespaceOnly(content) ? " " : ChatColor.translateAlternateColorCodes('&', content);

        if (as != null) {
            as.setCustomName(name);
            as.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
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

