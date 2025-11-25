package dev.acrispycookie.crispycommons.implementations.gui.book.action;

import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookLine;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an abstract action that can be attached to a {@link BookLine} in a book.
 * <p>
 * Each {@link BookAction} is uniquely identified by a UUID and can be executed by calling the
 * {@link #performAction(Player)} method. After the action is executed, it is removed from the pending actions.
 * </p>
 */
public abstract class BookAction {

    /**
     * The unique identifier for this action.
     */
    private final UUID uuid;

    /**
     * A static map storing all pending actions, keyed by their UUID.
     */
    private static final Map<UUID, BookAction> pendingActions = new HashMap<>();

    /**
     * Defines the behavior of the action when it is executed.
     *
     * @param p the {@link Player} who triggered the action.
     */
    public abstract void run(@NotNull Player p);

    /**
     * Constructs a new {@code BookAction} and generates a unique UUID for it.
     * <p>
     * The action is added to the pending actions map upon creation.
     * </p>
     */
    public BookAction() {
        uuid = getRandomUuid();
        pendingActions.put(uuid, this);
    }

    /**
     * Cancels the action, removing it from the pending actions map.
     */
    public void cancel() {
        pendingActions.remove(uuid);
    }

    /**
     * Performs the action for the specified player and removes it from the pending actions map.
     *
     * @param p the {@link Player} who triggered the action.
     */
    public void performAction(@NotNull Player p) {
        run(p);
        pendingActions.remove(uuid);
    }

    /**
     * Returns the UUID associated with this action.
     *
     * @return the unique UUID of this action.
     */
    public @NotNull UUID getUuid() {
        return uuid;
    }

    /**
     * Retrieves a pending action by its UUID.
     * <p>
     * If no action is found with the given UUID, this method returns {@code null}.
     * </p>
     *
     * @param uuid the UUID of the action to retrieve.
     * @return the {@link BookAction} associated with the UUID, or {@code null} if not found.
     */
    public static @Nullable BookAction getPendingAction(UUID uuid) {
        return pendingActions.getOrDefault(uuid, null);
    }

    /**
     * Generates a unique UUID that is not already present in the pending actions map.
     *
     * @return a unique {@link UUID}.
     */
    private @NotNull UUID getRandomUuid() {
        UUID uuid = UUID.randomUUID();
        while (pendingActions.containsKey(uuid)) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }
}

