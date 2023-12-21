package dev.acrispycookie.crispycommons.holograms;

import dev.acrispycookie.crispycommons.holograms.lines.CrispyHologramLine;
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
    protected int tickLifetime;
    protected final Location location;
    protected abstract void displayToPlayer(Player player);
    protected abstract void hideFromPlayer(Player player);
    protected abstract void handleTextChange();
    public abstract void onClick(Player player, int lineIndex);

    public AbstractCrispyHologram(JavaPlugin plugin, Collection<? extends Player> receiverList, ArrayList<CrispyHologramLine> text, Location location, int tickLifetime) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>(receiverList);
        this.text = text;
        this.location = location;
        this.tickLifetime = tickLifetime;

        if(tickLifetime != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, tickLifetime);
        }
    }

    public AbstractCrispyHologram(JavaPlugin plugin, Player receiver, ArrayList<CrispyHologramLine> text, Location location, int tickLifetime) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>();
        this.receiverList.add(receiver);
        this.text = text;
        this.location = location;
        this.tickLifetime = tickLifetime;

        if(tickLifetime != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, tickLifetime);
        }
    }

    @Override
    public void display() {
        receiverList.forEach(this::displayToPlayer);
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
}
