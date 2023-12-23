package dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.AbstractHologramLine;
import dev.acrispycookie.crispycommons.utility.elements.CrispyElement;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class ClickableHologramLine<T extends CrispyElement<K>, K> extends AbstractHologramLine<T, K> {
    public ClickableHologramLine(T element, CrispyHologram hologram, List<Player> receivers) {
        super(element, hologram, receivers);
    }
}
