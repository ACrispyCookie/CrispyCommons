package dev.acrispycookie.crispycommons.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.implementations.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyCommons {

    private static JavaPlugin plugin;

    public static void init(JavaPlugin instance) {
        plugin = instance;
        SimpleHologram holo;
        HologramLine<?> line;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
