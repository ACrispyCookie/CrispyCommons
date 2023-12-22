package dev.acrispycookie.crispycommons.implementations.holograms;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractCrispyHologram implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected ArrayList<HologramLine<?>> lines;
    protected boolean isDisplayed;
    protected Location location;
    protected int timeToLive;
    protected abstract Location getLineLocation(int lineIndex);

    public AbstractCrispyHologram(ArrayList<HologramLine<?>> lines, Location location, int timeToLive) {
        this.plugin = CrispyCommons.getPlugin();
        this.lines = lines;
        this.location = location;
        this.timeToLive = timeToLive;
    }

    @Override
    public void display() {
        if (isDisplayed) {
            return;
        }

        isDisplayed = true;

        for (int i = 0; i < lines.size(); i++) {
            HologramLine<?> line = lines.get(i);
            line.setLocation(getLineLocation(i));
            line.display();
        }

        if(timeToLive != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, timeToLive);
        }
    }

    @Override
    public void destroy() {
        if (!isDisplayed) {
            return;
        }

        isDisplayed = false;

        for (int i = 0; i < lines.size(); i++) {
            HologramLine<?> line = lines.get(i);
            line.setLocation(getLineLocation(i));
            line.destroy();
        }
    }

    @Override
    public void update() {
        lines.forEach(HologramLine::update);
    }

    @Override
    public void addLine(HologramLine<?> line) {
        lines.add(line);
        if (isDisplayed) {
            line.setLocation(getLineLocation(lines.size() - 1));
            line.display();
        }
    }

    @Override
    public void removeLine(int index) {
        HologramLine<?> toRemove = lines.get(index);
        lines.remove(index);
        if (isDisplayed) {
            toRemove.destroy();
        }

        for (int i = index; i < lines.size(); i++) {
            HologramLine<?> line = lines.get(i);
            line.setLocation(getLineLocation(i));
        }
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
    public void setPlayers(Player... players) {
        lines.forEach(line -> line.setPlayers(players));
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
        for (int i = 0; i < lines.size(); i++) {
            HologramLine<?> line = lines.get(i);
            line.setLocation(getLineLocation(i));
        }
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
