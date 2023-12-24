package dev.acrispycookie.crispycommons.implementations.visuals.particles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public abstract class AbstractCrispyParticle implements CrispyParticle {

    protected final JavaPlugin plugin;
    protected ArrayList<BukkitTask> bukkitTasks;
    protected long duration;
    protected long period;

    protected AbstractCrispyParticle(JavaPlugin plugin, long duration, long period) {
        this.plugin = plugin;
        this.bukkitTasks = new ArrayList<>();
        this.duration = duration;
        this.period = period;
    }

    @Override
    public void play(Player p) {
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> this.playOnce(p), duration, period);
        bukkitTasks.add(task);
    }

    @Override
    public void broadcast() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            play(p);
        }
    }

    @Override
    public void broadcastOnce() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            playOnce(p);
        }
    }

    @Override
    public void stop() {
        bukkitTasks.forEach((t) -> {
            if(t != null)
                t.cancel();
        });
        bukkitTasks.clear();
    }

}
