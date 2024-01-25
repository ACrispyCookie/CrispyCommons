package dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines;

import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;

public abstract class ClickableHologramLine<T extends AnimatedElement<?>> extends AbstractHologramLine<T> {
    public ClickableHologramLine(T element) {
        super(element);
    }
}
