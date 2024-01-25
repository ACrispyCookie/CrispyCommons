package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractCrispyScoreboard;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class SimpleScoreboard extends AbstractCrispyScoreboard implements Listener {

    public SimpleScoreboard(ScoreboardTitleLine title, Collection<? extends AbstractScoreboardLine> lines, Collection<? extends Player> receivers) {
        super(title, new ArrayList<>(lines), new HashSet<>(receivers));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
