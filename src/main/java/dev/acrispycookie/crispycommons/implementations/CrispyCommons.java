package dev.acrispycookie.crispycommons.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations.TextHologramLine;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class CrispyCommons {

    private static JavaPlugin plugin;

    public static void init(JavaPlugin instance) {
        plugin = instance;
        ArrayList<Player> players = new ArrayList<>();
        TextHologramLine line = new TextHologramLine("Hello World!", players);

    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
