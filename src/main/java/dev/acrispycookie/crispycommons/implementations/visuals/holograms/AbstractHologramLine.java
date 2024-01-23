package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyVisual;
import org.bukkit.entity.Player;

public abstract class AbstractHologramLine<T extends AnimatedElement<?>> extends AbstractCrispyVisual<T> implements HologramLine<T> {

    protected T element;
    protected CrispyHologram hologram;
    protected abstract void show(Player player);
    protected abstract void hide(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(T element) {
        this.element = element;
        this.hologram = null;
    }

    void show() {
        element.start();
        hologram.getPlayers().forEach(this::show);
        isDisplayed = true;
        hologram.update();
    }

    void hide() {
        element.stop();
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
    public T getCurrentContent() {
        return element;
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
