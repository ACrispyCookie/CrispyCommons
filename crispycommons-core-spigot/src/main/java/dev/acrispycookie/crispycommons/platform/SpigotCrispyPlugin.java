package dev.acrispycookie.crispycommons.platform;

import dev.acrispycookie.crispycommons.platform.CrispyPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public class SpigotCrispyPlugin implements CrispyPlugin {

    private final JavaPlugin plugin;

    public SpigotCrispyPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public InputStream getResource(String resourceName) {
        return plugin.getResource(resourceName);
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public String getName() {
        return plugin.getName();
    }
}
