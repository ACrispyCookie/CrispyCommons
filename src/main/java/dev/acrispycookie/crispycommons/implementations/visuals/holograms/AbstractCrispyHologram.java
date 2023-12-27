package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public abstract class AbstractCrispyHologram extends AbstractCrispyAccessibleVisual<List<HologramLine<?>>> implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected ArrayList<AbstractHologramLine<?, ?>> lines;
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
        if (isDisplayed)
            return;

        isDisplayed = true;
        lines.forEach(AbstractHologramLine::show);
        if(timeToLive != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::hide, timeToLive);
        }
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        lines.forEach(AbstractHologramLine::hide);
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        lines.forEach(AbstractHologramLine::update);
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
        super.removePlayer(player);
        if(!isDisplayed) {
            lines.forEach(l -> l.hide(player));
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
    public void addLine(int index, AbstractHologramLine<?, ?> line) {
        if (index > lines.size())
            return;

        lines.add(index, line);
        line.setHologram(this);
        if (isDisplayed) {
            line.show();
        }

        lines.forEach(AbstractHologramLine::update);
    }

    @Override
    public void addLine(AbstractHologramLine<?, ?> line) {
        addLine(lines.size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= lines.size())
            return;

        AbstractHologramLine<?, ?> toRemove = lines.get(index);
        lines.remove(index);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
        }

        lines.forEach(AbstractHologramLine::update);
    }

    @Override
    public void showLine(int index) {
        if(index >= lines.size())
            return;

        AbstractHologramLine<?, ?> line = lines.get(index);
        if(isDisplayed && !line.isDisplayed()) {
            line.show();
        }
    }

    @Override
    public void hideLine(int index) {
        if(index >= lines.size())
            return;

        AbstractHologramLine<?, ?> line = lines.get(index);
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
    public List<HologramLine<?>> getCurrentContent() {
        return new ArrayList<>(lines);
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
