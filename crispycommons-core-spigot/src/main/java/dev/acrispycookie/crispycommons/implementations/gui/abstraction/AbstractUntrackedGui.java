package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract base class for GUIs that do not require tracking of viewers.
 * <p>
 * This class extends {@link AbstractGui} and provides a structure for GUIs where viewer tracking
 * is not necessary. Subclasses are expected to implement their own logic for opening and managing
 * the GUI, while the closing behavior is intentionally left unimplemented.
 * </p>
 *
 * @param <T> the type of {@link GuiData} associated with this GUI.
 */
public abstract class AbstractUntrackedGui<T extends GuiData> extends AbstractGui<T> {

    /**
     * Constructs an {@code AbstractUntrackedGui} with the specified GUI data.
     *
     * @param data the {@link GuiData} to be associated with this GUI.
     */
    public AbstractUntrackedGui(@NotNull T data) {
        super(data);
    }

    /**
     * Closes the GUI for the specified player.
     * <p>
     * This method is overridden to provide no specific closing behavior.
     * Subclasses may override this method to implement their own closing logic.
     * </p>
     *
     * @param player the player for whom the GUI will be closed.
     * @param closeView the option to close the view. Normally set to true, but set to false if another
     *                  view is going to open right after.
     */
    @Override
    public void close(@NotNull Player player, boolean closeView) {
        // No specific closing behavior; intended to be overridden by subclasses
    }

    /**
     * Internal method to close the GUI for the specified player.
     * <p>
     * This method is intentionally left empty, as untracked GUIs do not require
     * specific internal logic for closing. Subclasses may override this method if needed.
     * </p>
     *
     * @param player the player for whom the GUI will be closed.
     * @param closeView the option to close the view. Normally set to true, but set to false if another
     *                  view is going to open right after.
     */
    @Override
    protected void closeInternal(@NotNull Player player, boolean closeView) {
        // No internal closing logic; intended to be overridden by subclasses
    }
}

