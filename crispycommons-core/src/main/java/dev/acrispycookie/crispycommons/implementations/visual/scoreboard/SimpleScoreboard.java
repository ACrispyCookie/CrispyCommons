package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.nms.visuals.VersionScoreboard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A concrete implementation of {@link AbstractScoreboard} that represents a simple scoreboard.
 * <p>
 * {@code SimpleScoreboard} manages the creation, updating, and removal of scoreboards for players.
 * It supports dynamic updates to the scoreboard title and lines, and handles player-specific
 * display of the scoreboard.
 * </p>
 */
public class SimpleScoreboard extends AbstractScoreboard {

    private final VersionScoreboard versionScoreboard = VersionScoreboard.newInstance();

    /**
     * Constructs a {@code SimpleScoreboard} with the specified data, receivers, time-to-live, and visibility.
     *
     * @param data the {@link ScoreboardData} containing the visual and functional data for the scoreboard.
     * @param receivers the collection of players who will receive the scoreboard.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the scoreboard.
     * @param isPublic whether the scoreboard should be visible to all players.
     */
    public SimpleScoreboard(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the scoreboard to the specified player.
     *
     * @param p the player to whom the scoreboard will be shown.
     */
    @Override
    protected void show(Player p) {
        Component titleText = data.getTitle().getFromContext(OfflinePlayer.class, p);
        List<Component> lines = data.getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
        versionScoreboard.show(p, titleText, lines);
    }

    /**
     * Hides the scoreboard from the specified player.
     *
     * @param p the player from whom the scoreboard will be hidden.
     */
    @Override
    protected void hide(Player p) {
        versionScoreboard.hide(p, data.getLines().size());
    }

    /**
     * Updates the scoreboard for a specific player.
     *
     * @param p the player whose scoreboard will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {
        Component titleText = data.getTitle().getFromContext(OfflinePlayer.class, p);
        List<Component> lines = data.getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
        versionScoreboard.updateContent(p, titleText, lines);
    }

    /**
     * Global update method, currently not implemented as it is not used.
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }

    /**
     * Handles the addition of a line to the scoreboard's internal structure.
     *
     * @param index the index at which the line was added.
     */
    @Override
    protected void addLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = data.getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            versionScoreboard.resetLines(p, data.getLines().size() - 1, lines);
        }

        if (isAnyoneWatching()) {
            update();
        }
    }

    /**
     * Handles the removal of a line from the scoreboard's internal structure.
     *
     * @param index the index of the line to be removed.
     */
    @Override
    protected void removeLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = data.getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            versionScoreboard.resetLines(p, data.getLines().size() + 1, lines);
        }

        if (isAnyoneWatching()) {
            update();
        }
    }

    /**
     * Updates the title on the scoreboard.
     */
    @Override
    protected void updateTitle() {
        for (Player p : getCurrentlyViewing()) {
            Component titleText = data.getTitle().getFromContext(OfflinePlayer.class, p);
            versionScoreboard.resetTitle(p, titleText);
        }
    }

    /**
     * Updates the lines on the scoreboard, resetting them as necessary.
     *
     * @param oldSize the previous number of lines on the scoreboard.
     */
    @Override
    protected void updateLines(int oldSize) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = data.getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            versionScoreboard.resetLines(p, oldSize, lines);
        }
    }

}

