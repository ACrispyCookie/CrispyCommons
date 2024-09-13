package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.itemstack.PlayerHeadItem;
import dev.acrispycookie.crispycommons.implementations.itemstack.UrlHeadItem;
import dev.acrispycookie.crispycommons.platform.CrispyPlugin;
import dev.acrispycookie.crispycommons.platform.commands.PlatformCommand;
import dev.acrispycookie.crispycommons.implementations.gui.book.action.BookActionCommand;
import dev.acrispycookie.crispycommons.utility.nms.CommandRegister;
import dev.acrispycookie.crispycommons.platform.SpigotCrispyPlugin;
import dev.acrispycookie.crispycommons.utility.menu.MenuListener;
import dev.acrispycookie.crispycommons.platform.commands.SpigotCommand;
import dev.dejvokep.boostedyaml.serialization.standard.StandardSerializer;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * The main class for initializing and managing the core features of the CrispyCommons plugin.
 * <p>
 * {@link SpigotCrispyCommons} handles the setup and configuration of essential plugin components such as
 * command registration, and event listeners. It also manages the plugin's settings through
 * {@link CommonsSettings}.
 * </p>
 * <p>
 * This class also listens for player join events to perform actions like setting up a fresh scoreboard.
 * </p>
 */
public class SpigotCrispyCommons extends CrispyCommons implements Listener {

    private static SpigotCrispyCommons instance;

    /**
     * Initializes the {@code CrispyCommons} with the specified plugin instance and settings.
     * <p>
     * This method sets up the plugin environment, including configuring audiences and registering commands and
     * event listeners based on the provided settings.
     * </p>
     *
     * @param instance         the {@link CrispyPlugin} instance associated with this plugin.
     * @param audienceProvider the {@link AudienceProvider} instance associated with this plugin.
     * @param settings         the {@link CommonsSettings} containing configuration options for the plugin.
     */
    public SpigotCrispyCommons(@NotNull JavaPlugin instance, @NotNull BukkitAudiences audienceProvider, @NotNull SpigotCommonsSettings settings) {
        super((SpigotCrispyPlugin) () -> instance, audienceProvider, settings);
        SpigotCrispyCommons.instance = this;
        StandardSerializer.getDefault().register(CrispyItemStack.class, CrispyItemStack.getItemAdapter());
        StandardSerializer.getDefault().register(PlayerHeadItem.class, PlayerHeadItem.getHeadAdapter());
        StandardSerializer.getDefault().register(UrlHeadItem.class, UrlHeadItem.getHeadAdapter());
    }

    public static SpigotCrispyCommons getInstance() {
        return instance;
    }

    /**
     * Retrieves the {@link JavaPlugin} instance associated with this plugin.
     *
     * @return the {@link JavaPlugin} instance.
     */
    public @NotNull JavaPlugin getBukkitPlugin() {
        return ((SpigotCrispyPlugin) plugin).getSpigot();
    }

    /**
     * Retrieves the {@link BukkitAudiences} instance used by this plugin for handling audience-related tasks.
     *
     * @return the {@link BukkitAudiences} instance.
     */
    public @NotNull BukkitAudiences getBukkitAudiences() {
        return ((BukkitAudiences) audienceProvider);
    }

    /**
     * Retrieves the {@link SpigotCommonsSettings} instance used by this plugin for handling audience-related tasks.
     *
     * @return the {@link SpigotCommonsSettings} instance.
     */
    public @NotNull SpigotCommonsSettings getSettings() {
        return ((SpigotCommonsSettings) settings);
    }

    /**
     * Performs setup tasks based on the provided {@link CommonsSettings}.
     * <p>
     * This includes registering commands and event listeners if the corresponding settings are enabled.
     * </p>
     */
    protected void setup() {
        if (getSettings().getBookCommand() != null) {
            CommandRegister.newInstance().register((SpigotCrispyPlugin) plugin, plugin.getName(), new BookActionCommand(getSettings().getBookCommand()));
        }
        if (getSettings().isMenusEnabled()) {
            Bukkit.getPluginManager().registerEvents(MenuListener.newInstance(), getBukkitPlugin());
        }
        Bukkit.getPluginManager().registerEvents(this, getBukkitPlugin());
    }

    @Override
    public boolean registerCommand(@NotNull CrispyPlugin plugin, @NotNull String fallbackPrefix, @NotNull PlatformCommand command) {
        return CommandRegister.newInstance().register(((SpigotCrispyPlugin) plugin), fallbackPrefix, ((SpigotCommand) command).getSpigot());
    }

    @Override
    public void unregisterCommand(@NotNull CrispyPlugin plugin, @NotNull PlatformCommand command) {
        SimpleCommandMap map = (SimpleCommandMap) CommandRegister.newInstance().unregister((SpigotCrispyPlugin) plugin, ((SpigotCommand) command).getSpigot().getLabel());
        ((SpigotCommand) command).getSpigot().unregister(map);
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
