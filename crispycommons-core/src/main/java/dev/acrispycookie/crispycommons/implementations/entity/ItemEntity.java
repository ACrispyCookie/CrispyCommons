package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an in-game item entity that can be spawned, updated, and destroyed.
 * <p>
 * This class extends {@link ClickableEntity} and provides specific implementation details for
 * handling item entities in the game world. It includes methods to manage the entity's lifecycle
 * (spawn, update, destroy) and its visual representation for a player.
 * </p>
 */
public abstract class ItemEntity extends ClickableEntity<ItemElement<?>> implements Versioned {

    /**
     * Constructs an {@code ItemEntity} with the specified item element.
     *
     * @param element the item element associated with this entity.
     */
    public ItemEntity(@NotNull ItemElement<?> element, @NotNull Location location) {
        super(element, location);
    }

    public static ItemEntity newInstance(@NotNull ItemElement<?> element, @NotNull Location location) {
        return VersionManager.createInstance(ItemEntity.class, getRemapped(),
                new ArgPair<>(ItemElement.class, element),
                new ArgPair<>(Location.class, location));
    }

    public static MappedVersions getRemapped() {
        return new MappedVersions();
    }

}

