package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class ItemElement extends AnimatedElement<CrispyItem> {
    public ItemElement(Collection<? extends CrispyItem> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    public ItemElement(Supplier<CrispyItem> supplier, int period) {
        super(supplier, period, false);
    }

    public ItemElement(CrispyItem item) {
        this(() -> item, -1);
        setUpdate(() -> {});
    }

    public static ItemElement simple(CrispyItem item) {
        return new ItemElement(item) {};
    }

    public static ItemElement animated(ArrayList<CrispyItem> frames, int period) {
        return new ItemElement(frames, period) {};
    }

    public static ItemElement dynamic(Supplier<CrispyItem> supplier, int period) {
        return new ItemElement(supplier, period) {};
    }
}
