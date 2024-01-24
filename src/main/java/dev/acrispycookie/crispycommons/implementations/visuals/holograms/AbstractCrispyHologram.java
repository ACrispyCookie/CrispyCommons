package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public abstract class AbstractCrispyHologram extends AbstractCrispyAccessibleVisual<List<AbstractHologramLine<?>>> implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected Location location;
    protected int timeToLive;

    public AbstractCrispyHologram(List<AbstractHologramLine<?>> content, Location location, int timeToLive, Set<? extends Player> receivers) {
        super(content, receivers);
        this.plugin = CrispyCommons.getPlugin();
        this.location = location;
        this.timeToLive = timeToLive;
        this.isDisplayed = false;
        this.content.forEach(l -> l.setHologram(this));
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        content.forEach(AbstractHologramLine::show);
        if(timeToLive != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::hide, timeToLive);
        }
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        content.forEach(AbstractHologramLine::hide);
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        content.forEach(AbstractHologramLine::update);
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        if(isDisplayed) {
            content.forEach(l -> l.show(player));
        }
    }

    @Override
    public void removePlayer(Player player) {
        super.removePlayer(player);
        if(!isDisplayed) {
            content.forEach(l -> l.hide(player));
        }
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        if(isDisplayed) {
            hide();
            super.setPlayers(players);
            show();
        }
    }


    @Override
    public void addLine(int index, AbstractHologramLine<?> line) {
        if (index > content.size())
            return;

        content.add(index, line);
        line.setHologram(this);
        if (isDisplayed) {
            line.show();
        }

        content.forEach(AbstractHologramLine::update);
    }

    @Override
    public void addLine(AbstractHologramLine<?> line) {
        addLine(content.size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= content.size())
            return;

        AbstractHologramLine<?> toRemove = content.get(index);
        content.remove(index);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
        }

        content.forEach(AbstractHologramLine::update);
    }

    @Override
    public void showLine(int index) {
        if(index >= content.size())
            return;

        AbstractHologramLine<?> line = content.get(index);
        if(isDisplayed && !line.isDisplayed()) {
            line.show();
        }
    }

    @Override
    public void hideLine(int index) {
        if(index >= content.size())
            return;

        AbstractHologramLine<?> line = content.get(index);
        if(isDisplayed && line.isDisplayed()) {
            line.hide();
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
    public void setLocation(Location location) {
        this.location = location;

        update();
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
}
