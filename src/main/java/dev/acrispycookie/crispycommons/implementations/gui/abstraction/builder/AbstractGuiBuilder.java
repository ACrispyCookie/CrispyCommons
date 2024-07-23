package dev.acrispycookie.crispycommons.implementations.gui.abstraction.builder;

import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.gui.abstraction.builder.GuiBuilder;

public abstract class AbstractGuiBuilder<T extends CrispyGui> implements GuiBuilder<T> {

    protected T toBuild;
}
