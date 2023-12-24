package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SimpleHologram extends AbstractCrispyHologram implements Listener {

    public SimpleHologram(Location location, int timeToLive) {
        super(location, timeToLive);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
