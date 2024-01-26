package dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import org.bukkit.Location;

import java.util.List;

public class HologramData implements VisualData {

    private List<AbstractHologramLine<?>> lines;
    private int timeToLive;
    private Location location;

    public HologramData(List<AbstractHologramLine<?>> lines, int timeToLive, Location location) {
        this.lines = lines;
        this.timeToLive = timeToLive;
        this.location = location;
    }

    public List<AbstractHologramLine<?>> getLines() {
        return lines;
    }

    public void setLines(List<AbstractHologramLine<?>> lines) {
        this.lines = lines;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
