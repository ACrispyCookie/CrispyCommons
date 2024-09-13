package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.platform.CrispyPlugin;
import dev.acrispycookie.crispycommons.platform.commands.PlatformCommand;
import net.kyori.adventure.platform.AudienceProvider;
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
public abstract class CrispyCommons {

    /**
     * The instance of the {@link CrispyPlugin} associated with this plugin.
     * <p>
     * This field stores the reference to the main plugin class, which is used to access plugin-specific methods and resources.
     * </p>
     */
    protected CrispyPlugin plugin;

    /**
     * The {@link AudienceProvider} instance used for managing audience-related tasks.
     * <p>
     * BukkitAudiences allows for handling and sending messages, books, and other components to players using the Adventure API.
     * </p>
     */
    protected AudienceProvider audienceProvider;

    /**
     * The {@link CommonsSettings} instance containing configuration options for the plugin.
     * <p>
     * This field stores the settings that determine which features of the plugin are enabled and how they are configured.
     * </p>
     */
    protected CommonsSettings settings;


    /**
     * Initializes the {@code CrispyCommons} with the specified plugin instance and settings.
     * <p>
     * This method sets up the plugin environment, including configuring audiences and registering commands and
     * event listeners based on the provided settings.
     * </p>
     *
     * @param instance the {@link CrispyPlugin} instance associated with this plugin.
     * @param audienceProvider the {@link AudienceProvider} instance associated with this plugin.
     * @param settings the {@link CommonsSettings} containing configuration options for the plugin.
     */
    public CrispyCommons(@NotNull CrispyPlugin instance, @NotNull AudienceProvider audienceProvider, @NotNull CommonsSettings settings) {
        this.plugin = instance;
        this.settings = settings;
        this.audienceProvider = audienceProvider;
        setup();
    }

    /**
     * Retrieves the {@link CrispyPlugin} instance associated with this plugin.
     *
     * @return the {@link CrispyPlugin} instance.
     */
    public @NotNull CrispyPlugin getPlugin() {
        return plugin;
    }

    /**
     * Retrieves the {@link AudienceProvider} instance used by this plugin for handling audience-related tasks.
     *
     * @return the {@link AudienceProvider} instance.
     */
    public @NotNull AudienceProvider getAudienceProvider() {
        return audienceProvider;
    }

    /**
     * Retrieves the {@link CommonsSettings} instance containing the configuration for this plugin.
     *
     * @return the {@link CommonsSettings} instance.
     */
    public @NotNull CommonsSettings getSettings() {
        return settings;
    }

    /**
     * Performs setup tasks based on the provided {@link CommonsSettings}.
     * <p>
     * This includes registering commands and event listeners if the corresponding settings are enabled.
     * </p>
     */
    protected abstract void setup();

    /**
     * Registers a command for the provided {@link CrispyPlugin}.
     */
    public abstract boolean registerCommand(@NotNull CrispyPlugin plugin, @NotNull String fallbackPrefix, @NotNull PlatformCommand command);

    /**
     * Unregisters a command for the provided {@link CrispyPlugin}.
     */
    public abstract void unregisterCommand(@NotNull CrispyPlugin plugin, @NotNull PlatformCommand command);

}
