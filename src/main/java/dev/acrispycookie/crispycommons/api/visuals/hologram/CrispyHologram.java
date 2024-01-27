package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items.ItemElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.AbstractHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.PublicHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines.TextHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyHologram extends CrispyAccessibleVisual<HologramData> {

    static HologramBuilder builder() {
        return new HologramBuilder();
    }
    void addLine(AbstractHologramLine<?> line);
    void addLine(int index, AbstractHologramLine<?> line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);
    Location getLocation();
    int getTimeToLive();
    void setLocation(Location location);
    void setTimeToLive(int timeToLive);

    class HologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

        private final HologramData data = new HologramData(new ArrayList<>(), 0, null);
        private boolean isPublic = false;

        public HologramBuilder setLocation(Location location) {
            this.data.setLocation(location);
            return this;
        }

        public HologramBuilder setTimeToLive(int timeToLive) {
            this.data.setTimeToLive(timeToLive);
            return this;
        }

        public HologramBuilder addTextLine(TextElement text) {
            TextHologramLine line = new TextHologramLine(text);
            List<AbstractHologramLine<?>> newLines = this.data.getLines();
            newLines.add(line);
            this.data.setLines(newLines);
            return this;
        }

        public HologramBuilder addItemLine(ItemElement item) {
            ItemHologramLine line = new ItemHologramLine(item);
            List<AbstractHologramLine<?>> newLines = this.data.getLines();
            newLines.add(line);
            this.data.setLines(newLines);
            return this;
        }

        public HologramBuilder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public CrispyHologram build() {
            SimpleHologram hologram;

            if(isPublic) {
                hologram = new PublicHologram(data, receivers);
            } else {
                hologram = new SimpleHologram(data, receivers);
            }

            return hologram;
        }
    }
}
