package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.NameTagElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.utility.elements.ContextMap;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class SimpleNameTag extends AbstractNameTag {

    private CrispyHologram aboveNameHologram;

    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().equals(data.getPlayer())) {
            if (isAnyoneWatching() && aboveNameHologram != null)
                aboveNameHologram.hide();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().equals(data.getPlayer())) {
            if (isAnyoneWatching() && aboveNameHologram != null)
                aboveNameHologram.show();
        }
    }

    @Override
    protected void show(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

        showNameTag(p);
        showAboveName(p);
        showBelowName(p);
    }

    @Override
    protected void hide(Player p) {
        hideNameTag(p);
        hideBelowName(p);
        if (aboveNameHologram != null)
            aboveNameHologram.removePlayer(p);
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        if (!p.canSee(data.getPlayer())) {
            hide(p);
            return;
        }

        updateNameTag(p);
        updateBelowName(p);
    }

    @Override
    protected void globalUpdate() {

    }

    private void showAboveName(Player p) {
        if (data.getAboveName() == null)
            return;
        if (aboveNameHologram == null) {
            aboveNameHologram = getAboveNameHologram();
            aboveNameHologram.show();
        }
        aboveNameHologram.addPlayer(p);
    }

    private void showBelowName(Player p) {
        if (data.getBelowName() == null || data.getBelowNameValue() == null)
            return;

        String below = getElement(data.getBelowName(), data.getPlayer(), p);
        int value = getElement(data.getBelowNameValue(), data.getPlayer(), p);

        setVanillaBelowName(p, below, value);
    }

    private void updateBelowName(Player p) {
        if (data.getBelowName() == null || data.getBelowNameValue() == null)
            return;

        String below = getElement(data.getBelowName(), data.getPlayer(), p);
        int value = getElement(data.getBelowNameValue(), data.getPlayer(), p);

        updateVanillaBelowName(p, below, value);
    }

    private void hideBelowName(Player p) {
        if (data.getBelowName() == null || data.getBelowNameValue() == null)
            return;

        removeVanillaBelowName(p);
    }

    private void showNameTag(Player p) {
        String prefix = getElement(data.getPrefix(), data.getPlayer(), p);
        String suffix = getElement(data.getSuffix(), data.getPlayer(), p);

        setVanillaNameTag(p, prefix, suffix);
    }

    private void updateNameTag(Player p) {
        String prefix = getElement(data.getPrefix(), data.getPlayer(), p);
        String suffix = getElement(data.getSuffix(), data.getPlayer(), p);
        updateVanillaNameTag(p, prefix, suffix);
    }

    private void hideNameTag(Player p) {
        if (data.getPrefix() == null && data.getSuffix() == null)
            return;
        removeVanillaNameTag(p);
    }

    private void updateVanillaNameTag(Player receiver, String prefix, String suffix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix.substring(0, Math.min(16, prefix.length()))));
        t.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix.substring(0, Math.min(16, suffix.length()))));
    }

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

    private void updateVanillaBelowName(Player receiver, String below, int value) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Objective objective = scoreboard.getObjective(data.getPlayer().getName());
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', below));
        objective.getScore(data.getPlayer().getName()).setScore(value);
    }

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

    private void removeVanillaBelowName(Player receiver) {
        Scoreboard scoreboard = receiver.getScoreboard();
        if (scoreboard.getObjective(data.getPlayer().getName()) == null)
            return;
        scoreboard.getObjective(data.getPlayer().getName()).unregister();
        receiver.setScoreboard(scoreboard);
    }

    private CrispyHologram getAboveNameHologram() {
        TextElement<?> line = data.getAboveName().convertToTextElement(data.getPlayer());

        return CrispyHologram.builder()
                .addLine(line)
                .setLocation(GeneralElement.dynamic(() -> data.getPlayer().getLocation().add(0, 1.1, 0), 1, false))
                .build();
    }

    private <T> T getElement(NameTagElement<T, ?> element, Player player, Player receiver) {
        ContextMap co = new ContextMap()
                .add(OfflinePlayer.class, player)
                .add(NameTagContext.class, new NameTagContext(player, receiver));
        return element.getFromContext(co);
    }
}
