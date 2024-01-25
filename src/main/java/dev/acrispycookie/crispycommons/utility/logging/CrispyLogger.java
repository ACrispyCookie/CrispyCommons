package dev.acrispycookie.crispycommons.utility.logging;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

public class CrispyLogger {

    public static void log(JavaPlugin plugin, Level level, String message) {
        plugin.getLogger().log(level, message);
    }

    public static void printException(JavaPlugin plugin, Exception exception, String message) {
        plugin.getLogger().log(Level.SEVERE, message);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        plugin.getLogger().log(Level.SEVERE,
                "Reason: " + sw);
    }
}
