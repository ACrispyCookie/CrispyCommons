package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalAnimatedElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractHologram extends AbstractVisual<HologramData> implements CrispyHologram {

    AbstractHologram(HologramData data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getLines().forEach(GlobalAnimatedElement::start);
    }

    @Override
    protected void prepareHide() {
        data.getLines().forEach(GlobalAnimatedElement::stop);
    }


    @Override
    public void addLine(int index, GlobalAnimatedElement<?> line) {
        if (index > data.getLines().size())
            return;

        data.addLine(index, line);
    }

    @Override
    public void addLine(GlobalAnimatedElement<?> line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        data.removeLine(index);
    }

    @Override
    public void setLines(Collection<? extends GlobalAnimatedElement<?>> lines) {
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
    public List<GlobalAnimatedElement<?>> getLines() {
        return data.getLines();
    }
}
