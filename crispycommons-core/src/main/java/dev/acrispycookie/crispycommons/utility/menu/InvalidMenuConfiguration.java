package dev.acrispycookie.crispycommons.utility.menu;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown to indicate that a menu configuration is invalid.
 * <p>
 * This exception is used to signal issues with the configuration of menus in the application.
 * It extends {@link RuntimeException} and provides a constructor to specify a detailed error message.
 * </p>
 */
public class InvalidMenuConfiguration extends RuntimeException {

    /**
     * Constructs a new {@code InvalidMenuConfiguration} with the specified detail message.
     *
     * @param message the detail message to be reported when the exception is thrown.
     */
    public InvalidMenuConfiguration(@NotNull String message) {
        super(message);
    }
}
