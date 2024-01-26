package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractCrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.HashSet;

public class SimpleScoreboard extends AbstractCrispyScoreboard implements Listener {

    public SimpleScoreboard(ScoreboardData data, Collection<? extends Player> receivers) {
        super(data, new HashSet<>(receivers));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
