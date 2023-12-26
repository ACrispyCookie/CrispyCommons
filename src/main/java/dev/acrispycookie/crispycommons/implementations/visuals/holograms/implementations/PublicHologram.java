package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;

public class PublicHologram extends SimpleHologram implements Listener {

    public PublicHologram(Location location, int timeToLive, Collection<? extends Player> receivers) {
        super(location, timeToLive, receivers);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }
}
