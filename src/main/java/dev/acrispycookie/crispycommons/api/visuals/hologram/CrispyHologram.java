package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.PublicHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalAnimatedElement;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyHologram extends CrispyVisual {

    static HologramBuilder builder() {
        return new HologramBuilder();
    }
    void addLine(DynamicElement<?> line);
    void addLine(int index, DynamicElement<?> line);
    void removeLine(int index);
    void setLines(Collection<? extends DynamicElement<?>> lines);
    void setLocation(Location location);
    List<DynamicElement<?>> getLines();
    Location getLocation();

    class HologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

        SimpleHologram hologram;
        private final HologramData data = new HologramData(new ArrayList<>(), null);
        private boolean isPublic = false;

        public HologramBuilder setLocation(Location location) {
            this.data.setLocation(location);
            return this;
        }

        public HologramBuilder addLine(DynamicElement<?> element) {
            this.data.getLines().add(element);
            element.setUpdate(() -> hologram.update());
            return this;
        }

        public HologramBuilder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public CrispyHologram build() {
            if(isPublic) {
                hologram = new PublicHologram(data, receivers, timeToLive);
            } else {
                hologram = new SimpleHologram(data, receivers, timeToLive);
            }

            return hologram;
        }
    }
}
