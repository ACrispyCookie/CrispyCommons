package dev.acrispycookie.crispycommons.implementations.visuals.hologram.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items.ItemElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
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

public class HologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

    private Location location;
    private int timeToLive;
    private boolean isPublic = false;
    private final List<AbstractHologramLine<?>> lines = new ArrayList<>();


    public HologramBuilder() {
    }

    public HologramBuilder(Collection<? extends Player> receivers) {
        super(receivers);
    }

    public HologramBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public HologramBuilder setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }

    public HologramBuilder addTextLine(TextElement text) {
        TextHologramLine line = new TextHologramLine(text);
        lines.add(line);
        return this;
    }

    public HologramBuilder addItemLine(ItemElement item) {
        ItemHologramLine line = new ItemHologramLine(item);
        lines.add(line);
        return this;
    }

    public HologramBuilder setPublic(boolean isPublic) {
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
