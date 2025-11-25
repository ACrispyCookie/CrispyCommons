package dev.acrispycookie.crispycommons.implementations.visual.actionbar;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.data.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * A concrete implementation of {@link AbstractActionbar} that represents a simple action bar.
 * <p>
 * {@code SimpleActionbar} handles the display of action bars on a per-player basis. It is designed to
 * send action bar messages to players individually, updating the display as necessary.
 * </p>
 */
public class SimpleActionbar extends AbstractActionbar {

    /**
     * Constructs a {@code SimpleActionbar} with the specified parameters.
     *
     * @param data the {@link ActionbarData} that contains the visual and functional data for the action bar.
     * @param receivers the set of players who will receive the action bar.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the action bar.
     * @param isPublic whether the action bar should be visible to all players.
     */
    public SimpleActionbar(@NotNull ActionbarData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the action bar to the specified player.
     * <p>
     * This method retrieves the appropriate text for the player from the action bar's data context
     * and sends it as an action bar message using the {@link Adventure} API.
     * </p>
     *
     * @param player the player to whom the action bar will be shown.
     */
    @Override
    protected void show(@NotNull Player player) {
        Component text = getText().getFromContext(OfflinePlayer.class, player);
        Audience audience = SpigotCrispyCommons.getInstance().getBukkitAudiences().player(player);
        audience.sendActionBar(text);
    }

    /**
     * Hides the action bar from the specified player.
     * <p>
     * This method is currently empty, as no specific action is needed to hide the action bar.
     * </p>
     *
     * @param player the player from whom the action bar will be hidden.
     */
    @Override
    protected void hide(@NotNull Player player) {
        // No specific action needed to hide the action bar.
    }

    /**
     * Updates the action bar for a specific player.
     * <p>
     * This method simply re-shows the action bar to the player, effectively updating its content.
     * </p>
     *
     * @param player the player for whom the action bar will be updated.
     */
    @Override
    protected void perPlayerUpdate(@NotNull Player player) {
        show(player);
    }

    /**
     * Updates the action bar globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }
}

