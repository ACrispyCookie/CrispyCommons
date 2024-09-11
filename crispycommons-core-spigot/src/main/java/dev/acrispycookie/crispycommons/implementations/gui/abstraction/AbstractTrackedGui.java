package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract base class for GUIs that track their viewers.
 * <p>
 * This class extends {@link AbstractGui} by adding functionality to keep track of players
 * who are currently viewing the GUI. It maintains a map of {@link OfflinePlayer} instances
 * to their viewing status, allowing subclasses to manage and query viewer information effectively.
 * </p>
 *
 * @param <T> the type of {@link GuiData} associated with this GUI.
 */
public abstract class AbstractTrackedGui<T extends GuiData> extends AbstractGui<T> {

    /**
     * A map tracking the viewing status of players for this GUI.
     * <p>
     * The key is an {@link OfflinePlayer}, and the value is a {@code Boolean} indicating
     * whether the player is currently viewing ({@code true}) or not viewing ({@code false}) the GUI.
     * </p>
     */
    protected final Map<OfflinePlayer, Boolean> viewers = new HashMap<>();

    /**
     * Constructs an {@code AbstractTrackedGui} with the specified GUI data.
     *
     * @param data the {@link GuiData} to be associated with this GUI.
     */
    public AbstractTrackedGui(@NotNull T data) {
        super(data);
    }

    /**
     * Checks whether the specified player is currently viewing this GUI.
     *
     * @param player the {@link OfflinePlayer} to check.
     * @return {@code true} if the player is viewing the GUI; {@code false} otherwise.
     */
    public boolean isPlayerViewing(@NotNull Player player) {
        return viewers.getOrDefault(player, false);
    }
}

