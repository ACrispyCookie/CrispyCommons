package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Location;

import java.util.List;

public interface CrispyHologram extends CrispyAccessibleVisual<HologramData> {

    void addLine(AbstractHologramLine<?> line);
    void addLine(int index, AbstractHologramLine<?> line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);
    Location getLocation();
    int getTimeToLive();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);
}
