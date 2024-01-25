package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleTextElement extends TextElement {

    public SimpleTextElement(TextComponent text) {
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
