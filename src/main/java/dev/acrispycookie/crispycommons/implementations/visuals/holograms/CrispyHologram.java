package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.utility.showable.CrispyAccessibleVisual;
import org.bukkit.Location;

import java.util.List;

public interface CrispyHologram extends CrispyAccessibleVisual<List<HologramLine<?>>> {

    void addLine(AbstractHologramLine<?, ?> line);
    void addLine(int index, AbstractHologramLine<?, ?> line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);
    Location getLocation();
    int getTimeToLive();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);
}
