package dev.acrispycookie.crispycommons.holograms.lines;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface CrispyHologramLine {
    void setLocation(Location location);
    void setReceivers(List<Player> receivers);
    void display();
    void destroy();
    void update();
    String getCurrentText();
}
