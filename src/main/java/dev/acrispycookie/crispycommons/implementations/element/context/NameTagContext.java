package dev.acrispycookie.crispycommons.implementations.element.context;

import org.bukkit.OfflinePlayer;

/**
 * Represents the context for name tags between players.
 * <p>
 * This class holds references to the player and the receiver involved in a name tag context.
 * </p>
 */
public class NameTagContext {

    /**
     * The player who owns the name tag.
     * <p>
     * This field represents the {@link OfflinePlayer} whose name tag is being managed.
     * The player is the one who the name tag belongs to and is the subject of the context.
     * </p>
     */
    private final OfflinePlayer player;

    /**
     * The player who views the name tag.
     * <p>
     * This field represents the {@link OfflinePlayer} who is the receiver or viewer of the name tag.
     * The receiver is the one who sees the name tag associated with the player.
     * </p>
     */
    private final OfflinePlayer receiver;

    /**
     * Constructs a {@code NameTagContext} with the specified player and receiver.
     *
     * @param player the {@link OfflinePlayer} who is the owner of the name tag.
     * @param receiver the {@link OfflinePlayer} who is the viewer of the name tag.
     *
     * @throws NullPointerException if {@code player} or {@code receiver} is {@code null}.
     */
    public NameTagContext(OfflinePlayer player, OfflinePlayer receiver) {
        this.player = player;
        this.receiver = receiver;
    }

    /**
     * Returns the player that owns the name tag.
     *
     * @return the {@link OfflinePlayer} that owns the name tag.
     */
    public OfflinePlayer getPlayer() {
        return player;
    }

    /**
     * Returns the receiver of the name tag context.
     *
     * @return the {@link OfflinePlayer} who is the viewer of the name tag.
     */
    public OfflinePlayer getReceiver() {
        return receiver;
    }
}

