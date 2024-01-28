package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.audience.Audience;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleBossbar extends AbstractBossbar {

    public SimpleBossbar(BossbarData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.showBossBar(data.getBossBar());
    }

    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.hideBossBar(data.getBossBar());
    }

    @Override
    protected void perPlayerUpdate(Player p) {

    }
}
