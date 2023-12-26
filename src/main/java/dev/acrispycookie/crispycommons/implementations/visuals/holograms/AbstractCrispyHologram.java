package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyVisual;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public abstract class AbstractCrispyHologram extends AbstractCrispyAccessibleVisual<List<HologramLine<?>>> implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected ArrayList<HologramLine<?>> lines;
    protected boolean isDisplayed;
    protected Location location;
    protected int timeToLive;

    public AbstractCrispyHologram(Location location, int timeToLive, Set<? extends Player> receivers) {
        super(receivers);
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
    public boolean isDisplayed() {
        return isDisplayed;
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        if(isDisplayed) {
            lines.forEach(l -> l.show(player));
        }
    }

    @Override
    public void removePlayer(Player player) {
        super.addPlayer(player);
        if(!isDisplayed) {
            lines.forEach(l -> l.hide(player));
        }
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        if(isDisplayed) {
            lines.forEach(l -> this.receivers.forEach(l::hide));
            super.setPlayers(players);
            lines.forEach(l -> this.receivers.forEach(l::show));
        }
    }


    @Override
    public void addLine(int index, HologramLine<?> line) {
        lines.add(index, line);
        line.setHologram(this);
        if (isDisplayed) {
            line.show();
        }

        lines.forEach(HologramLine::update);
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

        lines.forEach(HologramLine::update);
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
    public List<HologramLine<?>> getCurrentContent() {
        return lines;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;

        update();
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
}
