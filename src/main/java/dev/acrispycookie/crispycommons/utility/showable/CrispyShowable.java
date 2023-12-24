package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public interface CrispyShowable {

    void show();
    void hide();
    void update();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    List<Player> getPlayers();
    boolean isDisplayed();

}
