package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public interface CrispyAccessibleVisual<T> extends CrispyVisual<T> {

    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    Set<Player> getPlayers();
}
