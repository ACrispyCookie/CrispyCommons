package dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines;

import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramLineData;

public abstract class ClickableHologramLine<T extends AnimatedElement<?>> extends AbstractHologramLine<T> {
    public ClickableHologramLine(T element) {
        super(new HologramLineData<>(element, 0, null));
    }
}
