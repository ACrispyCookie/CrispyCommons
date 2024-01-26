package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.nametag.AbstractNametag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

public class SimpleNameTag extends AbstractNametag implements Listener {

    public SimpleNameTag(NameTagData data, Set<? extends Player> receivers) {
        super(data, receivers);
        this.receivers.addAll(Bukkit.getOnlinePlayers());
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        receivers.add(e.getPlayer());
    }

    @Override
    protected void showPlayer(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;


    }

    @Override
    protected void hidePlayer(Player p) {

    }

    @Override
    protected void updatePlayer(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

    }
}
