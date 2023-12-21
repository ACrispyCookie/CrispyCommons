package dev.acrispycookie.crispycommons.holograms.lines.implementations;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimatedHologramLine extends SimpleHologramLine {

    private final ArrayList<String> animation;
    private final int period;
    private final JavaPlugin plugin;
    private int animationTask;
    private int currentIndex;

    public AnimatedHologramLine(JavaPlugin plugin, int period, List<String> animation) {
        super(animation.get(0));
        this.plugin = plugin;
        this.animation = new ArrayList<>(animation);
        this.period = period;
    }

    public AnimatedHologramLine(JavaPlugin plugin, int period, String animation) {
        super(animation.split("\n")[0]);
        this.plugin = plugin;
        this.animation = new ArrayList<>(Arrays.asList(animation.split("\n")));
        this.period = period;
    }

    @Override
    public void update() {
        currentIndex = currentIndex + 1 >= animation.size() ? 0 : currentIndex + 1;
        this.line = animation.get(currentIndex);
        super.update();
    }

    @Override
    public void destroy() {
        plugin.getServer().getScheduler().cancelTask(animationTask);
        super.destroy();
    }

    @Override
    public void display() {
        animationTask = plugin.getServer().getScheduler().runTaskTimer(plugin, this::update, 0, period).getTaskId();
        super.display();
    }

}
