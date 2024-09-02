package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.implementations.gui.book.action.BookActionCommand;
import dev.acrispycookie.crispycommons.utility.menu.MenuListener;
import dev.acrispycookie.crispycommons.utility.nms.CommandRegister;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
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

    /**
     * The instance of the {@link JavaPlugin} associated with this plugin.
     * <p>
     * This field stores the reference to the main plugin class, which is used to access plugin-specific methods and resources.
     * </p>
     */
    private static JavaPlugin plugin;

    /**
     * The {@link BukkitAudiences} instance used for managing audience-related tasks.
     * <p>
     * BukkitAudiences allows for handling and sending messages, books, and other components to players using the Adventure API.
     * </p>
     */
    private static BukkitAudiences bukkitAudiences;

    /**
     * The {@link CommonsSettings} instance containing configuration options for the plugin.
     * <p>
     * This field stores the settings that determine which features of the plugin are enabled and how they are configured.
     * </p>
     */
    private static CommonsSettings settings;


    /**
     * Initializes the {@code CrispyCommons} with the specified plugin instance and settings.
     * <p>
     * This method sets up the plugin environment, including configuring Bukkit audiences and registering commands and
     * event listeners based on the provided settings.
     * </p>
     *
     * @param instance the {@link JavaPlugin} instance associated with this plugin.
     * @param settings the {@link CommonsSettings} containing configuration options for the plugin.
     */
    public static void init(@NotNull JavaPlugin instance, @NotNull CommonsSettings settings) {
        plugin = instance;
        CrispyCommons.settings = settings;
        CrispyCommons.bukkitAudiences = BukkitAudiences.create(plugin);
        setup();
    }

    /**
     * Retrieves the {@link JavaPlugin} instance associated with this plugin.
     *
     * @return the {@link JavaPlugin} instance.
     */
    public static @NotNull JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Retrieves the {@link BukkitAudiences} instance used by this plugin for handling audience-related tasks.
     *
     * @return the {@link BukkitAudiences} instance.
     */
    public static @NotNull BukkitAudiences getBukkitAudiences() {
        return bukkitAudiences;
    }

    /**
     * Performs setup tasks based on the provided {@link CommonsSettings}.
     * <p>
     * This includes registering commands and event listeners if the corresponding settings are enabled.
     * </p>
     */
    private static void setup() {
        if (settings.getBookCommand() != null) {
            CommandRegister.newInstance().register(plugin, plugin.getName(),
                    new BookActionCommand(settings.getBookCommand()));
        }
        if (settings.isMenusEnabled()) {
            Bukkit.getPluginManager().registerEvents(MenuListener.newInstance(), plugin);
        }
        Bukkit.getPluginManager().registerEvents(new CrispyCommons(), plugin);
    }

    /**
     * Retrieves the {@link CommonsSettings} instance containing the configuration for this plugin.
     *
     * @return the {@link CommonsSettings} instance.
     */
    public static @NotNull CommonsSettings getSettings() {
        return settings;
    }

    /**
     * Handles the {@link PlayerJoinEvent} to reset the player's scoreboard.
     * <p>
     * This ensures that players have a fresh scoreboard upon joining the server.
     * </p>
     *
     * @param event the {@link PlayerJoinEvent} triggered when a player joins the server.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        assert Bukkit.getScoreboardManager() != null : "Scoreboard manager is null. Contact developer.";
        event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

}
