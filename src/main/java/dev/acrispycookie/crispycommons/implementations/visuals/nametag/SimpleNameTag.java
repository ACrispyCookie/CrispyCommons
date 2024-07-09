package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

public class SimpleNameTag extends AbstractNametag {

    private CrispyHologram mainNameHologram;
    private CrispyHologram aboveNameHologram;
    private CrispyHologram belowNameHologram;

    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
        Component aboveName = null; // FIX
        if (!StringUtils.isEmptyOrWhitespaceOnly(LegacyComponentSerializer.legacyAmpersand().serialize(aboveName)))
            //TODO: Add hologram builder
            return;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }

    @Override
    protected void show(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

        Component aboveName = data.getAboveName() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getAboveName()).getRaw() :
                ((PersonalTextElement) data.getAboveName()).getRaw(p);

        if (!StringUtils.isEmptyOrWhitespaceOnly(LegacyComponentSerializer.legacyAmpersand().serialize(aboveName)))
            aboveNameHologram.addPlayer(p);
    }

    @Override
    protected void hide(Player p) {

    }

    @Override
    protected void perPlayerUpdate(Player p) {
        if (!p.canSee(data.getPlayer()))
            return;

    }

    @Override
    protected void globalUpdate() {

    }
}
