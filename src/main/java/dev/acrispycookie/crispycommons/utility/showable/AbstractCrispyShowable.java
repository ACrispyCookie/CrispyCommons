package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCrispyShowable implements CrispyShowable {

    protected final List<Player> receivers = new ArrayList<>();
    protected boolean isDisplayed = false;

    public AbstractCrispyShowable(Collection<? extends Player> receivers) {
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
    public List<Player> getPlayers() {
        return new ArrayList<>(receivers);
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
