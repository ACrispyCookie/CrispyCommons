package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleCrispyHologram extends CrispyHologramImpl {

    private Runnable onClick;
    private int animationTask;

    public SimpleCrispyHologram(JavaPlugin plugin, Collection<? extends Player> receiverList, HologramText text, Location location) {
        super(plugin, receiverList, text, location);
        if(text.isAnimated())
            setupAnimationTask();
    }

    public SimpleCrispyHologram(JavaPlugin plugin, Player receiver, HologramText text, , Location location) {
        super(plugin, receiver, text, location);
        if(text.isAnimated())
            setupAnimationTask();
    }

    public SimpleCrispyHologram clickable(Runnable onClick) {
        this.onClick = onClick;
        return this;
    }

    @Override
    protected void displayToPlayer(Player player, int tickLifetime) {

    }

    @Override
    protected void hideFromPlayer(Player player) {
        if(text.isAnimated())
            Bukkit.getScheduler().cancelTask(animationTask);


    }

    @Override
    public void click(Player player) {
        onClick.run();
    }

    @Override
    public void update() {
        String lines = text.getCurrentText();

    }

    @Override
    public void handleTextChange() {
        if(text.isAnimated())
            setupAnimationTask();
        update();
    }

    private void setupAnimationTask() {
        this.animationTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::update, 0L, text.getAnimationPeriod());
    }
}
