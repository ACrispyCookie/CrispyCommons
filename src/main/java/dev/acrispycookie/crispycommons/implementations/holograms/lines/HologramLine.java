package dev.acrispycookie.crispycommons.implementations.holograms.lines;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface HologramLine<K> {

    void setLocation(Location location);
    void display();
    void destroy();
    void update();
    boolean isDisplayed();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Player... players);
    K getCurrentElement();
}
