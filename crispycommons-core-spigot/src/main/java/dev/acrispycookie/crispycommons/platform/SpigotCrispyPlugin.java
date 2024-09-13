package dev.acrispycookie.crispycommons.platform;

import dev.acrispycookie.crispycommons.platform.CrispyPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public interface SpigotCrispyPlugin extends CrispyPlugin {

    JavaPlugin getSpigot();

    @Override
    default File getDataFolder() {
        return getSpigot().getDataFolder();
    }

    @Override
    default InputStream getResource(String resourceName) {
        return getSpigot().getResource(resourceName);
    }

    @Override
    default Logger getLogger() {
        return getSpigot().getLogger();
    }

    @Override
    default String getName() {
        return getSpigot().getName();
    }
}
