package dev.acrispycookie.crispycommons.implementations.holograms.implementations.builders;

import dev.acrispycookie.crispycommons.implementations.holograms.implementations.PublicHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.implementations.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleHologramBuilder {
    private final ArrayList<Player> receiverList;
    private final ArrayList<HologramLine<?>> text;
    private Location location;
    private int tickLifetime;
    private boolean isPublic;
    public void onClick(Player player, int lineIndex) {}

    public SimpleHologramBuilder() {
        this.receiverList = new ArrayList<>();
        this.text = new ArrayList<>();
    }

    public SimpleHologramBuilder addPlayer(Player player) {
        receiverList.add(player);
        return this;
    }

    public SimpleHologramBuilder addPlayers(Collection<? extends Player> players) {
        receiverList.addAll(players);
        return this;
    }

    public SimpleHologramBuilder addLine(HologramLine<?> line) {
        this.text.add(line);
        return this;
    }

    public SimpleHologramBuilder addLine(int index, HologramLine<?> line) {
        this.text.add(index, line);
        return this;
    }

    public SimpleHologramBuilder addLines(ArrayList<HologramLine<?>> line) {
        this.text.addAll(line);
        return this;
    }

    public SimpleHologramBuilder addLines(int index, ArrayList<HologramLine<?>> line) {
        this.text.addAll(index, line);
        return this;
    }

    public SimpleHologramBuilder removeLine(int index) {
        this.text.remove(index);
        return this;
    }

    public SimpleHologramBuilder removeLine(HologramLine<?> line) {
        this.text.remove(line);
        return this;
    }

    public SimpleHologramBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public SimpleHologramBuilder timeToLive(int tickLifetime) {
        this.tickLifetime = tickLifetime;
        return this;
    }

    public SimpleHologramBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleHologram build() {
        if(isPublic)
            return new SimpleHologram(text, location, tickLifetime);
        else
            return new PublicHologram(text, location, tickLifetime);
    }
}
