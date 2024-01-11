package dev.acrispycookie.crispycommons.utility.elements;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class AnimatedElement<T> extends DynamicElement<T> {

    private final ArrayList<T> frames;
    private int frame;

    public AnimatedElement(ArrayList<? extends T> frames, int period, boolean async) {
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

    public AnimatedElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier, period, async);
        this.frames = new ArrayList<>();
        this.frame = 0;
    }

    public void addFrame(T frame) {
        this.frames.add(frame);
    }

}
