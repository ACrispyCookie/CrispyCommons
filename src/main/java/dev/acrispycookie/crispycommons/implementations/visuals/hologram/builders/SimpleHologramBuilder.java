package dev.acrispycookie.crispycommons.implementations.visuals.hologram.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.hologram.AbstractHologramLine;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.PublicHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines.TextHologramLine;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SimpleHologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

    private Location location;
    private int timeToLive;
    private boolean isPublic = false;
    private final List<AbstractHologramLine<?>> lines = new ArrayList<>();


    public SimpleHologramBuilder(Location location, int timeToLive) {
        this.location = location;
        this.timeToLive = timeToLive;
    }

    public SimpleHologramBuilder(Location location, int timeToLive, Collection<? extends Player> receivers) {
        super(receivers);
        this.location = location;
        this.timeToLive = timeToLive;
    }

    public SimpleHologramBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public SimpleHologramBuilder setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }

    public SimpleHologramBuilder addTextLine(String text) {
        TextHologramLine line = new TextHologramLine(text);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder addAnimatedTextLine(Collection<? extends String> frames, int period) {
        TextHologramLine line = new TextHologramLine(frames, period);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder addAnimatedTextLine(Supplier<String> supplier, int period) {
        TextHologramLine line = new TextHologramLine(supplier, period);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder addItemLine(CrispyItem item) {
        ItemHologramLine line = new ItemHologramLine(item);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder addAnimatedItemLine(Collection<? extends CrispyItem> frames, int period) {
        ItemHologramLine line = new ItemHologramLine(frames, period);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder addAnimatedItemLine(Supplier<CrispyItem> supplier, int period) {
        ItemHologramLine line = new ItemHologramLine(supplier, period);
        lines.add(line);
        return this;
    }

    public SimpleHologramBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public CrispyHologram build() {
        SimpleHologram hologram;
        HologramData data = new HologramData(lines, timeToLive, location);

        if(isPublic) {
            hologram = new PublicHologram(data, receivers);
        } else {
            hologram = new SimpleHologram(data, receivers);
        }

        return hologram;
    }


}
