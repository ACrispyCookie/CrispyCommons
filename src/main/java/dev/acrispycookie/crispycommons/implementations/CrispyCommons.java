package dev.acrispycookie.crispycommons.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.implementations.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations.TextHologramLine;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.AnimatedTextElement;
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
