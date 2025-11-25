package dev.acrispycookie.crispycommons.api.gui.abstraction.builder;

import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import org.jetbrains.annotations.Nullable;

/**
 * Helper interface for building instances of {@link CrispyGui}.
 *
 * @param <T> the type of {@link CrispyGui} that the builder will create.
 */
public interface GuiBuilder<T extends CrispyGui> {

    /**
     * Constructs and returns a new instance of {@link CrispyGui}.
     *
     * @return a fully constructed {@link CrispyGui} instance.
     */
    @Nullable T build();
}
