package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CrispyShowable<T> {

    void show();
    void hide();
    void update();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    Set<Player> getPlayers();
    boolean isDisplayed();
    T getCurrentContent();

}
