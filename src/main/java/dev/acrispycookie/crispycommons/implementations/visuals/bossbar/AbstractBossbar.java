package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.bossbar.CrispyBossbar;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractBossbar extends AbstractVisual<BossbarData> implements CrispyBossbar {

    AbstractBossbar(BossbarData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void prepareShow() {
        data.getText().start();
    }

    @Override
    protected void prepareHide() {
        data.getText().stop();
    }

    @Override
    protected void globalUpdate() {

    }

    @Override
    public void setText(TextElement text) {
        data.getText().stop();
        data.setText(text);
        data.getText().setUpdate(this::update);
        if (isDisplayed) {
            data.getText().start();
            update();
        }
    }

    @Override
    public void setProgress(GeneralElement<Float> progress) {
        data.getProgress().stop();
        data.setProgress(progress);
        data.getProgress().setUpdate(this::update);
        if (isDisplayed) {
            data.getProgress().start();
            update();
        }
    }

    @Override
    public void setColor(GeneralElement<BossBar.Color> color) {
        data.getColor().stop();
        data.setColor(color);
        data.getColor().setUpdate(this::update);
        if (isDisplayed) {
            data.getColor().start();
            update();
        }
    }

    @Override
    public void setOverlay(GeneralElement<BossBar.Overlay> overlay) {
        data.getOverlay().stop();
        data.setOverlay(overlay);
        data.getOverlay().setUpdate(this::update);
        if (isDisplayed) {
            data.getOverlay().start();
            update();
        }
    }

    @Override
    public TextElement getText() {
        return data.getText();
    }

    @Override
    public GeneralElement<Float> getProgress() {
        return data.getProgress();
    }

    @Override
    public GeneralElement<BossBar.Color> getColor() {
        return data.getColor();
    }

    @Override
    public GeneralElement<BossBar.Overlay> getOverlay() {
        return data.getOverlay();
    }
}
