package dev.acrispycookie.crispycommons.holograms.text;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedTextBuilder {

    private final ArrayList<SimpleHologramText> frames = new ArrayList<>();
    private int animationPeriod = 1;

    public AnimatedTextBuilder setAnimationPeriod(int animationPeriod) {
        this.animationPeriod = animationPeriod;
        return this;
    }

    public AnimatedTextBuilder addFrame(SimpleHologramText frame) {
        frames.add(frame);
        return this;
    }

    public AnimatedHologramText addFrames(SimpleHologramText... frames) {
        this.frames.addAll(Arrays.asList(frames));
        return new AnimatedHologramText(this.frames, 1);
    }

    public AnimatedTextBuilder removeFrame(int frame) {
        frames.remove(frame);
        return this;
    }

    public AnimatedTextBuilder setFrame(int frame, SimpleHologramText text) {
        frames.set(frame, text);
        return this;
    }

    public AnimatedHologramText build() {
        return new AnimatedHologramText(frames, animationPeriod);
    }
}
