package dev.acrispycookie.crispycommons.api.visuals.abstraction.builder;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVisualBuilder<T> implements VisualBuilder<T> {

    protected final Set<Player> receivers = new HashSet<>();

    public AbstractVisualBuilder(Collection<? extends Player> initialReceivers) {
        addPlayers(initialReceivers);
    }

    public AbstractVisualBuilder() {

    }

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
}
