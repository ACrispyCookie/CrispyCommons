package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.AbstractHologramLine;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

public abstract class ClickableHologramLine<T extends AnimatedElement<K>, K> extends AbstractHologramLine<T, K> {
    public ClickableHologramLine(T element) {
        super(element);
    }
}
