package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyCommons {

    private static JavaPlugin plugin;
    private static BukkitAudiences bukkitAudiences;
    private static CommonsSettings settings;

    public static void init(JavaPlugin instance, CommonsSettings settings) {
        plugin = instance;
        CrispyCommons.settings = settings;
        CrispyCommons.bukkitAudiences = BukkitAudiences.create(plugin);
        if(settings != null)
            setup();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static BukkitAudiences getBukkitAudiences() {
        return bukkitAudiences;
    }

    private static void setup() {
        if (settings.isBookActionEnabled())
            ((CraftServer) plugin.getServer()).getCommandMap().register(plugin.getName(),
                    new BookActionCommand(settings.getBookCommand()));
    }

}
