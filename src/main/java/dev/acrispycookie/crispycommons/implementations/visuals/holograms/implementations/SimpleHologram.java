package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.HashSet;

public class SimpleHologram extends AbstractCrispyHologram implements Listener {

    public SimpleHologram(Location location, int timeToLive, Collection<? extends Player> receivers) {
        super(location, timeToLive, new HashSet<>(receivers));
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
