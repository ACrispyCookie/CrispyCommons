package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

public class PublicNameTag extends SimpleNameTag {

    public PublicNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive);
        setPlayers(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
