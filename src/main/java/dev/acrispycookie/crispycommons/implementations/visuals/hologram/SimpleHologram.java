package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class SimpleHologram extends AbstractCrispyHologram implements Listener {

    public SimpleHologram(Collection<? extends AbstractHologramLine<?>> lines, Location location, int timeToLive, Collection<? extends Player> receivers) {
        super(new ArrayList<>(lines), location, timeToLive, new HashSet<>(receivers));
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
