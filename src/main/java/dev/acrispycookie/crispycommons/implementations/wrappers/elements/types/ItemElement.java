package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class ItemElement extends AnimatedElement<CrispyItemStack> {
    public ItemElement(Collection<? extends CrispyItemStack> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    public ItemElement(Supplier<CrispyItemStack> supplier, int period) {
        super(supplier, period, false);
    }

    public ItemElement(CrispyItemStack item) {
        this(() -> item, -1);
        setUpdate(() -> {});
    }

    @Override
    public ItemElement clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(this.getRaw());
    }
    public static ItemElement simple(CrispyItemStack item) {
        return new ItemElement(item) {};
    }

    public static ItemElement animated(Collection<? extends CrispyItemStack> frames, int period) {
        return new ItemElement(frames, period) {};
    }

    public static ItemElement dynamic(Supplier<CrispyItemStack> supplier, int period) {
        return new ItemElement(supplier, period) {};
    }
}
