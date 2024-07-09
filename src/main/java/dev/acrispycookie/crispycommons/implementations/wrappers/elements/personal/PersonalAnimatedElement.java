package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal;

import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public abstract class PersonalAnimatedElement<T> extends PersonalDynamicElement<T> {

    private int frame;

    protected PersonalAnimatedElement(Function<OfflinePlayer, Collection<? extends T>> supplier, int period, boolean async) {
        super((p) -> null, period, async);
        this.frame = 0;
        this.supplier = (p) -> {
            ArrayList<T> playerFrames = new ArrayList<>(supplier.apply(p));
            T frame = playerFrames.get(this.frame);
            this.frame = this.frame + 1 >= playerFrames.size() ? 0 : this.frame + 1;
            return frame;
        };
    }

    protected PersonalAnimatedElement(TSupplier<T> supplier, int period, boolean async) {
        super(supplier.getFunction(), period, async);
        this.frame = 0;
    }

}
