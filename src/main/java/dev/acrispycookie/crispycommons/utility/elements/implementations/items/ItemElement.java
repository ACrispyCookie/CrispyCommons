package dev.acrispycookie.crispycommons.utility.elements.implementations.items;

import dev.acrispycookie.crispycommons.implementations.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;

public abstract class ItemElement extends AnimatedElement<CrispyItem> {
    public ItemElement(ArrayList<CrispyItem> frames, int period) {
        super(frames, period);
    }
}
