package dev.acrispycookie.crispycommons.implementations.holograms;

import dev.acrispycookie.crispycommons.api.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.CrispyHologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractCrispyHologram implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected final ArrayList<Player> receiverList;
    protected ArrayList<CrispyHologramLine> text;
    protected int timeToLive;
    protected final Location location;
    protected abstract void displayToPlayer(Player player);
    protected abstract void hideFromPlayer(Player player);
    protected abstract void handleTextChange();

    public AbstractCrispyHologram(JavaPlugin plugin, Collection<? extends Player> receiverList, ArrayList<CrispyHologramLine> text, Location location, int timeToLive) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>(receiverList);
        this.text = text;
        this.location = location;
        this.timeToLive = timeToLive;
    }

    public AbstractCrispyHologram(JavaPlugin plugin, Player receiver, ArrayList<CrispyHologramLine> text, Location location, int timeToLive) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>();
        this.receiverList.add(receiver);
        this.text = text;
        this.location = location;
        this.timeToLive = timeToLive;
    }

    @Override
    public void display() {
        receiverList.forEach(this::displayToPlayer);
        if(timeToLive != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, timeToLive);
        }
    }

    @Override
    public void destroy() {
        receiverList.forEach(this::hideFromPlayer);
    }

    public void changeText(ArrayList<CrispyHologramLine> text) {
        this.text = text;
        handleTextChange();
    }

    public void addLine(CrispyHologramLine line) {
        text.add(line);
        handleTextChange();
    }

    public void addLine(int index, CrispyHologramLine line) {
        text.add(index, line);
        handleTextChange();
    }

    public void removeLine(CrispyHologramLine line) {
        text.remove(line);
        handleTextChange();
    }

    public void removeLine(int index) {
        text.remove(index);
        handleTextChange();
    }

    public void setLine(int index, CrispyHologramLine line) {
        text.set(index, line);
        handleTextChange();
    }

    public void addPlayer(Player player) {
        receiverList.add(player);
        displayToPlayer(player);
    }

    public void removePlayer(Player player) {
        receiverList.remove(player);
        hideFromPlayer(player);
    }

    public void setPlayers(Player... players) {
        for (Player player : receiverList) {
            if (!player.isOnline()) {
                hideFromPlayer(player);
            }
        }
        receiverList.clear();

        for(Player player : players) {
            receiverList.add(player);
            displayToPlayer(player);
        }
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
}
