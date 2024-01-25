package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleStringElement extends StringElement {

    public SimpleStringElement(String text) {
        super(new ArrayList<>(Collections.singletonList(text)), -1, false);
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
