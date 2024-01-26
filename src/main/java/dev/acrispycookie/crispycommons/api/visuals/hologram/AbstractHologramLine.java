package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramLineData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public abstract class AbstractHologramLine<T extends AnimatedElement<?>> extends AbstractVisual<HologramLineData<T>> implements HologramLine<T> {

    protected abstract void show(Player player);
    protected abstract void hide(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(HologramLineData<T> data) {
        super(data);
    }

    void show() {
        data.getElement().start();
        data.getHologram().getPlayers().stream().filter(OfflinePlayer::isOnline).forEach(this::show);
        isDisplayed = true;
        data.getHologram().update();
    }

    void hide() {
        data.getElement().stop();
        data.getHologram().getPlayers().stream().filter(OfflinePlayer::isOnline).forEach(this::hide);
        isDisplayed = false;
        data.getHologram().update();
    }

    protected void update() {
        if (!isDisplayed)
            return;

        data.getHologram().getPlayers().stream().filter(OfflinePlayer::isOnline).forEach(this::update);
    }

    @Override
    public CrispyHologram getHologram() {
        return data.getHologram();
    }

    @Override
    public void setHologram(CrispyHologram hologram) {
        data.setHologram(hologram);
    }

}
