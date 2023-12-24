package dev.acrispycookie.crispycommons.implementations;

import org.bukkit.plugin.java.JavaPlugin;

public class CrispyCommons {

    private static JavaPlugin plugin;

    public static void init(JavaPlugin instance) {
        plugin = instance;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
