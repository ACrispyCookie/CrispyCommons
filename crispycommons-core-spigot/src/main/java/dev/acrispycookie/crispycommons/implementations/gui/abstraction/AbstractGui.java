package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import dev.acrispycookie.crispycommons.logging.CrispyLogger;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract base class for creating graphical user interfaces (GUIs).
 * <p>
 * This class provides a structure for GUIs that manage {@link GuiData} and implements the {@link CrispyGui} interface.
 * Subclasses should provide specific implementations for opening and closing the GUI for a player.
 * </p>
 *
 * @param <T> the type of {@link GuiData} that this GUI will use.
 */
public abstract class AbstractGui<T extends GuiData> implements CrispyGui {

    /**
     * The data associated with this GUI, used to store and manage its state.
     */
    protected final T data;

    /**
     * Constructs an {@code AbstractGui} with the specified GUI data.
     *
     * @param data the data to be associated with this GUI.
     */
    public AbstractGui(@NotNull T data) {
        this.data = data;
    }

    /**
     * Returns the data associated with this GUI.
     *
     * @return the GUI data.
     */
    protected @NotNull T getData() {
        return data;
    }

    /**
     * Opens the GUI for the specified player.
     * <p>
     * This method first ensures that the GUI data is ready before delegating to the {@code openInternal} method
     * to perform the actual opening logic. If the data is not ready, an exception is logged.
     * </p>
     *
     * @param player the player for whom the GUI will be opened.
     */
    @Override
    public void open(@NotNull Player player) {
        try {
            data.assertReady();
            openInternal(player);
        } catch (GuiData.GuiNotReadyException e) {
            CrispyLogger.printException(SpigotCrispyCommons.getInstance().getPlugin(), e, "This GUI is not ready to be opened!");
        }
    }

    /**
     * Closes the GUI for the specified player.
     * <p>
     * This method delegates to the {@code closeInternal} method to perform the actual closing logic.
     * </p>
     *
     * @param player the player for whom the GUI will be closed.
     * @param closeView the option to close the view. Set to false if another menu is going to open right after.
     */
    @Override
    public void close(@NotNull Player player, boolean closeView) {
        closeInternal(player, closeView);
    }

    /**
     * Opens the GUI for the specified player with custom logic defined by subclasses.
     *
     * @param player the player for whom the GUI will be opened.
     */
    protected abstract void openInternal(@NotNull Player player);

    /**
     * Closes the GUI for the specified player with custom logic defined by subclasses.
     *
     * @param player the player for whom the GUI will be closed.
     * @param closeView the option to close the view. Set to false if another menu is going to open right after.
     */
    protected abstract void closeInternal(@NotNull Player player, boolean closeView);
}

