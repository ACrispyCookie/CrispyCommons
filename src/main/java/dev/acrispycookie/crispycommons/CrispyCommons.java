package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyCommons {

    private static JavaPlugin plugin;
    private static BukkitAudiences bukkitAudiences;
    private static InventoryManager inventoryManager;
    private static CommonsSettings settings;

    public static void init(JavaPlugin instance, CommonsSettings settings) {
        plugin = instance;
        CrispyCommons.settings = settings;
        CrispyCommons.bukkitAudiences = BukkitAudiences.create(plugin);
        CrispyCommons.inventoryManager = new InventoryManager(instance);
        if(settings != null)
            setup();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static BukkitAudiences getBukkitAudiences() {
        return bukkitAudiences;
    }

    public static InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    private static void setup() {
        if (settings.isBookActionEnabled())
            ((CraftServer) plugin.getServer()).getCommandMap().register(plugin.getName(),
                    new BookActionCommand(settings.getBookCommand()));
        if (settings.isInventoriesEnabled()) {

        }
    }

}
