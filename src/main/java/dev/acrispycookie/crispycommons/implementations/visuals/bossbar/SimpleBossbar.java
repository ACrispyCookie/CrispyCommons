package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleBossbar extends AbstractBossbar {

    public SimpleBossbar(BossbarData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void showPlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.showBossBar(data.getBossBar());
    }

    @Override
    protected void hidePlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.hideBossBar(data.getBossBar());
    }
}
