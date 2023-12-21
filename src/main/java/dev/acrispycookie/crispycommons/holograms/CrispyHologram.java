package dev.acrispycookie.crispycommons.holograms;

import dev.acrispycookie.crispycommons.holograms.lines.CrispyHologramLine;
import org.bukkit.entity.Player;

public interface CrispyHologram {

    void display();
    void update();
    void destroy();
    void addLine(int index, CrispyHologramLine line);
    void addLine(CrispyHologramLine line);
    void removeLine(int index);
    void removeLine(CrispyHologramLine line);
    void setLine(int index, CrispyHologramLine line);
    void onClick(Player player, int lineIndex);
}
