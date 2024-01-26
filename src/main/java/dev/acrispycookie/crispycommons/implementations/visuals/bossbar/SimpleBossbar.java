package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.bossbar.AbstractBossbar;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleBossbar extends AbstractBossbar {

    public SimpleBossbar(TextElement content, Set<? extends Player> receivers, int timeToLive, float progress, BossBar.Color color, BossBar.Overlay overlay) {
        super(content, receivers, timeToLive, progress, color, overlay);
    }

    @Override
    protected void showPlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.showBossBar(bossBar);
    }

    @Override
    protected void hidePlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.hideBossBar(bossBar);
    }
}
