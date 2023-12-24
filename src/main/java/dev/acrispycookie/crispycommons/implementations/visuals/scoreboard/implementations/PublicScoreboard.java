package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PublicScoreboard extends SimpleScoreboard {
    public PublicScoreboard(ScoreboardTitleLine title) {
        super(title);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        addPlayer(event.getPlayer());
    }
}
