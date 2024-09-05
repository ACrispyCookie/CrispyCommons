package dev.acrispycookie.crispycommons.implementations.visual.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.title.data.TitleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Set;

/**
 * An implementation of {@link AbstractTitle} that represents a title with dynamic updates.
 * <p>
 * {@code UpdatingTitle} is designed to continuously update the title displayed to the player,
 * allowing for dynamic changes in the title and subtitle over time. It handles the display
 * and update logic on a per-player basis.
 * </p>
 */
public class UpdatingTitle extends AbstractTitle {

    /**
     * Constructs an {@code UpdatingTitle} with the specified parameters.
     *
     * @param data the {@link TitleData} that contains the visual and functional data for the title.
     * @param receivers the set of players who will receive the title.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the title.
     * @param isPublic whether the title should be visible to all players.
     */
    public UpdatingTitle(@NotNull TitleData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the title to the specified player.
     * <p>
     * This method calculates the fade-in, duration, and fade-out times based on the title data and shows the title to the player.
     * </p>
     *
     * @param player the player to whom the title will be shown.
     */
    @Override
    protected void show(@NotNull Player player) {
        showTitle(player, getFadeIn().getFromContext(OfflinePlayer.class, player) * 50L, data.getSmallestPeriod() * 150L, 0);
    }

    /**
     * Hides the title from the specified player.
     * <p>
     * This method immediately hides the title from the player by setting the fade-out time.
     * </p>
     *
     * @param player the player from whom the title will be hidden.
     */
    @Override
    public void hide(@NotNull Player player) {
        showTitle(player, 0, 1, getFadeOut().getFromContext(OfflinePlayer.class, player) * 50L);
    }

    /**
     * Updates the title for a specific player.
     * <p>
     * This method re-shows the title to the player with an updated duration, allowing dynamic changes.
     * </p>
     *
     * @param player the player for whom the title will be updated.
     */
    @Override
    protected void perPlayerUpdate(@NotNull Player player) {
        showTitle(player, 0, data.getSmallestPeriod() * 150L, 0);
    }

    /**
     * Updates the title globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }

    /**
     * Sends the title to the specified player with the given timing parameters.
     * <p>
     * This method constructs a {@link Title} object using the specified fade-in, duration, and fade-out times,
     * and sends it to the player.
     * </p>
     *
     * @param p the player to whom the title will be shown.
     * @param fadeIn the fade-in time in milliseconds.
     * @param duration the duration for which the title will be displayed, in milliseconds.
     * @param fadeOut the fade-out time in milliseconds.
     */
    private void showTitle(@NotNull Player p, long fadeIn, long duration, long fadeOut) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(
                getTitle().getFromContext(OfflinePlayer.class, p),
                getSubtitle().getFromContext(OfflinePlayer.class, p),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofMillis(duration),
                        Duration.ofMillis(fadeOut)
                )
        );
        audience.showTitle(toSend);
    }
}

