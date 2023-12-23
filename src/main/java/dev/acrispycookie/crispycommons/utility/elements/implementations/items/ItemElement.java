package dev.acrispycookie.crispycommons.utility.elements.implementations.items;

import dev.acrispycookie.crispycommons.implementations.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ItemElement extends AnimatedElement<CrispyItem> {
    public ItemElement(Collection<? extends CrispyItem> frames, int period) {
        super(new ArrayList<>(frames), period);
    }
}
