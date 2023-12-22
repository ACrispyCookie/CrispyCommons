package dev.acrispycookie.crispycommons.utility.elements;

import java.util.ArrayList;

public class AnimatedElement<T> extends AbstractCrispyElement<T> {

    private final ArrayList<T> frames;
    private final int period;
    private int frame;

    public AnimatedElement(ArrayList<T> frames, int period) {
        super(frames.get(0));
        this.frames = new ArrayList<>(frames);
        this.frame = 0;
        this.period = period;
    }

    public AnimatedElement(T frame, int period) {
        super(frame);
        this.frames = new ArrayList<>();
        this.frames.add(frame);
        this.frame = 0;
        this.period = period;
    }

    public void addFrame(T frame) {
        this.frames.add(frame);
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public T getElement() {
        element = frames.get(frame);
        frame = frame + 1 >= frames.size() ? 0 : frame + 1;

        return element;
    }
}
