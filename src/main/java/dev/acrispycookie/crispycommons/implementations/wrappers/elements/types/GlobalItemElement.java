package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.GlobalAnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class GlobalItemElement extends GlobalAnimatedElement<CrispyItemStack> {
    public GlobalItemElement(Collection<? extends CrispyItemStack> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    public GlobalItemElement(Supplier<CrispyItemStack> supplier, int period) {
        super(supplier, period, false);
    }

    public GlobalItemElement(CrispyItemStack item) {
        this(() -> item, -1);
        setUpdate(() -> {});
    }

    @Override
    public GlobalItemElement clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(this.getRaw());
    }
    public static GlobalItemElement simple(CrispyItemStack item) {
        return new GlobalItemElement(item) {};
    }

    public static GlobalItemElement animated(Collection<? extends CrispyItemStack> frames, int period) {
        return new GlobalItemElement(frames, period) {};
    }

    public static GlobalItemElement dynamic(Supplier<CrispyItemStack> supplier, int period) {
        return new GlobalItemElement(supplier, period) {};
    }
}
