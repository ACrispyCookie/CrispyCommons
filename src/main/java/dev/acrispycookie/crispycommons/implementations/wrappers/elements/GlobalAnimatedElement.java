package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GlobalAnimatedElement<T> extends GlobalDynamicElement<T> {

    private final ArrayList<T> frames;
    private int frame;

    protected GlobalAnimatedElement(Collection<? extends T> frames, int period, boolean async) {
        super(() -> null, period, async);
        this.frames = new ArrayList<>(frames);
        this.frame = 0;
        this.supplier = () -> {
            T frame = this.frames.get(this.frame);
            this.frame = this.frame + 1 >= frames.size() ? 0 : this.frame + 1;
            return frame;
        };
    }

    protected GlobalAnimatedElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier, period, async);
        this.frames = new ArrayList<>();
        this.frame = 0;
    }

}
