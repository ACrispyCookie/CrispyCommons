package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class PersonalAnimatedElement<T> extends PersonalDynamicElement<T> {

    private final HashMap<OfflinePlayer, ArrayList<T>> frames = new HashMap<>();
    private int frame;

    protected PersonalAnimatedElement(Map<OfflinePlayer, Collection<? extends T>> frames, int period, boolean async) {
        super((p) -> null, period, async);
        frames.forEach((p, t) -> this.frames.put(p, new ArrayList<>(t)));
        this.frame = 0;
        this.supplier = (p) -> {
            T frame = this.frames.get(p).get(this.frame);
            this.frame = this.frame + 1 >= frames.size() ? 0 : this.frame + 1;
            return frame;
        };
        frames.forEach((p, c) -> {
            this.elements.put(p, supplier.apply(p));
        });
    }

    protected PersonalAnimatedElement(Function<OfflinePlayer, ? extends T> supplier, int period, boolean async) {
        super(supplier, period, async);
        this.frame = 0;
    }

}
