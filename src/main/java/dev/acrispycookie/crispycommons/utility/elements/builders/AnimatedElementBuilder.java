package dev.acrispycookie.crispycommons.utility.elements.builders;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.CrispyElement;

import java.util.ArrayList;

public class AnimatedElementBuilder<T extends CrispyElement<?>> {

    private final ArrayList<T> frames = new ArrayList<>();
    private int period;

    public AnimatedElementBuilder<T> addFrames(ArrayList<T> frames) {
        this.frames.addAll(frames);
        return this;
    }

    public AnimatedElementBuilder<T> addFrame(T frame) {
        frames.add(frame);
        return this;
    }

    public AnimatedElementBuilder<T> setPeriod(int period) {
        this.period = period;
        return this;
    }

    public AnimatedElement<T> build() {
        return new AnimatedElement<>(frames, period);
    }
}
