package dev.acrispycookie.crispycommons.utility.elements.implementations.items;

import dev.acrispycookie.crispycommons.implementations.itemstack.CrispyItem;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleItemElement extends ItemElement {
    public SimpleItemElement(CrispyItem item) {
        super(new ArrayList<>(Collections.singleton(item)), -1);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    protected void update() {

    }
}
