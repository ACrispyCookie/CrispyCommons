package dev.acrispycookie.crispycommons.api.gui.abstraction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a graphical user interface.
 * A {@link CrispyGui} can be opened and closed for individual players.
 */
public interface CrispyGui {

    /**
     * Opens this GUI for the specified player.
     * This method will display the user interface to the player, allowing them
     * to interact with it.
     *
     * @param player the player for whom the GUI should be opened.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void open(@NotNull Player player);

    /**
     * Closes this GUI for the specified player.
     * This method will remove the user interface from the player's view,
     * effectively closing it for them.
     *
     * @param player the player for whom the GUI should be closed.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void close(@NotNull Player player);
}
