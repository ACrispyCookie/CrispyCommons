package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleTextComponentElement extends TextComponentElement {

    public SimpleTextComponentElement(TextComponent text) {
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
