package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractBossbar extends AbstractAccessibleVisual<BossbarData> implements CrispyBossbar  {

    protected abstract void showPlayer(Player p);
    protected abstract void hidePlayer(Player p);

    public AbstractBossbar(BossbarData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getText().start();
        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
        if (data.getTimeToLive() != -1)
            new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), data.getTimeToLive());
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        data.getText().stop();
        receivers.stream().filter(Player::isOnline).forEach(this::hidePlayer);
    }

    @Override
    public void update() {
        data.getBossBar().name(data.getText().getRaw());
    }

    @Override
    public void setText(TextElement text) {
        data.setText(text);
    }

    @Override
    public void setProgress(float progress) {
        data.setProgress(progress);
        data.getBossBar().progress(progress);
    }

    @Override
    public void setColor(BossBar.Color color) {
        data.setColor(color);
        data.getBossBar().color(color);
    }

    @Override
    public void setOverlay(BossBar.Overlay overlay) {
        data.setOverlay(overlay);
        data.getBossBar().overlay(overlay);
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        data.setTimeToLive(timeToLive);
    }

    @Override
    public float getProgress() {
        return data.getProgress();
    }

    @Override
    public BossBar.Color getColor() {
        return data.getColor();
    }

    @Override
    public BossBar.Overlay getOverlay() {
        return data.getOverlay();
    }

    @Override
    public int getTimeToLive() {
        return data.getTimeToLive();
    }
}
