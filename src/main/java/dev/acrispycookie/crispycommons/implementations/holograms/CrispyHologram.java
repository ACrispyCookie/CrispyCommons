package dev.acrispycookie.crispycommons.implementations.holograms;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public interface CrispyHologram {

    void display();
    void destroy();
    void update();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    void addLine(HologramLine<?> line);
    void addLine(int index, HologramLine<?> line);
    void removeLine(int index);
    boolean isDisplayed();
    Location getLocation();
    int getTimeToLive();
    List<HologramLine<?>> getLines();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);
}
