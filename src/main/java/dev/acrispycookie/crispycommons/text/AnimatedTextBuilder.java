package dev.acrispycookie.crispycommons.text;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedTextBuilder {

    private final ArrayList<SimpleCrispyText> frames = new ArrayList<>();
    private int animationPeriod = 1;

    public AnimatedTextBuilder setAnimationPeriod(int animationPeriod) {
        this.animationPeriod = animationPeriod;
        return this;
    }

    public AnimatedTextBuilder addFrame(SimpleCrispyText frame) {
        frames.add(frame);
        return this;
    }

    public AnimatedText addFrames(SimpleCrispyText... frames) {
        this.frames.addAll(Arrays.asList(frames));
        return new AnimatedText(this.frames, 1);
    }

    public AnimatedTextBuilder removeFrame(int frame) {
        frames.remove(frame);
        return this;
    }

    public AnimatedTextBuilder setFrame(int frame, SimpleCrispyText text) {
        frames.set(frame, text);
        return this;
    }

    public AnimatedText build() {
        return new AnimatedText(frames, animationPeriod);
    }
}
