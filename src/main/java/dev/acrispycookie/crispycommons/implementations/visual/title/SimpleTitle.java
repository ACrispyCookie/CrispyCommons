package dev.acrispycookie.crispycommons.implementations.visual.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.title.data.TitleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

/**
 * A concrete implementation of {@link AbstractTitle} that represents a simple title display.
 * <p>
 * {@code SimpleTitle} is responsible for displaying a title with optional subtitle to players.
 * The title is shown with specific fade-in, stay, and fade-out durations, which are configurable.
 * This class handles the display on a per-player basis.
 * </p>
 */
public class SimpleTitle extends AbstractTitle {

    /**
     * Constructs a {@code SimpleTitle} with the specified parameters.
     *
     * @param data the {@link TitleData} containing the visual data for the title.
     * @param receivers the set of players who will receive the title.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the title.
     * @param isPublic whether the title should be visible to all players.
     */
    public SimpleTitle(TitleData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the title to the specified player.
     * <p>
     * The title, subtitle, and their respective durations are retrieved from the title data
     * and sent to the player using the {@link Adventure} API.
     * </p>
     *
     * @param p the player to whom the title will be shown.
     */
    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(
                data.getTitle().getFromContext(OfflinePlayer.class, p),
                data.getSubtitle().getFromContext(OfflinePlayer.class, p),
                Title.Times.times(
                        Duration.ofMillis(data.getFadeIn().getFromContext(OfflinePlayer.class, p) * 50L),
                        Duration.ofMillis(timeToLive.getElement().getFromContext(OfflinePlayer.class, p) * 50L),
                        Duration.ofMillis(data.getFadeOut().getFromContext(OfflinePlayer.class, p) * 50L)
                )
        );
        audience.showTitle(toSend);
    }

    /**
     * Hides the title from the specified player.
     * <p>
     * This method is currently empty, as no specific action is needed to hide the title.
     * </p>
     *
     * @param p the player from whom the title will be hidden.
     */
    @Override
    public void hide(Player p) {
        // No specific action needed to hide the title.
    }

    /**
     * Updates the title for a specific player.
     * <p>
     * This method is currently empty, as the title does not require per-player updates
     * beyond the initial display.
     * </p>
     *
     * @param p the player for whom the title will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {
        // No per-player update logic needed.
    }

    /**
     * Updates the title globally.
     * <p>
     * This method is currently empty, as global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }
}

