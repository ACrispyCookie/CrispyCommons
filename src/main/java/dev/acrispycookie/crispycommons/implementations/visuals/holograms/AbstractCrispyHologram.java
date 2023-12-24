package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCrispyHologram implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected ArrayList<HologramLine<?>> lines;
    protected boolean isDisplayed;
    protected Location location;
    protected int timeToLive;

    public AbstractCrispyHologram(Location location, int timeToLive) {
        this.plugin = CrispyCommons.getPlugin();
        this.lines = new ArrayList<>();
        this.location = location;
        this.timeToLive = timeToLive;
        this.isDisplayed = false;
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        isDisplayed = true;
        lines.forEach(HologramLine::show);

        if(timeToLive != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::hide, timeToLive);
        }
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        isDisplayed = false;
        lines.forEach(HologramLine::hide);
    }

    @Override
    public void update() {
        lines.forEach(HologramLine::update);
    }

    @Override
    public void addPlayer(Player player) {
        lines.forEach(line -> line.addPlayer(player));
    }

    @Override
    public void removePlayer(Player player) {
        lines.forEach(line -> line.removePlayer(player));
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        lines.forEach(line -> line.setPlayers(players));
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        lines.forEach(line -> players.addAll(line.getPlayers()));
        return players;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    @Override
    public void addLine(int index, HologramLine<?> line) {
        lines.add(index, line);
        line.setHologram(this);
        if (isDisplayed) {
            line.show();
        }

        for (int i = index + 1; i < lines.size(); i++) {
            lines.get(i).updateLocation();
        }
    }

    @Override
    public void addLine(HologramLine<?> line) {
        addLine(lines.size(), line);
    }

    @Override
    public void removeLine(int index) {
        HologramLine<?> toRemove = lines.get(index);
        lines.remove(index);
        if (isDisplayed) {
            toRemove.hide();
        }

        for (HologramLine<?> line : lines) {
            line.updateLocation();
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getTimeToLive() {
        return timeToLive;
    }

    @Override
    public ArrayList<HologramLine<?>> getLines() {
        return lines;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;

        for (HologramLine<?> line : lines) {
            line.updateLocation();
        }
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
}
