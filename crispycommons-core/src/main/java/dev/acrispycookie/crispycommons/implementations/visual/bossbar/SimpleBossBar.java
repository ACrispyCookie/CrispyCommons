package dev.acrispycookie.crispycommons.implementations.visual.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.bossbar.data.BossBarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

/**
 * A concrete implementation of {@link AbstractBossBar} that represents a simple boss bar.
 * <p>
 * {@code SimpleBossBar} handles the display and management of boss bars for individual players, including
 * showing, hiding, and updating the boss bar's text, color, progress, and overlay.
 * </p>
 */
public class SimpleBossBar extends AbstractBossBar {

    /**
     * A map storing the boss bars associated with each player.
     */
    private final HashMap<OfflinePlayer, BossBar> bossBars = new HashMap<>();

    /**
     * Constructs a {@code SimpleBossBar} with the specified parameters.
     *
     * @param data       the {@link BossBarData} that contains the visual and functional data for the boss bar.
     * @param receivers  the set of players who will receive the boss bar.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the boss bar.
     * @param isPublic   whether the boss bar should be visible to all players.
     */
    public SimpleBossBar(@NotNull BossBarData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, isPublic);
    }

    /**
     * Displays the boss bar to the specified player.
     * <p>
     * This method creates a new boss bar instance for the player and displays it using the {@link Adventure} API.
     * The boss bar's text, progress, color, and overlay are set based on the player's context.
     * </p>
     *
     * @param player the player to whom the boss bar will be shown.
     */
    @Override
    protected void show(@NotNull Player player) {
        bossBars.put(player, BossBar.bossBar(
                data.getText().getFromContext(OfflinePlayer.class, player),
                data.getProgress().getFromContext(OfflinePlayer.class, player),
                data.getColor().getFromContext(OfflinePlayer.class, player),
                data.getOverlay().getFromContext(OfflinePlayer.class, player)
        ));
        Audience audience = CrispyCommons.getBukkitAudiences().player(player);
        audience.showBossBar(bossBars.get(player));
    }

    /**
     * Hides the boss bar from the specified player.
     * <p>
     * This method removes the boss bar from the player's view using the {@link Adventure} API.
     * </p>
     *
     * @param player the player from whom the boss bar will be hidden.
     */
    @Override
    protected void hide(@NotNull Player player) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(player);
        audience.hideBossBar(bossBars.get(player));
    }

    /**
     * Updates the boss bar for a specific player.
     * <p>
     * This method updates the boss bar's text, progress, color, and overlay for the player.
     * </p>
     *
     * @param player the player for whom the boss bar will be updated.
     */
    @Override
    protected void perPlayerUpdate(@NotNull Player player) {
        BossBar bossBar = bossBars.get(player);
        bossBar.name(data.getText().getFromContext(OfflinePlayer.class, player));
        bossBar.color(data.getColor().getFromContext(OfflinePlayer.class, player));
        bossBar.progress(data.getProgress().getFromContext(OfflinePlayer.class, player));
        bossBar.overlay(data.getOverlay().getFromContext(OfflinePlayer.class, player));
    }

    @Override
    public void updateText() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player p : viewers) {
            BossBar bossBar = bossBars.get(p);
            bossBar.name(data.getText().getFromContext(OfflinePlayer.class, p));
        }
    }

    @Override
    public void updateProgress() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player p : viewers) {
            BossBar bossBar = bossBars.get(p);
            bossBar.progress(data.getProgress().getFromContext(OfflinePlayer.class, p));
        }
    }

    @Override
    public void updateColor() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player p : viewers) {
            BossBar bossBar = bossBars.get(p);
            bossBar.color(data.getColor().getFromContext(OfflinePlayer.class, p));
        }
    }

    @Override
    public void updateOverlay() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player p : viewers) {
            BossBar bossBar = bossBars.get(p);
            bossBar.overlay(data.getOverlay().getFromContext(OfflinePlayer.class, p));
        }
    }
}

