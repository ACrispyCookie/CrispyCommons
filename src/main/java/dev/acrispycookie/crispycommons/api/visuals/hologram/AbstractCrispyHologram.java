package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public abstract class AbstractCrispyHologram extends AbstractCrispyAccessibleVisual<HologramData> implements CrispyHologram {


    public AbstractCrispyHologram(HologramData data, Set<? extends Player> receivers) {
        super(data, receivers);
        this.data.getLines().forEach(l -> l.setHologram(this));
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getLines().forEach(AbstractHologramLine::show);
        if(data.getTimeToLive() != -1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), data.getTimeToLive());
        }
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        data.getLines().forEach(AbstractHologramLine::hide);
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        data.getLines().forEach(AbstractHologramLine::update);
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        if(isDisplayed) {
            data.getLines().forEach(l -> l.show(player));
        }
    }

    @Override
    public void removePlayer(Player player) {
        super.removePlayer(player);
        if(!isDisplayed) {
            data.getLines().forEach(l -> l.hide(player));
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
        if (index > data.getLines().size())
            return;

        List<AbstractHologramLine<?>> newLines = data.getLines();
        newLines.add(index, line);
        data.setLines(newLines);
        line.setHologram(this);
        if (isDisplayed) {
            line.show();
        }

        data.getLines().forEach(AbstractHologramLine::update);
    }

    @Override
    public void addLine(AbstractHologramLine<?> line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        AbstractHologramLine<?> toRemove = data.getLines().get(index);
        List<AbstractHologramLine<?>> newLines = data.getLines();
        newLines.remove(index);
        data.setLines(newLines);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
        }

        data.getLines().forEach(AbstractHologramLine::update);
    }

    @Override
    public void showLine(int index) {
        if(index >= data.getLines().size())
            return;

        AbstractHologramLine<?> line = data.getLines().get(index);
        if(isDisplayed && !line.isDisplayed()) {
            line.show();
        }
    }

    @Override
    public void hideLine(int index) {
        if(index >= data.getLines().size())
            return;

        AbstractHologramLine<?> line = data.getLines().get(index);
        if(isDisplayed && line.isDisplayed()) {
            line.hide();
        }
    }

    @Override
    public Location getLocation() {
        return data.getLocation();
    }

    @Override
    public int getTimeToLive() {
        return data.getTimeToLive();
    }

    @Override
    public void setLocation(Location location) {
        data.setLocation(location);

        update();
    }

    @Override
    public void setTimeToLive(int timeToLive) {
        data.setTimeToLive(timeToLive);
    }
}
