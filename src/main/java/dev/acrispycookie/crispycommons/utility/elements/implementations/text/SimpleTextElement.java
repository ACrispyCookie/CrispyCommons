package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleTextElement extends TextElement {

    public SimpleTextElement(String text) {
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
