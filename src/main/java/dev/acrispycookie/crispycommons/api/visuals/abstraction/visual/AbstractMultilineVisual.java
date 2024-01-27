package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractMultilineVisual<T extends VisualData> extends AbstractVisual<T> implements CrispyMultilineVisual {
    public AbstractMultilineVisual(T data, Set<? extends Player> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }
}
