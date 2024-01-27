package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

public class SimpleNameTag extends AbstractNametag implements Listener {

    private CrispyHologram mainNameHologram;
    private CrispyHologram aboveNameHologram;
    private CrispyHologram belowNameHologram;

    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
        this.receivers.addAll(Bukkit.getOnlinePlayers());
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
        if (!StringUtils.isEmptyOrWhitespaceOnly(LegacyComponentSerializer.legacyAmpersand().serialize(data.getAboveName().getRaw())))
            //TODO: Add hologram builder
            return;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        receivers.add(e.getPlayer());
    }

    @Override
    protected void showPlayer(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

        if (!StringUtils.isEmptyOrWhitespaceOnly(LegacyComponentSerializer.legacyAmpersand().serialize(data.getAboveName().getRaw())))
            aboveNameHologram.addPlayer(p);
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
