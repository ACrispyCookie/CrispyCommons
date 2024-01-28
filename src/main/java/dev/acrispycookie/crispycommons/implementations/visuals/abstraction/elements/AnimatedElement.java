package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class AnimatedElement<T> extends DynamicElement<T> {

    private final ArrayList<T> frames;
    private int frame;

    protected AnimatedElement(ArrayList<? extends T> frames, int period, boolean async) {
        super(() -> null, period, async);
        this.frames = new ArrayList<>(frames);
        this.frame = 0;
        this.supplier = () -> {
            T frame = this.frames.get(this.frame);
            this.frame = this.frame + 1 >= frames.size() ? 0 : this.frame + 1;
            return frame;
        };
        this.element = supplier.get();
    }

    protected AnimatedElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier, period, async);
        this.frames = new ArrayList<>();
        this.frame = 0;
    }

}
