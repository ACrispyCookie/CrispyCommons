package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PublicHologram extends SimpleHologram implements Listener {

    public PublicHologram(Location location, int timeToLive) {
        super(location, timeToLive);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        setPlayers(Bukkit.getOnlinePlayers());
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
    public void hide() {
        HandlerList.unregisterAll(this);
        super.hide();
    }
}
