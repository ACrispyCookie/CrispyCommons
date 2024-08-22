package dev.acrispycookie.crispycommons.api.entity;

import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.entity.ItemEntity;
import dev.acrispycookie.crispycommons.implementations.entity.TextEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an entity that can be spawned using a {@link CrispyElement}
 * <p>
 * An {@link Entity} can be spawned, destroyed and updated for individual players.
 * </p>
 */
public interface Entity {

    /**
     * Creates an {@link Entity} from the specified dynamic element.
     * <p>
     * This static factory method generates an {@link Entity} instance based on the provided {@link DynamicElement}.
     * At the moment only {@link ItemElement} and {@link TextElement} are supported and they are spawned using armor stand packets.
     * </p>
     *
     * @param element the {@link DynamicElement} from which to create the {@link Entity}.
     * @return a new {@link Entity} instance based on the specified dynamic element.
     * @throws NullPointerException if {@code element} is {@code null}.
     */
    static @NotNull Entity of(@NotNull DynamicElement<?, ?> element) {
        if (element instanceof ItemElement)
            return new ItemEntity((ItemElement<?>) element);
        else if (element instanceof TextElement)
            return new TextEntity((TextElement<?>) element);
        else
            throw new IllegalArgumentException("The element must be an instance of ItemElement or TextElement!");
    }

    /**
     * Returns the height offset value for each line in hologram.
     *
     * @return the offset value as a {@code double}.
     */
    double offsetPerLine();

    /**
     * Retrieves the current {@link Location} of this entity.
     * <p>
     * This method returns the {@link Location} that represents the current position of the entity.
     * </p>
     *
     * @return the current {@link Location}.
     * @throws NullPointerException if the location is not set or cannot be determined.
     */
    @NotNull Location getLocation();

    /**
     * Retrieves the associated {@link DynamicElement} of this entity.
     * <p>
     * This method returns the {@link DynamicElement} that is linked to this entity and was used
     * to create this entity.
     * </p>
     *
     * @return the associated {@link DynamicElement}.
     * @throws NullPointerException if the element is not set or cannot be determined.
     */
    @NotNull DynamicElement<?, ?> getElement();

    /**
     * Spawns the entity at the specified location for the given player.
     * <p>
     * This method places the entity at the provided {@link Location} and makes it visible to the specified {@link Player}.
     * </p>
     *
     * @param location the {@link Location} where the entity will be spawned.
     * @param player the {@link Player} for whom the entity will be spawned and visible.
     * @throws NullPointerException if {@code location} or {@code player} is {@code null}.
     */
    void spawn(@NotNull Location location, @NotNull Player player);

    /**
     * Destroys the entity for the given player.
     * <p>
     * This method removes the entity from the game or view, making it no longer visible or interactable for the specified
     * {@link Player}.
     * </p>
     *
     * @param player the {@link Player} for whom the entity will be destroyed.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void destroy(@NotNull Player player);

    /**
     * Updates the entity's location and other metadata for the given player.
     * <p>
     * This method changes the entity's position to the new specified {@link Location}, updates other
     * metadata of the entity and then updates the view for the specified {@link Player} to reflect this change.
     * </p>
     *
     * @param location the new {@link Location} to which the entity will be updated.
     * @param player the {@link Player} for whom the entity's location will be updated.
     * @throws NullPointerException if {@code location} or {@code player} is {@code null}.
     */
    void update(@NotNull Location location, @NotNull Player player);

}
