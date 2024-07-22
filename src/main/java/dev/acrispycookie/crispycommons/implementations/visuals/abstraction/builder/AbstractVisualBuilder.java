package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.VisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVisualBuilder<T extends CrispyVisual> implements VisualBuilder<T> {

    protected T toBuild;
    protected boolean isPublic = false;
    protected final Set<OfflinePlayer> receivers = new HashSet<>();
    protected TimeToLiveElement<?> timeToLive = TimeToLiveElement.simple(-1L, TimeToLiveElement.StartMode.GLOBAL);

    @Override
    public AbstractVisualBuilder<T> addPlayer(OfflinePlayer p) {
        receivers.add(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> removePlayer(OfflinePlayer p) {
        receivers.remove(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> setPlayers(Collection<? extends OfflinePlayer> p) {
        receivers.clear();
        receivers.addAll(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> addPlayers(Collection<? extends OfflinePlayer> p) {
        receivers.addAll(p);
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    @Override
    public AbstractVisualBuilder<T> setTimeToLive(TimeToLiveElement<?> timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }
}
