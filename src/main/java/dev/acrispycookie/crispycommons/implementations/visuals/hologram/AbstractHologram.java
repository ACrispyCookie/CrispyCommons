package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractHologram extends AbstractVisual<HologramData> implements CrispyHologram {

    AbstractHologram(HologramData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void prepareShow() {
        data.getLines().forEach(AnimatedElement::start);
    }

    @Override
    public void prepareHide() {
        data.getLines().forEach(AnimatedElement::stop);
    }

    @Override
    public void prepareUpdate() {

    }


    @Override
    public void addLine(int index, AnimatedElement<?> line) {
        if (index > data.getLines().size())
            return;

        data.addLine(index, line);
    }

    @Override
    public void addLine(AnimatedElement<?> line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        data.removeLine(index);
    }

    @Override
    public void setLines(Collection<? extends AnimatedElement<?>> lines) {
        data.setLines(new ArrayList<>(lines));
    }

    @Override
    public void setLocation(Location location) {
        data.setLocation(location);
    }

    @Override
    public Location getLocation() {
        return data.getLocation();
    }


    @Override
    public List<AnimatedElement<?>> getLines() {
        return data.getLines();
    }
}
