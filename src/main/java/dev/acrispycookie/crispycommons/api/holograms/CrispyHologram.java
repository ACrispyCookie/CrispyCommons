package dev.acrispycookie.crispycommons.api.holograms;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.CrispyHologramLine;
import org.bukkit.entity.Player;

public interface CrispyHologram {

    void display();
    void update();
    void destroy();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Player... players);
    void setTimeToLive(int timeToLive);
    void addLine(int index, CrispyHologramLine line);
    void addLine(CrispyHologramLine line);
    void removeLine(int index);
    void removeLine(CrispyHologramLine line);
    void setLine(int index, CrispyHologramLine line);
}
