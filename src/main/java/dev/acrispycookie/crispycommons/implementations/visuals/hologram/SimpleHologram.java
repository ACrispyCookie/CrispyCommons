package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.HashSet;

public class SimpleHologram extends AbstractHologram implements Listener {

    public SimpleHologram(HologramData data, Collection<? extends Player> receivers) {
        super(data, new HashSet<>(receivers));
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
