package dev.acrispycookie.crispycommons.api.gui.abstraction;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the essential data required by a {@link CrispyGui}
 * to render and display the user interface correctly.
 */
public interface GuiData {

    /**
     * Validates the completeness of the data needed for the GUI.
     * <p>
     * This method checks if all necessary components, such as book pages or menu sections,
     * are properly set.
     * </p>
     *
     * @throws GuiNotReadyException if any critical data is missing or incomplete.
     */
    void assertReady();

    /**
     * Exception thrown when a {@link CrispyGui} is not correctly configured or
     * is missing essential components required for its proper display.
     */
    class GuiNotReadyException extends RuntimeException {

        /**
         * Constructs a new {@link GuiNotReadyException} with the specified detail message.
         *
         * @param message the message that describes exactly what is missing.
         * @throws NullPointerException if {@code message} is {@code null}.
         */
        public GuiNotReadyException(@NotNull String message) {
            super(message);
        }
    }
}
