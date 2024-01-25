package dev.acrispycookie.crispycommons.api.visuals.abstraction.builder;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface VisualBuilder<T> {

    VisualBuilder<T> addPlayer(Player p);
    VisualBuilder<T> removePlayer(Player p);
    VisualBuilder<T> setPlayers(Collection<? extends Player> p);
    VisualBuilder<T> addPlayers(Collection<? extends Player> p);
    T build();
}
