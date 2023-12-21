package dev.acrispycookie.crispycommons.implementations.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.CrispyHologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PublicCrispyHologram extends SimpleCrispyHologram implements Listener {

    public PublicCrispyHologram(JavaPlugin plugin, ArrayList<CrispyHologramLine> text, Location location, int timeToLive) {
        super(plugin, Bukkit.getOnlinePlayers(), text, location, timeToLive);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        removePlayer(e.getPlayer());
    }

    @Override
    public void destroy() {
        HandlerList.unregisterAll(this);
        receiverList.forEach(this::hideFromPlayer);
    }
}
