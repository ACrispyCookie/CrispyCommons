package dev.acrispycookie.crispycommons.implementations.guis.abstraction;

import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;

public abstract class AbstractGui<T extends GuiData> implements CrispyGui {

    protected T data;

    public AbstractGui(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
