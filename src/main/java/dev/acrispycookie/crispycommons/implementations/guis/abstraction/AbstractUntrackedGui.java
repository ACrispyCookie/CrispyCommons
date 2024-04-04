package dev.acrispycookie.crispycommons.implementations.guis.abstraction;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;

public abstract class AbstractUntrackedGui<T extends GuiData> extends AbstractGui<T> {

    public AbstractUntrackedGui(T data) {
        super(data);
    }
}
