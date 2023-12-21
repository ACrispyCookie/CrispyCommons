package dev.acrispycookie.crispycommons.holograms.implementations.builders;

import dev.acrispycookie.crispycommons.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.holograms.implementations.PublicCrispyHologram;
import dev.acrispycookie.crispycommons.holograms.implementations.SimpleCrispyHologram;
import dev.acrispycookie.crispycommons.holograms.lines.CrispyHologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CrispyHologramBuilder {
    private final JavaPlugin plugin;
    private final ArrayList<Player> receiverList;
    private final ArrayList<CrispyHologramLine> text;
    private Location location;
    private int tickLifetime;
    private boolean isPublic;
    public void onClick(Player player, int lineIndex) {}

    public CrispyHologramBuilder(JavaPlugin plugin) {
        this.receiverList = new ArrayList<>();
        this.plugin = plugin;
        this.text = new ArrayList<>();
    }

    public CrispyHologramBuilder addPlayer(Player player) {
        receiverList.add(player);
        return this;
    }

    public CrispyHologramBuilder addPlayers(Collection<? extends Player> players) {
        receiverList.addAll(players);
        return this;
    }

    public CrispyHologramBuilder addLine(CrispyHologramLine line) {
        this.text.add(line);
        return this;
    }

    public CrispyHologramBuilder addLine(int index, CrispyHologramLine line) {
        this.text.add(index, line);
        return this;
    }

    public CrispyHologramBuilder addLines(ArrayList<CrispyHologramLine> line) {
        this.text.addAll(line);
        return this;
    }

    public CrispyHologramBuilder addLines(int index, ArrayList<CrispyHologramLine> line) {
        this.text.addAll(index, line);
        return this;
    }

    public CrispyHologramBuilder removeLine(int index) {
        this.text.remove(index);
        return this;
    }

    public CrispyHologramBuilder removeLine(CrispyHologramLine line) {
        this.text.remove(line);
        return this;
    }

    public CrispyHologramBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public CrispyHologramBuilder tickLifetime(int tickLifetime) {
        this.tickLifetime = tickLifetime;
        return this;
    }

    public CrispyHologramBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleCrispyHologram build() {
        if(isPublic)
            return new SimpleCrispyHologram(plugin, receiverList, text, location, tickLifetime);
        else
            return new PublicCrispyHologram(plugin, text, location, tickLifetime);
    }
}
