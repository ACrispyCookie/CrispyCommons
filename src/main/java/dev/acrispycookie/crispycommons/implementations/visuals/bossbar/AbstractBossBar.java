package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.bossbar.CrispyBossBar;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossBarData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Set;

public abstract class AbstractBossBar extends AbstractVisual<BossBarData> implements CrispyBossBar {

    AbstractBossBar(BossBarData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (getPlayers().contains(event.getPlayer()) && isDisplayed)
            Bukkit.getScheduler().runTaskLater(CrispyCommons.getPlugin(), () -> show(event.getPlayer()), 20L);
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
    public void setText(TextElement<?> text) {
        data.getText().stop();
        data.setText(text);
        data.getText().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getText().start();
            update();
        }
    }

    @Override
    public void setProgress(GeneralElement<Float, ?> progress) {
        data.getProgress().stop();
        data.setProgress(progress);
        data.getProgress().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getProgress().start();
            update();
        }
    }

    @Override
    public void setColor(GeneralElement<BossBar.Color, ?> color) {
        data.getColor().stop();
        data.setColor(color);
        data.getColor().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getColor().start();
            update();
        }
    }

    @Override
    public void setOverlay(GeneralElement<BossBar.Overlay, ?> overlay) {
        data.getOverlay().stop();
        data.setOverlay(overlay);
        data.getOverlay().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getOverlay().start();
            update();
        }
    }

    @Override
    public TextElement<?> getText() {
        return data.getText();
    }

    @Override
    public GeneralElement<Float, ?> getProgress() {
        return data.getProgress();
    }

    @Override
    public GeneralElement<BossBar.Color, ?> getColor() {
        return data.getColor();
    }

    @Override
    public GeneralElement<BossBar.Overlay, ?> getOverlay() {
        return data.getOverlay();
    }
}
