package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyVisual;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractHologramLine<T extends AnimatedElement<K>, K> extends AbstractCrispyVisual<K> implements HologramLine<K> {

    protected T element;
    protected CrispyHologram hologram;

    public AbstractHologramLine(T element) {
        this.element = element;
        this.hologram = null;
    }

    @Override
    public void show() {
        if (isDisplayed || hologram == null) {
            return;
        }

        element.start();
        hologram.getPlayers().forEach(this::show);
        isDisplayed = true;
        hologram.update();
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        element.stop();
        hologram.getPlayers().forEach(this::hide);
        isDisplayed = false;
        hologram.update();
    }

    @Override
    public void update() {
        if (isDisplayed) {
            hologram.getPlayers().forEach(this::update);
        }
    }

    @Override
    public K getCurrentContent() {
        return element.getContent();
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
