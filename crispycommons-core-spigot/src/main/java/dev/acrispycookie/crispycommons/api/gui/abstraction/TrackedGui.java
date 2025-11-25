package dev.acrispycookie.crispycommons.api.gui.abstraction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a GUI that can track whether players are currently viewing it.
 * <p>
 * Extends the {@link CrispyGui} interface to add tracking capabilities.
 * </p>
 */
public interface TrackedGui extends CrispyGui {

    /**
     * Checks if the specified player is currently viewing this GUI.
     *
     * @param player the player to check.
     * @return true if the player is currently viewing this GUI, false otherwise.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    boolean isPlayerViewing(@NotNull Player player);
}
