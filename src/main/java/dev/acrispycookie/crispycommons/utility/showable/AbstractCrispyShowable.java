package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractCrispyShowable<T> implements CrispyShowable<T> {

    protected final Set<Player> receivers = new HashSet<>();
    protected boolean isDisplayed = false;

    public AbstractCrispyShowable(Set<? extends Player> receivers) {
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

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
