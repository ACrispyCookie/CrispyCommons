package dev.acrispycookie.crispycommons.holograms.implementations;

import dev.acrispycookie.crispycommons.text.CrispyText;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PublicCrispyHologram extends SimpleCrispyHologram implements Listener {

    public PublicCrispyHologram(JavaPlugin plugin, CrispyText text, Location location, int tickLifetime) {
        super(plugin, Bukkit.getOnlinePlayers(), text, location, tickLifetime);
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
