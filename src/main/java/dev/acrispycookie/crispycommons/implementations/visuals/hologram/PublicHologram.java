package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;

public class PublicHologram extends SimpleHologram implements Listener {

    public PublicHologram(HologramData data, Collection<? extends Player> receivers) {
        super(data, receivers);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }
}
