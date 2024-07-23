package dev.acrispycookie.crispycommons.implementations.visual.hologram.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HologramData implements VisualData {

    private final List<DynamicElement<?, ?>> lines;
    private GeneralElement<Location, ?> location;

    public HologramData(Collection<? extends DynamicElement<?, ?>> lines, GeneralElement<Location, ?> location) {
        this.lines = new ArrayList<>(lines);
        this.location = location;
    }

    public List<DynamicElement<?, ?>> getLines() {
        return lines;
    }

    public void setLines(List<DynamicElement<?, ?>> lines) {
        this.lines.clear();
        this.lines.addAll(lines);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public void addLine(int index, DynamicElement<?, ?> element) {
        this.lines.add(index, element);
    }

    public GeneralElement<Location, ?> getLocation() {
        return location;
    }

    public void setLocation(GeneralElement<Location, ?> location) {
        this.location = location;
    }

    @Override
    public void assertReady(Player player) {
        if (lines.isEmpty())
            throw new VisualNotReadyException("The hologram's lines were not set!");
        if (location.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The hologram's location was not set!");
    }
}
