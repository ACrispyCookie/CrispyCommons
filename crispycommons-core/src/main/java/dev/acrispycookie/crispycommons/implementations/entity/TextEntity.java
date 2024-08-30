package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import dev.acrispycookie.crispycommons.version.utility.Version;
import dev.acrispycookie.crispycommons.version.utility.VersionPair;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Location;

/**
 * Represents an in-game text entity that can be spawned, updated, and destroyed.
 * <p>
 * This class extends {@link ClickableEntity} and provides specific implementation details for
 * handling text entities in the game world. It includes methods to manage the entity's lifecycle
 * (spawn, update, destroy) and its visual representation for a player.
 * </p>
 */
public abstract class TextEntity extends ClickableEntity<TextElement<?>> implements Versioned {

    /**
     * Constructs a {@code TextEntity} with the specified text element.
     *
     * @param element the text element associated with this entity.
     */
    public TextEntity(@NotNull TextElement<?> element, @NotNull Location location) {
        super(element, location);
    }

    public static TextEntity newInstance(@NotNull TextElement<?> element, @NotNull Location location) {
        return VersionManager.createInstance(TextEntity.class, getRemapped(),
                new ArgPair<>(TextElement.class, element),
                new ArgPair<>(Location.class, location));
    }

    public static MappedVersions getRemapped() {
        return new MappedVersions(new VersionPair(Version.v1_20_R3, Version.v1_8_R3));
    }
}

