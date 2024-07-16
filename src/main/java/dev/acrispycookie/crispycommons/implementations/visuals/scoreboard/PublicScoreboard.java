package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.HashSet;

public class PublicScoreboard extends SimpleScoreboard {
    public PublicScoreboard(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        addPlayer(event.getPlayer());
    }
}
