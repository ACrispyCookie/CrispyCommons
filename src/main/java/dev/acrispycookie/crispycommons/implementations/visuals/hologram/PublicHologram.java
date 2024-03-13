package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;

public class PublicHologram extends SimpleHologram {

    public PublicHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }
}
