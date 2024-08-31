package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashSet;

/**
 * A concrete implementation of {@link AbstractScoreboard} that represents a simple scoreboard.
 * <p>
 * {@code SimpleScoreboard} manages the creation, updating, and removal of scoreboards for players.
 * It supports dynamic updates to the scoreboard title and lines, and handles player-specific
 * display of the scoreboard.
 * </p>
 */
public abstract class SimpleScoreboard extends AbstractScoreboard implements Versioned {

    /**
     * Constructs a {@code SimpleScoreboard} with the specified data, receivers, time-to-live, and visibility.
     *
     * @param data the {@link ScoreboardData} containing the visual and functional data for the scoreboard.
     * @param receivers the collection of players who will receive the scoreboard.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the scoreboard.
     * @param isPublic whether the scoreboard should be visible to all players.
     */
    public SimpleScoreboard(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.GLOBAL, isPublic);
    }

    public static SimpleScoreboard newInstance(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        return VersionManager.createInstance(SimpleScoreboard.class, getRemapped(),
                new ArgPair<>(ScoreboardData.class, data),
                new ArgPair<>(Collection.class, receivers),
                new ArgPair<>(TimeToLiveElement.class, timeToLive),
                new ArgPair<>(boolean.class, isPublic));
    }

    public static MappedVersions getRemapped() {
        return new MappedVersions();
    }
}

