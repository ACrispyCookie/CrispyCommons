package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal;

import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

public abstract class PersonalAnimatedElement<T> extends PersonalDynamicElement<T> {

    private final HashMap<OfflinePlayer, Integer> currentFrames = new HashMap<>();

    protected PersonalAnimatedElement(Function<OfflinePlayer, Collection<? extends T>> supplier, int period, boolean async) {
        super((p) -> null, period, async);
        this.supplier = (p) -> {
            ArrayList<T> playerFrames = new ArrayList<>(supplier.apply(p));
            int frame = currentFrames.getOrDefault(p, 0);
            currentFrames.put(p, frame + 1 >= playerFrames.size() ? 0 : frame + 1);
            return playerFrames.get(frame);
        };
    }

    protected PersonalAnimatedElement(TSupplier<T> supplier, int period, boolean async) {
        super(supplier.getFunction(), period, async);
    }

}
