package dev.acrispycookie.crispycommons.implementations.holograms;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface CrispyHologram {

    void display();
    void destroy();
    void update();
    Location getLineLocation(HologramLine<?> line);
    void addLine(HologramLine<?> line);
    void removeLine(int index);
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Player... players);
    boolean isDisplayed();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);
}
