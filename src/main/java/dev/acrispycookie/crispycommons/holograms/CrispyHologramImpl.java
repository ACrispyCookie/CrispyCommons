package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CrispyHologramImpl implements CrispyHologram{

    protected final JavaPlugin plugin;
    protected final ArrayList<Player> receiverList;
    protected HologramText text;
    protected final Location location;
    protected abstract void displayToPlayer(Player player, int tickLifetime);
    protected abstract void hideFromPlayer(Player player);
    protected abstract void handleTextChange();
    public abstract void click(Player player);

    public CrispyHologramImpl(JavaPlugin plugin, Collection<? extends Player> receiverList, HologramText text, Location location) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>(receiverList);
        this.text = text;
        this.location = location;
    }

    public CrispyHologramImpl(JavaPlugin plugin, Player receiver, HologramText text, Location location) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>();
        this.receiverList.add(receiver);
        this.text = text;
        this.location = location;
    }


    @Override
    public void display() {
        receiverList.forEach((p) -> displayToPlayer(p, -1));
    }

    @Override
    public void display(int tickLifetime) {
        receiverList.forEach((p) -> displayToPlayer(p, tickLifetime));
    }

    @Override
    public void destroy() {
        receiverList.forEach(this::hideFromPlayer);
    }

    public void changeText(HologramText text) {
        this.text = text;
        handleTextChange();
    }
}
