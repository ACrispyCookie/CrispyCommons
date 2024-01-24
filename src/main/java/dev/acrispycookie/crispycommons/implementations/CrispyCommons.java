package dev.acrispycookie.crispycommons.implementations;

import dev.acrispycookie.crispycommons.implementations.guis.books.CrispyBookImpl;
import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyCommons {

    private static JavaPlugin plugin;
    private static CommonsSettings settings;

    public static void init(JavaPlugin instance, CommonsSettings settings) {
        plugin = instance;
        CrispyCommons.settings = settings;
        if(settings != null)
            setup();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    private static void setup() {
        if (settings.isBookActionEnabled())
            ((CraftServer) plugin.getServer()).getCommandMap().register(plugin.getName(),
                    new BookActionCommand(settings.getBookCommand()));
    }

}
