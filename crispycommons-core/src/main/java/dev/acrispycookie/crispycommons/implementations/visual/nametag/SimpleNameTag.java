package dev.acrispycookie.crispycommons.implementations.visual.nametag;

import dev.acrispycookie.crispycommons.api.visual.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.data.NameTagData;
import dev.acrispycookie.crispycommons.implementations.element.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.element.ContextMap;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.*;

import java.util.Set;

/**
 * A concrete implementation of {@link AbstractNameTag} that represents a simple name tag visual element.
 * <p>
 * {@code SimpleNameTag} manages the display of a player's name tag, including the prefix, suffix,
 * text above the name, and text below the name. This implementation also integrates with the Minecraft
 * scoreboard system to handle name tag visibility and display.
 * </p>
 */
public class SimpleNameTag extends AbstractNameTag {

    /**
     * Constructs a {@code SimpleNameTag} instance with the specified parameters.
     *
     * @param data       the {@link NameTagData} containing the data for the name tag elements.
     * @param receivers  the set of players who will receive the name tag.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the name tag.
     * @param isPublic   whether the name tag should be visible to all players.
     */
    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.GLOBAL, isPublic);
    }

    /**
     * Shows the name tag to the specified player, including prefix, suffix, above-name, and below-name texts.
     *
     * @param p the player to whom the name tag will be shown.
     */
    @Override
    protected void show(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

        showNameTag(p);
        showAboveName(p);
        showBelowName(p);
    }

    /**
     * Hides the name tag from the specified player.
     *
     * @param p the player from whom the name tag will be hidden.
     */
    @Override
    protected void hide(Player p) {
        hideNameTag(p);
        hideBelowName(p);
        if (aboveNameHologram != null)
            aboveNameHologram.removePlayer(p);
    }

    /**
     * Updates the name tag for a specific player, re-showing it if the player can still see the tagged player.
     *
     * @param p the player for whom the name tag will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {

    }

    /**
     * This implementation does not use global updates, so this method is left empty.
     */
    @Override
    protected void globalUpdate() {
        updatePrefix();
        updateSuffix();
        updateBelowName();
        updateBelowNameValue();
        updateAboveName();
    }

    @Override
    public void updatePrefix() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            String prefix = getElement(data.getPrefix(), data.getPlayer(), player);
            updateVanillaNameTagPrefix(player, prefix);
        }

    }

    @Override
    public void updateSuffix() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            String suffix = getElement(data.getSuffix(), data.getPlayer(), player);
            updateVanillaNameTagSuffix(player, suffix);
        }
    }

    @Override
    public void updateBelowName() {
        if (data.getBelowName() == null)
            return;

        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            String below = getElement(data.getBelowName(), data.getPlayer(), player);
            updateVanillaBelowName(player, below);
        }
    }

    @Override
    public void updateBelowNameValue() {
        if (data.getBelowName() == null)
            return;

        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            int value = getElement(data.getBelowNameValue(), data.getPlayer(), player);
            updateVanillaBelowNameValue(player, value);
        }
    }

    @Override
    public void updateAboveName() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            if (!player.canSee(data.getPlayer())) {
                hide(player);
            } else if (!aboveNameHologram.getPlayers().contains(player)) {
                showAboveName(player);
            }
        }
    }

    /**
     * Shows the above-name hologram to the specified player, initializing it if necessary.
     *
     * @param p the player to whom the above-name hologram will be shown.
     */
    private void showAboveName(Player p) {
        if (data.getAboveName() == null)
            return;
        if (aboveNameHologram == null) {
            aboveNameHologram = getAboveNameHologram();
            aboveNameHologram.show();
        }
        aboveNameHologram.addPlayer(p);
    }

    /**
     * Shows the below-name text to the specified player.
     *
     * @param p the player to whom the below-name text will be shown.
     */
    private void showBelowName(Player p) {
        if (data.getBelowName() == null || data.getBelowNameValue() == null)
            return;

        String below = getElement(data.getBelowName(), data.getPlayer(), p);
        int value = getElement(data.getBelowNameValue(), data.getPlayer(), p);

        setVanillaBelowName(p, below, value);
    }

    /**
     * Hides the below-name text from the specified player.
     *
     * @param p the player from whom the below-name text will be hidden.
     */
    private void hideBelowName(Player p) {
        if (data.getBelowName() == null || data.getBelowNameValue() == null)
            return;

        removeVanillaBelowName(p);
    }

    /**
     * Shows the name tag (prefix and suffix) to the specified player.
     *
     * @param p the player to whom the name tag will be shown.
     */
    private void showNameTag(Player p) {
        String prefix = getElement(data.getPrefix(), data.getPlayer(), p);
        String suffix = getElement(data.getSuffix(), data.getPlayer(), p);

        setVanillaNameTag(p, prefix, suffix);
    }

    /**
     * Hides the name tag from the specified player.
     *
     * @param p the player from whom the name tag will be hidden.
     */
    private void hideNameTag(Player p) {
        if (data.getPrefix() == null && data.getSuffix() == null)
            return;
        removeVanillaNameTag(p);
    }

    /**
     * Updates the player's vanilla name tag with the specified prefix.
     *
     * @param receiver the player who will see the updated name tag.
     * @param prefix   the prefix to display on the name tag.
     */
    private void updateVanillaNameTagPrefix(Player receiver, String prefix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix.substring(0, Math.min(16, prefix.length()))));
    }

    /**
     * Updates the player's vanilla name tag with the specified suffix.
     *
     * @param receiver the player who will see the updated name tag.
     * @param suffix   the suffix to display on the name tag.
     */
    private void updateVanillaNameTagSuffix(Player receiver, String suffix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName());
        t.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix.substring(0, Math.min(16, suffix.length()))));
    }

    /**
     * Sets the player's vanilla name tag with the specified prefix and suffix.
     *
     * @param receiver the player who will see the name tag.
     * @param prefix   the prefix to display on the name tag.
     * @param suffix   the suffix to display on the name tag.
     */
    private void setVanillaNameTag(Player receiver, String prefix, String suffix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName()) == null ?
                scoreboard.registerNewTeam(data.getPlayer().getName()) :
                scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix.substring(0, Math.min(16, prefix.length()))));
        t.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix.substring(0, Math.min(16, suffix.length()))));
        t.setNameTagVisibility(NameTagVisibility.ALWAYS);
        t.addEntry(data.getPlayer().getName());
        receiver.setScoreboard(scoreboard);
    }

    /**
     * Removes the player's vanilla name tag.
     *
     * @param receiver the player who will no longer see the name tag.
     */
    private void removeVanillaNameTag(Player receiver) {
        Scoreboard scoreboard = receiver.getScoreboard();
        if (scoreboard.getTeam(data.getPlayer().getName()) == null)
            return;
        Team t = scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix("");
        t.setSuffix("");
        t.setNameTagVisibility(NameTagVisibility.ALWAYS);
        t.removeEntry(data.getPlayer().getName());
        receiver.setScoreboard(scoreboard);
    }

    /**
     * Updates the player's vanilla below-name text with the specified text and value.
     *
     * @param receiver the player who will see the updated below-name text.
     * @param below    the text to display below the name.
     */
    private void updateVanillaBelowName(Player receiver, String below) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Objective objective = scoreboard.getObjective(data.getPlayer().getName());
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', below));
    }

    /**
     * Updates the player's vanilla below-name value with the specified value.
     *
     * @param receiver the player who will see the updated below-name text.
     * @param value    the value to display below the name.
     */
    private void updateVanillaBelowNameValue(Player receiver, int value) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Objective objective = scoreboard.getObjective(data.getPlayer().getName());
        objective.getScore(data.getPlayer().getName()).setScore(value);
    }

    /**
     * Sets the player's vanilla below-name text with the specified text and value.
     *
     * @param receiver the player who will see the below-name text.
     * @param below    the text to display below the name.
     * @param value    the value to display below the name.
     */
    private void setVanillaBelowName(Player receiver, String below, int value) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Objective objective = scoreboard.getObjective(data.getPlayer().getName()) == null ?
                scoreboard.registerNewObjective(data.getPlayer().getName(), "dummy") :
                scoreboard.getObjective(data.getPlayer().getName());
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', below));
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.getScore(data.getPlayer().getName()).setScore(value);
        receiver.setScoreboard(scoreboard);
    }

    /**
     * Removes the player's vanilla below-name text.
     *
     * @param receiver the player who will no longer see the below-name text.
     */
    private void removeVanillaBelowName(Player receiver) {
        Scoreboard scoreboard = receiver.getScoreboard();
        if (scoreboard.getObjective(data.getPlayer().getName()) == null)
            return;
        scoreboard.getObjective(data.getPlayer().getName()).unregister();
        receiver.setScoreboard(scoreboard);
    }

    /**
     * Constructs and returns a hologram to be displayed above the player's name.
     *
     * @return the constructed {@link CrispyHologram}.
     */
    private CrispyHologram getAboveNameHologram() {
        TextElement<?> line = data.getAboveName().convertToTextElement(data.getPlayer());

        return CrispyHologram.builder()
                .addLine(line)
                .setLocation(GeneralElement.dynamic(() -> data.getPlayer().getLocation().add(0, 2.37, 0), 0, 1, false))
                .setPublic(isPublic)
                .build();
    }

    /**
     * Retrieves the appropriate element value from the context of the player and receiver.
     *
     * @param element  the {@link NameTagElement} to retrieve.
     * @param player   the player associated with the name tag.
     * @param receiver the player viewing the name tag.
     * @param <T>      the type of the element.
     * @return the value of the element from the context.
     */
    private <T> T getElement(NameTagElement<T, ?> element, Player player, Player receiver) {
        ContextMap co = new ContextMap()
                .add(OfflinePlayer.class, player)
                .add(NameTagContext.class, new NameTagContext(player, receiver));
        return element.getFromContext(co);
    }
}

