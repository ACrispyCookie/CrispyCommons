package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;

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
}
