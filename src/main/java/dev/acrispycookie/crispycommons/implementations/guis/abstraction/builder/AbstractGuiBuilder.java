package dev.acrispycookie.crispycommons.implementations.guis.abstraction.builder;

import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.guis.abstraction.builder.GuiBuilder;

public abstract class AbstractGuiBuilder<T extends CrispyGui> implements GuiBuilder<T> {

    protected T toBuild;
}
