package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
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

/**
 * A concrete implementation of {@link AbstractScoreboard} that represents a simple scoreboard.
 * <p>
 * {@code SimpleScoreboard} manages the creation, updating, and removal of scoreboards for players.
 * It supports dynamic updates to the scoreboard title and lines, and handles player-specific
 * display of the scoreboard.
 * </p>
 */
public class SimpleScoreboard extends AbstractScoreboard {

    /**
     * A map that associates each player with their respective scoreboard.
     */
    private final HashMap<OfflinePlayer, Scoreboard> scoreboards = new HashMap<>();

    /**
     * A map that tracks how many times a specific string has been used as an entry in a player's scoreboard.
     */
    private final HashMap<OfflinePlayer, HashMap<String, Integer>> timesUsedInEntries = new HashMap<>();

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
        scoreboards.put(p, getNewBoard(p));
        initTitle(p);
        initLines(p, 0);
        p.setScoreboard(scoreboards.get(p));
    }

    /**
     * Hides the scoreboard from the specified player.
     *
     * @param p the player from whom the scoreboard will be hidden.
     */
    @Override
    protected void hide(Player p) {
        p.setScoreboard(removeSidebar(p));
    }

    /**
     * Updates the scoreboard for a specific player.
     *
     * @param p the player whose scoreboard will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {
        initTitle(p);
        for (int i = 0; i < data.getLines().size(); i++) {
            updateLine(p, i);
        }
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
            resetScoreboard(p, data.getLines().size() - 1);
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
            resetScoreboard(p, data.getLines().size() + 1);
        }

        if (isAnyoneWatching()) {
            update();
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
            resetScoreboard(p, oldSize);
        }
    }

    /**
     * Updates a specific line on the scoreboard for a player.
     *
     * @param player the player whose scoreboard line will be updated.
     * @param index the index of the line to update.
     */
    private void updateLine(Player player, int index) {
        Scoreboard scoreboard = scoreboards.get(player);
        Component text = data.getLines().get(index).getFromContext(OfflinePlayer.class, player);
        Objective obj = scoreboard.getObjective("[CrispyCommons]");
        Team team = scoreboard.getTeam(String.valueOf(index));

        String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
        line = line.substring(0, Math.min(line.length(), 32));
        String teamEntry = team.getEntries().iterator().next();
        String prefix = getPrefix(line);
        String suffix = getSuffix(line, teamEntry);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        obj.getScore(teamEntry).setScore(15 - index);
    }

    /**
     * Initializes the scoreboard title for a player.
     *
     * @param player the player whose scoreboard title will be initialized.
     */
    private void initTitle(Player player) {
        Objective obj = scoreboards.get(player).getObjective("[CrispyCommons]");
        Component text = data.getTitle().getFromContext(OfflinePlayer.class, player);
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text)));
    }

    /**
     * Initializes the lines on the scoreboard for a player, starting from a specified index.
     *
     * @param player the player whose scoreboard lines will be initialized.
     * @param startIndex the index from which to start initializing lines.
     */
    private void initLines(Player player, int startIndex) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = startIndex; i < data.getLines().size(); i++) {
            Objective obj = scoreboard.getObjective("[CrispyCommons]");

            Component text = data.getLines().get(i).getFromContext(OfflinePlayer.class, player);
            String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
            String teamEntry = getEntry(player, line, scoreboard);
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            team.addEntry(teamEntry);
            team.setPrefix(getPrefix(line));
            team.setSuffix(getSuffix(line, teamEntry));
            obj.getScore(teamEntry).setScore(15 - i);
        }
    }

    /**
     * Removes lines from the scoreboard starting from a specified index.
     *
     * @param player the player whose scoreboard lines will be removed.
     * @param startIndex the index from which to start removing lines.
     * @param size the number of lines to remove.
     */
    private void removeLines(Player player, int startIndex, int size) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = startIndex; i < size; i++) {
            Team team = scoreboard.getTeam(String.valueOf(i));
            team.unregister();
        }
    }

    /**
     * Extracts the prefix from a scoreboard line, ensuring it fits within the character limit.
     *
     * @param line the full line of text.
     * @return the extracted prefix.
     */
    private String getPrefix(String line) {
        if (line.length() <= 16) {
            return line;
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return line.substring(0, 15);
        }
        return line.substring(0, 16);
    }

    /**
     * Retrieves a unique entry string for a scoreboard team.
     *
     * @param player the player for whom the entry is being created.
     * @param line the line of text to be used for the entry.
     * @param bukkitScoreboard the player's scoreboard.
     * @return a unique entry string.
     */
    private String getEntry(Player player, String line, Scoreboard bukkitScoreboard) {
        if (line.length() < 16) {
            return getUniqueEntry(player, "", bukkitScoreboard);
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return getUniqueEntry(player, line.substring(15, 17), bukkitScoreboard);
        }
        return getUniqueEntry(player, ChatColor.getLastColors(line.substring(0, 16)), bukkitScoreboard);
    }

    /**
     * Extracts the suffix from a scoreboard line, considering color codes and fitting within the character limit.
     *
     * @param line the full line of text.
     * @param lastEntry the last entry used in the scoreboard.
     * @return the extracted suffix.
     */
    private String getSuffix(String line, String lastEntry) {
        if (line.length() <= 16) {
            return "";
        }
        String lastEntryColor = getStrippedColors(ChatColor.getLastColors(lastEntry));
        String lastPrefixColor = getStrippedColors(ChatColor.getLastColors(line.substring(0, 16)));
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            int startIndex = line.substring(15, 17).equals(lastEntryColor) ? 17 : 15;
            return line.substring(startIndex);
        } else if (lastPrefixColor.equals(lastEntryColor)) {
            return line.substring(16);
        } else {
            String finalString = line.substring(16);
            finalString = lastPrefixColor + finalString;
            return finalString.substring(0, Math.min(finalString.length(), 16));
        }
    }

    /**
     * Generates a unique entry string for a scoreboard, avoiding conflicts with existing entries.
     *
     * @param player the player for whom the entry is being generated.
     * @param suffix the suffix string to be used in the entry.
     * @param bukkitScoreboard the player's scoreboard.
     * @return a unique entry string.
     */
    private String getUniqueEntry(Player player, String suffix, Scoreboard bukkitScoreboard) {
        StringBuilder finalEntry = new StringBuilder(suffix);
        HashMap<String, Integer> timesUsed;
        if (!timesUsedInEntries.containsKey(player)) {
            timesUsed = new HashMap<>();
            timesUsedInEntries.put(player, timesUsed);
        } else {
            timesUsed = timesUsedInEntries.get(player);
        }

        if (!timesUsed.containsKey(suffix)) {
            timesUsed.put(suffix, 1);
            return finalEntry.toString();
        }

        int times = timesUsed.get(suffix);
        for (int i = 0; i < times; i++) {
            finalEntry.insert(0, ChatColor.translateAlternateColorCodes('&', "&r"));
        }
        timesUsed.put(suffix, times + 1);
        timesUsedInEntries.put(player, timesUsed);
        return finalEntry.toString();
    }

    /**
     * Strips color codes from a string.
     *
     * @param lastColors the string containing color codes.
     * @return the string without color codes.
     */
    private String getStrippedColors(String lastColors) {
        if (lastColors.isEmpty()) {
            return "";
        }
        int lastReset = lastColors.lastIndexOf(ChatColor.translateAlternateColorCodes('&', "&r"));
        lastReset = lastReset == -1 ? 0 : lastReset + 2;

        return lastColors.substring(lastReset);
    }

    /**
     * Resets the scoreboard for a player, reinitializing it with updated data.
     *
     * @param p the player whose scoreboard will be reset.
     * @param teamSize the number of teams in the scoreboard.
     */
    private void resetScoreboard(Player p, int teamSize) {
        timesUsedInEntries.put(p, new HashMap<>());
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        p.getScoreboard().getObjective("[CrispyCommons]").unregister();
        p.setScoreboard(getNewBoard(p));
        removeLines(p, 0, teamSize);
        initLines(p, 0);
    }

    /**
     * Creates a new scoreboard for a player.
     *
     * @param p the player for whom the scoreboard will be created.
     * @return the new scoreboard.
     */
    private Scoreboard getNewBoard(Player p) {
        Scoreboard board = p.getScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }

    /**
     * Removes the sidebar from the player's scoreboard.
     *
     * @param p the player whose sidebar will be removed.
     * @return the updated scoreboard.
     */
    private Scoreboard removeSidebar(Player p) {
        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective("[CrispyCommons]");
        if (objective != null) {
            objective.unregister();
        }

        for (int i = 0; i < data.getLines().size(); i++) {
            Team t = board.getTeam(String.valueOf(i));
            if (t != null) {
                t.unregister();
            }
        }
        return board;
    }
}

