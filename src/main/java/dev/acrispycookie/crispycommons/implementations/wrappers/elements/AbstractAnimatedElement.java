package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

public abstract class AbstractAnimatedElement<T, K> extends AbstractDynamicElement<T, K> {

    private final HashMap<K, Integer> currentFrames = new HashMap<>();

    protected AbstractAnimatedElement(Function<K, Collection<? extends T>> supplier, int period, boolean async, Class<K> kClass) {
        super((p) -> null, period, async, kClass);
        this.supplier = (p) -> {
            ArrayList<T> playerFrames = new ArrayList<>(supplier.apply(p));
            int frame = currentFrames.getOrDefault(p, 0);
            currentFrames.put(p, frame + 1 >= playerFrames.size() ? 0 : frame + 1);
            return playerFrames.get(frame);
        };
    }

    protected AbstractAnimatedElement(MyElementSupplier<K, T> supplier, int period, boolean async, Class<K> kClass) {
        super(supplier.getFunction(), period, async, kClass);
    }

}
