package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyHologram extends CrispyVisual {

    static HologramBuilder builder() {
        return new HologramBuilder();
    }
    void addLine(DynamicElement<?, ?> line);
    void addLine(int index, DynamicElement<?, ?> line);
    void removeLine(int index);
    void setLines(Collection<? extends DynamicElement<?, ?>> lines);
    void setLocation(GeneralElement<Location, ?> location);
    List<DynamicElement<?, ?>> getLines();
    GeneralElement<Location, ?> getLocation();

    class HologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

        private final HologramData data = new HologramData(new ArrayList<>(), null);

        public HologramBuilder setLocation(GeneralElement<Location, ?> location) {
            this.data.setLocation(location);
            location.setUpdate(() -> toBuild.update());
            return this;
        }

        public HologramBuilder addLine(DynamicElement<?, ?> element) {
            this.data.getLines().add(element);
            element.setUpdate(() -> toBuild.update());
            return this;
        }

        public CrispyHologram build() {
            toBuild = new SimpleHologram(data, receivers, timeToLive, isPublic);

            return toBuild;
        }
    }
}
