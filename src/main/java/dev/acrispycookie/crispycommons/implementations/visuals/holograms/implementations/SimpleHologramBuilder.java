package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.AbstractHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.TextHologramLine;
import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SimpleHologramBuilder {

    private Location location;
    private int timeToLive;
    private boolean isPublic = false;
    private final Collection<Player> players = new ArrayList<>();
    private final List<AbstractHologramLine<?, ?>> lines = new ArrayList<>();


    public SimpleHologramBuilder(Location location, int timeToLive) {
        this.location = location;
        this.timeToLive = timeToLive;
    }

    public SimpleHologramBuilder(Location location, int timeToLive, Collection<? extends Player> receivers) {
        this.location = location;
        this.timeToLive = timeToLive;
        this.players.addAll(receivers);
    }

    public SimpleHologramBuilder addPlayer(Player player) {
        players.add(player);
        return this;
    }

    public SimpleHologramBuilder setPlayers(Collection<? extends Player> players) {
        this.players.clear();
        this.players.addAll(players);
        return this;
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

    public SimpleHologram build() {
        SimpleHologram hologram;

        if(isPublic) {
            hologram = new PublicHologram(location, timeToLive, players);
        } else {
            hologram = new SimpleHologram(location, timeToLive, players);
        }
        lines.forEach(hologram::addLine);

        return hologram;
    }


}
