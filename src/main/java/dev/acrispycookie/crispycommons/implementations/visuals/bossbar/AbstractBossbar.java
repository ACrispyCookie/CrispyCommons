package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.api.visuals.bossbar.CrispyBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractBossbar extends AbstractVisual<BossbarData> implements CrispyBossbar {

    AbstractBossbar(BossbarData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void prepareShow() {
        data.getText().start();
    }

    @Override
    public void prepareHide() {
        data.getText().stop();
    }

    @Override
    public void prepareUpdate() {
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
}
