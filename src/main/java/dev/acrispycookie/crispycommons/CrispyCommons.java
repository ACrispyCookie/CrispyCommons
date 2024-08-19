package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.implementations.gui.book.action.BookActionCommand;
import dev.acrispycookie.crispycommons.utility.menu.MenuListener;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * The main class for initializing and managing the core features of the CrispyCommons plugin.
 * <p>
 * {@link CrispyCommons} handles the setup and configuration of essential plugin components such as
 * command registration, and event listeners. It also manages the plugin's settings through
 * {@link CommonsSettings}.
 * </p>
 * <p>
 * This class also listens for player join events to perform actions like setting up a fresh scoreboard.
 * </p>
 */
public class CrispyCommons implements Listener {

    private static JavaPlugin plugin;
    private static BukkitAudiences bukkitAudiences;
    private static CommonsSettings settings;

    public static void init(@NotNull JavaPlugin instance, @NotNull CommonsSettings settings) {
        plugin = instance;
        CrispyCommons.settings = settings;
        CrispyCommons.bukkitAudiences = BukkitAudiences.create(plugin);
        setup();
    }

    public static @NotNull JavaPlugin getPlugin() {
        return plugin;
    }

    public static @NotNull BukkitAudiences getBukkitAudiences() {
        return bukkitAudiences;
    }

    private static void setup() {
        if (settings.isBookActionEnabled())
            ((CraftServer) plugin.getServer()).getCommandMap().register(plugin.getName(),
                    new BookActionCommand(settings.getBookCommand()));
        if (settings.isMenusEnabled()) {
            Bukkit.getPluginManager().registerEvents(new MenuListener(), plugin);
        }
        Bukkit.getPluginManager().registerEvents(new CrispyCommons(), plugin);
    }

    public static @NotNull CommonsSettings getSettings() {
        return settings;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

}
