package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.nms.entity.custom.CustomText;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
     * The version independent instance of a custom text.
     * <p>
     * This instance manages the spawning, updating and removing of the custom text with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private CustomText textEntity;

    /**
     * Constructs a {@code TextEntity} with the specified text element.
     *
     * @param element the text element associated with this entity.
     */
    public TextEntity(@NotNull TextElement<?> element) {
        super(element);
    }

    /**
     * Returns the offset per line for this entity when displayed.
     *
     * @return the offset per line, typically used for adjusting the position multiple entities in a hologram.
     */
    @Override
    public double offsetPerLine() {
        return -textEntity.offsetPerLine();
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

        textEntity = CustomText.newInstance(location, elementValue);
        textEntity.spawn(location, player);
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
        textEntity.destroy(player);
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
        textEntity.setText(text);
        textEntity.update(location, player);
    }

    /**
     * Returns the current location of the entity.
     *
     * @return the {@link Location} of the entity in the game world.
     */
    @Override
    public @NotNull Location getLocation() {
        return textEntity.getLocation();
    }
}

