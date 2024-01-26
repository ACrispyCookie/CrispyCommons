package dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;

public class HologramLineData<T extends AnimatedElement<?>> implements VisualData {

    private T element;
    private int position;
    private CrispyHologram hologram;

    public HologramLineData(T element, int position, CrispyHologram hologram) {
        this.element = element;
        this.position = position;
        this.hologram = hologram;
    }

    public CrispyHologram getHologram() {
        return hologram;
    }

    public void setHologram(CrispyHologram hologram) {
        this.hologram = hologram;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
