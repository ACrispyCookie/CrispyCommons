package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
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

    private CrispyHologram mainNameHologram;
    private CrispyHologram aboveNameHologram;
    private CrispyHologram belowNameHologram;

    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().equals(data.getPlayer())) {
            if (isDisplayed && aboveNameHologram != null)
                aboveNameHologram.hide();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().equals(data.getPlayer())) {
            if (isDisplayed && aboveNameHologram != null)
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
        removeVanillaBelow(p);
        if (aboveNameHologram != null)
            aboveNameHologram.hide();
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

    private void showBelowName(Player p) {
        String below = getElement(data.getBelowName(), data.getPlayer(), p);
        int value = getElement(data.getBelowNameValue(), data.getPlayer(), p);

        setVanillaBelowName(p, below, value);
    }

    private void updateBelowName(Player p) {
        String below = getElement(data.getBelowName(), data.getPlayer(), p);
        int value = getElement(data.getBelowNameValue(), data.getPlayer(), p);

        updateVanillaBelowName(p, below, value);
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

    private void showNameTag(Player p) {
        String prefix = getElement(data.getPrefix(), data.getPlayer(), p);
        String suffix = getElement(data.getSuffix(), data.getPlayer(), p);

        /*
        if (prefix.length() > 16 || suffix.length() > 16) {
            if (mainNameHologram == null) {
                mainNameHologram = getMainHologram();
                mainNameHologram.show();
            }
            mainNameHologram.addPlayer(p);
            hideVanillaNameTag(p);
            return;
        }
        */

        setVanillaNameTag(p, prefix, suffix);
    }

    private void updateNameTag(Player p) {
        if (mainNameHologram != null) {
            return;
        }

        String prefix = getElement(data.getPrefix(), data.getPlayer(), p);
        String suffix = getElement(data.getSuffix(), data.getPlayer(), p);
        updateVanillaNameTag(p, prefix, suffix);
    }

    private void hideNameTag(Player p) {
        if (mainNameHologram != null) {
            mainNameHologram.hide();
            return;
        }

        removeVanillaNameTag(p);
    }

    private void updateVanillaNameTag(Player receiver, String prefix, String suffix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix.substring(0, 16)));
        t.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix.substring(0, 16)));
    }

    private void setVanillaNameTag(Player receiver, String prefix, String suffix) {
        Scoreboard scoreboard = receiver.getScoreboard();
        Team t = scoreboard.getTeam(data.getPlayer().getName()) == null ?
                scoreboard.registerNewTeam(data.getPlayer().getName()) :
                scoreboard.getTeam(data.getPlayer().getName());
        t.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix.substring(0, 16)));
        t.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix.substring(0, 16)));
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
        Objective objective = scoreboard.registerNewObjective(data.getPlayer().getName(), "dummy");
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', below));
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.getScore(data.getPlayer().getName()).setScore(value);
        receiver.setScoreboard(scoreboard);
    }

    private void removeVanillaBelow(Player receiver) {
        Scoreboard scoreboard = receiver.getScoreboard();
        scoreboard.getObjective(data.getPlayer().getName()).unregister();
        receiver.setScoreboard(scoreboard);
    }

    private CrispyHologram getMainHologram() {
        TextElement<?> prefix = data.getPrefix().convertToTextElement(data.getPlayer());
        TextElement<?> suffix = data.getSuffix().convertToTextElement(data.getPlayer());
        TextElement<?> line = prefix.add(TextElement.simple(data.getPlayer().getName())).add(suffix);

        return CrispyHologram.builder()
                .addLine(line)
                .setLocation(GeneralElement.dynamic(() -> data.getPlayer().getLocation().add(0, 0.8, 0), 1, false))
                .build();
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
