package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractCrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class SimpleScoreboard extends AbstractCrispyScoreboard implements Listener {

    public SimpleScoreboard(ScoreboardTitleLine title) {
        super(title);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
