package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;

public class PublicHologram extends SimpleHologram implements Listener {

    public PublicHologram(Collection<? extends AbstractHologramLine<?>> lines, Location location, int timeToLive, Collection<? extends Player> receivers) {
        super(lines, location, timeToLive, receivers);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }
}
