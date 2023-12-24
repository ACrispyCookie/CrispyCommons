package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations.TextHologramLine;
import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SimpleHologramBuilder {

    private Location location;
    private int timeToLive;
    private boolean isPublic = false;
    private final SimpleHologram hologram;
    private final ArrayList<Player> defaultReceivers = new ArrayList<>();
    private final HashMap<HologramLine<?>, Boolean> lines = new HashMap<>();


    public SimpleHologramBuilder(Location location, int timeToLive) {
        this.location = location;
        this.timeToLive = timeToLive;
        this.hologram = new SimpleHologram(location, timeToLive);
    }

    public SimpleHologramBuilder(Location location, int timeToLive, Collection<? extends Player> defaultReceivers) {
        this.location = location;
        this.timeToLive = timeToLive;
        this.hologram = new SimpleHologram(location, timeToLive);
        this.defaultReceivers.addAll(defaultReceivers);
    }

    public SimpleHologramBuilder addDefaultReceiver(Player player) {
        defaultReceivers.add(player);
        return this;
    }

    public SimpleHologramBuilder setDefaultReceivers(Collection<? extends Player> players) {
        defaultReceivers.clear();
        defaultReceivers.addAll(players);
        return this;
    }

    public SimpleHologramBuilder setLocation(Location location) {
        this.location = location;
        hologram.setLocation(this.location);
        return this;
    }

    public SimpleHologramBuilder setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        hologram.setTimeToLive(this.timeToLive);
        return this;
    }

    public SimpleHologramBuilder addTextLine(String text) {
        TextHologramLine line = new TextHologramLine(text, defaultReceivers);
        hologram.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleHologramBuilder addTextLine(String text, Collection<? extends Player> receivers) {
        TextHologramLine line = new TextHologramLine(text, receivers);
        hologram.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleHologramBuilder addAnimatedTextLine(Collection<? extends String> frames, int period) {
        TextHologramLine line = new TextHologramLine(frames, period, defaultReceivers);
        hologram.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleHologramBuilder addAnimatedTextLine(Collection<? extends String> frames, int period, Collection<? extends Player> receivers) {
        TextHologramLine line = new TextHologramLine(frames, period, receivers);
        hologram.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleHologramBuilder addItemLine(CrispyItem item) {
        ItemHologramLine line = new ItemHologramLine(item, defaultReceivers);
        hologram.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleHologramBuilder addItemLine(CrispyItem item, Collection<? extends Player> receivers) {
        ItemHologramLine line = new ItemHologramLine(item, receivers);
        hologram.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleHologramBuilder addAnimatedItemLine(Collection<? extends CrispyItem> frames, int period) {
        ItemHologramLine line = new ItemHologramLine(frames, period, defaultReceivers);
        hologram.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleHologramBuilder addAnimatedItemLine(Collection<? extends CrispyItem> frames, int period, Collection<? extends Player> receivers) {
        ItemHologramLine line = new ItemHologramLine(frames, period, receivers);
        hologram.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleHologramBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleHologram build() {
        if(isPublic) {
            return new PublicHologram(location, timeToLive);
        }

        lines.forEach((l, b) -> {
            if(b)
                l.setPlayers(defaultReceivers);
        });
        return hologram;
    }


}
