package dev.acrispycookie.crispycommons.api.visual.map_image;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an image that can be placed on a map in the game world.
 * <p>
 * The {@code CrispyMapImage} interface provides a method for placing the image onto a specified block,
 * allowing for visual customization and interaction within the game environment.
 * </p>
 */
public interface CrispyMapImage {

    /**
     * Places the map image on the specified starting block.
     *
     * @param start the {@link Block} where the image should be placed.
     */
    void place(@NotNull Block start);
}

