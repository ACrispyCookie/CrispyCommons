package dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HologramData implements VisualData {

    private final List<DynamicElement<?>> lines;
    private Location location;

    public HologramData(Collection<? extends DynamicElement<?>> lines, Location location) {
        this.lines = new ArrayList<>(lines);
        this.location = location;
    }

    public List<DynamicElement<?>> getLines() {
        return lines;
    }

    public void setLines(List<DynamicElement<?>> lines) {
        this.lines.clear();
        this.lines.addAll(lines);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public void addLine(int index, DynamicElement<?> element) {
        this.lines.add(index, element);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void assertReady() {
        if (lines == null)
            throw new VisualNotReadyException("The hologram's lines were not set!");
        if (location == null)
            throw new VisualNotReadyException("The hologram's location was not set!");
    }
}
