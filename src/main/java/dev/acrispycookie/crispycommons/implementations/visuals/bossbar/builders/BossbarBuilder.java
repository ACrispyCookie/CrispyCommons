package dev.acrispycookie.crispycommons.implementations.visuals.bossbar.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.bossbar.CrispyBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.SimpleBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

import java.util.Collection;

public class BossbarBuilder extends AbstractVisualBuilder<CrispyBossbar> {

    private TextElement text;
    private float progress;
    private BossBar.Color color;
    private BossBar.Overlay overlay;
    private int timeToLive = -1;
    private CrispyBossbar bossbar;

    public BossbarBuilder(Collection<? extends Player> players) {
        super(players);
    }

    public BossbarBuilder() {
    }

    public BossbarBuilder setText(TextElement text) {
        this.text = text;
        this.text.setUpdate(() -> bossbar.update());
        return this;
    }

    public BossbarBuilder setProgress(float progress) {
        this.progress = progress;
        return this;
    }

    public BossbarBuilder setColor(BossBar.Color color) {
        this.color = color;
        return this;
    }

    public BossbarBuilder setOverlay(BossBar.Overlay overlay) {
        this.overlay = overlay;
        return this;
    }

    public BossbarBuilder setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }

    @Override
    public CrispyBossbar build() {
        BossbarData data = new BossbarData(timeToLive, progress, color, overlay, text);
        this.bossbar = new SimpleBossbar(data, receivers);
        return bossbar;
    }
}
