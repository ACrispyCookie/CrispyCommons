package dev.acrispycookie.crispycommons.utility.elements.implementations.items;

import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class ItemElement extends AnimatedElement<CrispyItem> {
    public ItemElement(Collection<? extends CrispyItem> frames, int period, boolean async) {
        super(new ArrayList<>(frames), period, async);
    }

    public ItemElement(Supplier<CrispyItem> supplier, int period, boolean async) {
        super(supplier, period, async);
    }
}
