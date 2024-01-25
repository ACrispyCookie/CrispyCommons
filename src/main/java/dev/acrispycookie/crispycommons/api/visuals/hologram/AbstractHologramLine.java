package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.api.visuals.hologram.HologramLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyVisual;
import org.bukkit.entity.Player;

public abstract class AbstractHologramLine<T extends AnimatedElement<?>> extends AbstractCrispyVisual<T> implements HologramLine<T> {

    protected CrispyHologram hologram;
    protected abstract void show(Player player);
    protected abstract void hide(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(T element) {
        super(element);
        this.hologram = null;
    }

    void show() {
        content.start();
        hologram.getPlayers().forEach(this::show);
        isDisplayed = true;
        hologram.update();
    }

    void hide() {
        content.stop();
        hologram.getPlayers().forEach(this::hide);
        isDisplayed = false;
        hologram.update();
    }

    protected void update() {
        if (!isDisplayed)
            return;

        hologram.getPlayers().forEach(this::update);
    }

    @Override
    public CrispyHologram getHologram() {
        return hologram;
    }

    @Override
    public void setHologram(CrispyHologram hologram) {
        this.hologram = hologram;
    }

}
