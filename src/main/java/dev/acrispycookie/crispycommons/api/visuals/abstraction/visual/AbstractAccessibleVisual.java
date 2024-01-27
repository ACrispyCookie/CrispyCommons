package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAccessibleVisual<T extends VisualData> extends AbstractVisual<T> implements CrispyAccessibleVisual<T> {

    protected final Set<Player> receivers = new HashSet<>();
    protected long timeToLive;

    public AbstractAccessibleVisual(T data, Set<? extends Player> receivers, long timeToLive) {
        super(data);
        this.receivers.addAll(receivers);
        this.timeToLive = timeToLive;
    }

    @Override
    public void addPlayer(Player player) {
        receivers.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        receivers.remove(player);
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        receivers.clear();
        receivers.addAll(players);
    }

    @Override
    public Set<Player> getPlayers() {
        return receivers;
    }

    @Override
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public long getTimeToLive() {
        return timeToLive;
    }
}
