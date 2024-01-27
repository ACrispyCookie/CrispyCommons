package dev.acrispycookie.crispycommons.api.visuals.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import org.bukkit.OfflinePlayer;

import java.util.Collection;

public interface VisualBuilder<T extends CrispyVisual> {

    VisualBuilder<T> addPlayer(OfflinePlayer p);
    VisualBuilder<T> removePlayer(OfflinePlayer p);
    VisualBuilder<T> setPlayers(Collection<? extends OfflinePlayer> p);
    VisualBuilder<T> addPlayers(Collection<? extends OfflinePlayer> p);
    VisualBuilder<T> setTimeToLive(long timeToLive);
    T build();
}
