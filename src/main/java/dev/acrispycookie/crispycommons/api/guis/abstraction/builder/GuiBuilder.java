package dev.acrispycookie.crispycommons.api.guis.abstraction.builder;

import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;

public interface GuiBuilder<T extends CrispyGui> {

    T build();
}
