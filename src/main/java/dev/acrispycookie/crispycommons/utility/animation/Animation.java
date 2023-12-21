package dev.acrispycookie.crispycommons.utility.animation;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public abstract class Animation<T> {

    private final ArrayList<T> frames;
    private JavaPlugin plugin;
    private int period;
    private int frame;
    private int taskID;
    public abstract void update();

    public Animation(JavaPlugin plugin, ArrayList<T> frames, int period) {
        this.frames = new ArrayList<>(frames);
        this.plugin = plugin;
        this.frame = 0;
        this.period = period;
    }

    public Animation(ArrayList<T> frames) {
        this.frames = new ArrayList<>(frames);
        this.frame = 0;
    }

    public void start() {
        taskID = plugin.getServer().getScheduler().runTaskTimer(plugin, this::update, 0, period).getTaskId();
    }

    public void stop() {
        plugin.getServer().getScheduler().cancelTask(taskID);
    }

    public void addFrame(T frame) {
        this.frames.add(frame);
    }

    public int getPeriod() {
        return period;
    }

    public T getCurrentFrame() {
        T frame = frames.get(this.frame);
        this.frame = this.frame + 1 >= frames.size() ? 0 : this.frame + 1;

        return frame;
    }
}
