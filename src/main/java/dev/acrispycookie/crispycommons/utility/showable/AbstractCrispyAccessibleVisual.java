package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCrispyAccessibleVisual<T> extends AbstractCrispyVisual<T> implements CrispyAccessibleVisual<T> {

    protected final Set<Player> receivers = new HashSet<>();

    public AbstractCrispyAccessibleVisual(Set<? extends Player> receivers) {
        this.receivers.addAll(receivers);
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
}
