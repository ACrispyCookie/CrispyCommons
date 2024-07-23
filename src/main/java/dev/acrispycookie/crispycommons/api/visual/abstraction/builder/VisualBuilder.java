package dev.acrispycookie.crispycommons.api.visual.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;

public interface VisualBuilder<T extends CrispyVisual> {

    VisualBuilder<T> addPlayer(OfflinePlayer p);
    VisualBuilder<T> removePlayer(OfflinePlayer p);
    VisualBuilder<T> setPlayers(Collection<? extends OfflinePlayer> p);
    VisualBuilder<T> addPlayers(Collection<? extends OfflinePlayer> p);
    VisualBuilder<T> setTimeToLive(TimeToLiveElement<?> timeToLive);
    VisualBuilder<T> setPublic(boolean isPublic);
    T build();
}
