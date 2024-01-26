package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractBossbar extends AbstractCrispyAccessibleVisual<TextElement> implements CrispyBossbar  {

    private int timeToLive;
    protected float progress;
    protected BossBar.Color color;
    protected BossBar.Overlay overlay;
    protected BossBar bossBar;
    protected abstract void showPlayer(Player p);
    protected abstract void hidePlayer(Player p);

    public AbstractBossbar(TextElement content, Set<? extends Player> receivers, int timeToLive, float progress, BossBar.Color color, BossBar.Overlay overlay) {
        super(content, receivers);
        this.timeToLive = timeToLive;
        this.progress = progress;
        this.color = color;
        this.overlay = overlay;
        this.bossBar = BossBar.bossBar(content.getRaw(), progress, color, overlay);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        content.start();
        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
        if (timeToLive != -1)
            new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), timeToLive);
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        content.stop();
        receivers.stream().filter(Player::isOnline).forEach(this::hidePlayer);
    }

    @Override
    public void update() {
        this.bossBar.name(content.getRaw());
    }

    @Override
    public void setText(TextElement text) {
        this.content = text;
    }

    @Override
    public void setProgress(float progress) {
        this.progress = progress;
        this.bossBar.progress(progress);
    }

    @Override
    public void setColor(BossBar.Color color) {
        this.color = color;
        this.bossBar.color(color);
    }

    @Override
    public void setOverlay(BossBar.Overlay overlay) {
        this.overlay = overlay;
        this.bossBar.overlay(overlay);
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public float getProgress() {
        return progress;
    }

    @Override
    public BossBar.Color getColor() {
        return color;
    }

    @Override
    public BossBar.Overlay getOverlay() {
        return overlay;
    }

    @Override
    public int getTimeToLive() {
        return timeToLive;
    }
}
