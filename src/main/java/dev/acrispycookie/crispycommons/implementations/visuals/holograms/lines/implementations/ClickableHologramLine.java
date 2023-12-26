package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.AbstractHologramLine;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public abstract class ClickableHologramLine<T extends AnimatedElement<K>, K> extends AbstractHologramLine<T, K> {
    public ClickableHologramLine(T element) {
        super(element);
    }
}
