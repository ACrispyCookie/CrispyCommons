package dev.acrispycookie.crispycommons.implementations.visuals.bossbar.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.bossbar.CrispyBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.SimpleBossbar;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class SimpleBossbarBuilder extends AbstractVisualBuilder<CrispyBossbar> {

    private TextElement text;
    private float progress;
    private BossBar.Color color;
    private BossBar.Overlay overlay;
    private int timeToLive = -1;
    private CrispyBossbar crispyBossbar;

    public SimpleBossbarBuilder(Collection<? extends Player> players) {
        super(players);
    }

    public SimpleBossbarBuilder() {
    }

    public SimpleBossbarBuilder setText(String text) {
        this.text = new SimpleTextElement(text);
        return this;
    }

    public SimpleBossbarBuilder setText(Collection<? extends String> frames, int period) {
        this.text = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                crispyBossbar.update();
            }
        };
        return this;
    }

    public SimpleBossbarBuilder setText(Supplier<? extends String> supplier, int period) {
        this.text = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                crispyBossbar.update();
            }
        };
        return this;
    }

    public SimpleBossbarBuilder setProgress(float progress) {
        this.progress = progress;
        return this;
    }

    public SimpleBossbarBuilder setColor(BossBar.Color color) {
        this.color = color;
        return this;
    }

    public SimpleBossbarBuilder setOverlay(BossBar.Overlay overlay) {
        this.overlay = overlay;
        return this;
    }

    public SimpleBossbarBuilder setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }

    @Override
    public CrispyBossbar build() {
        this.crispyBossbar = new SimpleBossbar(text, receivers, timeToLive, progress, color, overlay);
        return crispyBossbar;
    }
}
