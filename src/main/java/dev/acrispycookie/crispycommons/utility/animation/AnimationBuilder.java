package dev.acrispycookie.crispycommons.utility.animation;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimationBuilder<T> {

    protected final ArrayList<T> frames = new ArrayList<>();
    protected int period = 0;

    public AnimationBuilder<T> setPeriod(int period) {
        this.period = period;
        return this;
    }

    public AnimationBuilder<T> addFrames(ArrayList<T> frames) {
        this.frames.addAll(frames);
        return this;
    }

    public AnimationBuilder<T> addFrame(T frame) {
        frames.add(frame);
        return this;
    }

    public Animation<T> build() {
        return new Animation<T>(frames) {
            @Override
            public void update() {

            }
        };
    }

}
