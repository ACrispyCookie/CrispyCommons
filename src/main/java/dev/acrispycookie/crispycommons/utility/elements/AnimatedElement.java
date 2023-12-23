package dev.acrispycookie.crispycommons.utility.elements;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public abstract class AnimatedElement<T> extends AbstractCrispyElement<T> {

    private final ArrayList<T> frames;
    private final int period;
    private int frame;
    private int taskId;
    protected abstract void update();

    public AnimatedElement(ArrayList<T> frames, int period) {
        super(frames.get(0));
        this.frames = new ArrayList<>(frames);
        this.frame = 0;
        this.period = period;
    }

    public void start() {
        taskId = Bukkit.getScheduler().runTaskTimer(CrispyCommons.getPlugin(), () -> {
            this.frame = this.frame + 1 >= this.frames.size() ? 0 : this.frame + 1;
            this.element = frames.get(frame);
            update();
        }, 0, period).getTaskId();
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public void addFrame(T frame) {
        this.frames.add(frame);
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public T getContent() {
        element = frames.get(frame);
        frame = frame + 1 >= frames.size() ? 0 : frame + 1;

        return element;
    }
}
