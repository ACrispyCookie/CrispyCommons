package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import dev.acrispycookie.crispycommons.utility.showable.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.utility.showable.CrispyVisual;
import org.bukkit.Location;

import java.util.List;

public interface CrispyHologram extends CrispyAccessibleVisual<List<HologramLine<?>>> {

    void addLine(HologramLine<?> line);
    void addLine(int index, HologramLine<?> line);
    void removeLine(int index);
    Location getLocation();
    int getTimeToLive();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);
}
