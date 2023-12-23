package dev.acrispycookie.crispycommons.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.implementations.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations.TextHologramLine;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class CrispyCommons {

    private static JavaPlugin plugin;

    public static void init(JavaPlugin instance) {
        plugin = instance;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
