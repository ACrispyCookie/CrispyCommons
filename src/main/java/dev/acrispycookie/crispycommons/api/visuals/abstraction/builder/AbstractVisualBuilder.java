package dev.acrispycookie.crispycommons.api.visuals.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVisualBuilder<T extends CrispyAccessibleVisual<?>> implements VisualBuilder<T> {

    protected final Set<Player> receivers = new HashSet<>();
    protected long timeToLive = 0;

    @Override
    public AbstractVisualBuilder<T> addPlayer(Player p) {
        receivers.add(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> removePlayer(Player p) {
        receivers.remove(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> setPlayers(Collection<? extends Player> p) {
        receivers.clear();
        receivers.addAll(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> addPlayers(Collection<? extends Player> p) {
        receivers.addAll(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }
}
