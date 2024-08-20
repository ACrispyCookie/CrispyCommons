package dev.acrispycookie.crispycommons.implementations.gui.abstraction.builder;

import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.gui.abstraction.builder.GuiBuilder;

/**
 * An abstract base class for building graphical user interfaces (GUIs).
 * <p>
 * This class implements the {@link GuiBuilder} interface and provides a foundation for building GUIs
 * of type {@link CrispyGui}. Subclasses should extend this class to implement specific GUI building logic.
 * </p>
 *
 * @param <T> the type of {@link CrispyGui} that this builder constructs.
 */
public abstract class AbstractGuiBuilder<T extends CrispyGui> implements GuiBuilder<T> {

    /**
     * The instance of the GUI being built.
     * <p>
     * Subclasses will use this field to configure the GUI during the building process.
     * </p>
     */
    protected T toBuild;
}

