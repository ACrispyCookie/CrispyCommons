package dev.acrispycookie.crispycommons.api.gui.abstraction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a graphical user interface.
 * <p>
 * A {@link CrispyGui} can be opened and closed for individual players.
 * </p>
 */
public interface CrispyGui {

    /**
     * Opens this GUI for the specified player.
     * <p>
     * This method will display the user interface to the player, allowing them
     * to interact with it.
     * </p>
     *
     * @param player the player for whom the GUI should be opened.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void open(@NotNull Player player);

    /**
     * Closes this GUI for the specified player.
     * <p>
     * This method will remove the user interface from the player's view,
     * effectively closing it for them.
     * </p>
     *
     * @param player the player for whom the GUI should be closed.
     * @param closeView the option to close the view. Normally set to true, but set to false if another
     *                  view is going to open right after.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void close(@NotNull Player player, boolean closeView);
}
