package dev.acrispycookie.crispycommons;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpigotCommonsSettings extends CommonsSettings {

    /**
     * Indicates whether the book action feature is enabled.
     * <p>
     * If {@code true}, the book action feature is enabled, allowing specific commands to be associated
     * with books in the plugin. If {@code false}, the feature is disabled.
     * </p>
     */
    private boolean bookActionEnabled;

    /**
     * The command associated with the book action feature.
     * <p>
     * This string represents the command that is executed when the book action feature is triggered.
     * If the book action feature is disabled, this value may be {@code null}.
     * </p>
     */
    private String bookCommand;

    /**
     * Indicates whether the menu management feature is enabled.
     * <p>
     * If {@code true}, the menu management feature is enabled, allowing menus to be created, managed,
     * and interacted with. If {@code false}, the feature is disabled.
     * </p>
     */
    private boolean menusEnabled;

    /**
     * The maximum number of menus that can be stored in the menu history for each player.
     * <p>
     * This integer value determines how many previous menus are retained in memory for each player,
     * allowing them to navigate back through their menu history up to this limit.
     * </p>
     */
    private int maximumMenuHistory;


    /**
     * Constructs a new {@code CommonsSettings} instance with specified configurations.
     *
     * @param bookActionEnabled   whether the book action feature is enabled.
     * @param bookCommand         the command associated with the book action.
     * @param menusEnabled        whether the menu management feature is enabled.
     * @param maximumMenuHistory  the maximum number of menus that can be stored in the history.
     */
    public SpigotCommonsSettings(boolean bookActionEnabled, @NotNull String bookCommand, boolean menusEnabled, int maximumMenuHistory) {
        this.bookActionEnabled = bookActionEnabled;
        this.bookCommand = bookCommand;
        this.menusEnabled = menusEnabled;
        this.maximumMenuHistory = maximumMenuHistory;
    }

    /**
     * Constructs a new {@code CommonsSettings} instance with default configurations.s
     */
    public SpigotCommonsSettings() {
        this.bookActionEnabled = false;
        this.menusEnabled = true;
        this.maximumMenuHistory = 10;
    }

    /**
     * Sets the book action command and enables the book action feature.
     * <p>
     * If the provided {@code bookCommand} is {@code null}, the book action feature is disabled.
     * </p>
     *
     * @param bookCommand the command associated with the book action.
     * @return this {@link CommonsSettings} instance for method chaining.
     */
    public @NotNull CommonsSettings bookAction(@Nullable String bookCommand) {
        this.bookActionEnabled = bookCommand != null;
        this.bookCommand = bookCommand;
        return this;
    }

    /**
     * Enables or disables the menu management feature.
     *
     * @param enabled {@code true} to enable the menu management feature, {@code false} to disable it.
     * @return this {@link CommonsSettings} instance for method chaining.
     */
    public @NotNull CommonsSettings setMenus(boolean enabled) {
        this.menusEnabled = enabled;
        return this;
    }

    /**
     * Sets the maximum number of menus that can be stored in the menu history for each player.
     *
     * @param maximumMenuHistory the maximum number of menus.
     * @return this {@link CommonsSettings} instance for method chaining.
     */
    public @NotNull CommonsSettings setMaximumMenuHistory(int maximumMenuHistory) {
        this.maximumMenuHistory = maximumMenuHistory;
        return this;
    }

    /**
     * Checks if the book action feature is enabled.
     *
     * @return {@code true} if the book action feature is enabled, {@code false} otherwise.
     */
    public boolean isBookActionEnabled() {
        return bookActionEnabled;
    }

    /**
     * Retrieves the command associated with the book action feature.
     *
     * @return the command string for the book action, or {@code null} if the feature is disabled.
     */
    public @Nullable String getBookCommand() {
        return bookCommand;
    }

    /**
     * Retrieves the maximum number of menus that can be stored in the menu history for each player.
     *
     * @return the maximum menu history value.
     */
    public int getMaximumMenuHistory() {
        return maximumMenuHistory;
    }

    /**
     * Checks if the menu management feature is enabled.
     *
     * @return {@code true} if the menu management feature is enabled, {@code false} otherwise.
     */
    public boolean isMenusEnabled() {
        return menusEnabled;
    }
}
