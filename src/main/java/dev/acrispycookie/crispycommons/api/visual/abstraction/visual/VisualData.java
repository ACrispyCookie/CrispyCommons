package dev.acrispycookie.crispycommons.api.visual.abstraction.visual;

import org.bukkit.entity.Player;

/**
 * Represents the data associated with a visual element in the CrispyCommons framework.
 * <p>
 * The {@code VisualData} interface provides a method to verify that the visual data is ready to be used
 * for a specific player. It also includes an inner exception class to handle cases where the data is not ready.
 * </p>
 */
public interface VisualData {

    /**
     * Asserts that the visual data is ready for the specified player.
     * <p>
     * This method should be called to ensure that the data associated with the visual element
     * is fully prepared and can be safely used for the given player.
     * </p>
     *
     * @param p the {@link Player} for whom the data should be ready.
     * @throws VisualNotReadyException if the data is not ready to be used.
     */
    void assertReady(Player p);

    /**
     * Exception thrown when visual data is not ready to be used.
     * <p>
     * This exception is used to indicate that the visual data has not been properly initialized
     * or is otherwise not in a state where it can be safely used.
     * </p>
     */
    class VisualNotReadyException extends RuntimeException {
        /**
         * Constructs a {@code VisualNotReadyException} with the specified detail message.
         *
         * @param message the detail message explaining why the visual data is not ready.
         */
        public VisualNotReadyException(String message) {
            super(message);
        }
    }
}

