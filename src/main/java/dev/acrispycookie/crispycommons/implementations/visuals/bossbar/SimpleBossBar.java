package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossBarData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class SimpleBossBar extends AbstractBossBar {

    private final HashMap<OfflinePlayer, BossBar> bossBars = new HashMap<>();

    public SimpleBossBar(BossBarData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    protected void show(Player p) {
        bossBars.put(p, BossBar.bossBar(data.getText().getFromContext(OfflinePlayer.class, p),
                data.getProgress().getFromContext(OfflinePlayer.class, p),
                data.getColor().getFromContext(OfflinePlayer.class, p),
                data.getOverlay().getFromContext(OfflinePlayer.class, p)));
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.showBossBar(bossBars.get(p));
    }

    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.hideBossBar(bossBars.get(p));
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        BossBar bossBar = bossBars.get(p);
        bossBar.name(data.getText().getFromContext(OfflinePlayer.class, p));
        bossBar.color(data.getColor().getFromContext(OfflinePlayer.class, p));
        bossBar.progress(data.getProgress().getFromContext(OfflinePlayer.class, p));
        bossBar.overlay(data.getOverlay().getFromContext(OfflinePlayer.class, p));
    }
}
