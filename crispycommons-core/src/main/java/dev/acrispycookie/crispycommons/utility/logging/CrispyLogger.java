package dev.acrispycookie.crispycommons.utility.logging;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

/**
 * A utility class for logging messages and exceptions in a {@link JavaPlugin}.
 * <p>
 * This class provides static methods to log messages at different levels and to print detailed
 * exception stack traces. It simplifies the logging process by providing a centralized logging
 * mechanism for plugins.
 * </p>
 */
public class CrispyLogger {

    /**
     * Logs a message at the specified {@link Level} using the given plugin's logger.
     *
     * @param plugin  the {@link JavaPlugin} instance to use for logging.
     * @param level   the {@link Level} at which the message should be logged.
     * @param message the message to log.
     */
    public static void log(@NotNull JavaPlugin plugin, @NotNull Level level, @NotNull String message) {
        plugin.getLogger().log(level, message);
    }

    /**
     * Logs an exception and its stack trace to the plugin's logger.
     * <p>
     * The exception is logged with {@link Level#SEVERE} and includes a custom message. The full
     * stack trace of the exception is captured and logged for detailed debugging.
     * </p>
     *
     * @param plugin    the {@link JavaPlugin} instance to use for logging.
     * @param exception the {@link Exception} to log.
     * @param message   a custom message to log along with the exception.
     */
    public static void printException(@NotNull JavaPlugin plugin, @NotNull Exception exception, @NotNull String message) {
        plugin.getLogger().log(Level.SEVERE, message);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        plugin.getLogger().log(Level.SEVERE,
                "Reason: " + sw);
    }
}
