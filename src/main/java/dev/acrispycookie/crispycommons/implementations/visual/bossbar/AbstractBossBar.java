package dev.acrispycookie.crispycommons.implementations.visual.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visual.bossbar.CrispyBossBar;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.bossbar.data.BossBarData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Set;

/**
 * An abstract class that represents a customizable boss bar, extending the functionality of {@link AbstractVisual}
 * and implementing the {@link CrispyBossBar} interface.
 * <p>
 * {@code AbstractBossBar} provides the foundational structure for creating and managing boss bars in Minecraft,
 * including setting and updating the text, progress, color, and overlay of the boss bar. It also handles player-specific
 * events such as respawning and ensures that the boss bar is displayed correctly after such events.
 * </p>
 */
public abstract class AbstractBossBar extends AbstractVisual<BossBarData> implements CrispyBossBar {

    /**
     * Constructs an {@code AbstractBossBar} with the specified parameters.
     *
     * @param data       the {@link BossBarData} that contains the visual and functional data for the boss bar.
     * @param receivers  the set of players who will receive the boss bar.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the boss bar.
     * @param isPublic   whether the boss bar should be visible to all players.
     */
    AbstractBossBar(BossBarData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Event handler that ensures the boss bar is displayed to a player after they respawn.
     * <p>
     * The boss bar will be re-displayed 20 ticks after the player respawns if the player is a recipient
     * of the boss bar and it is currently being displayed.
     * </p>
     *
     * @param event the {@link PlayerRespawnEvent} triggered when a player respawns.
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (getPlayers().contains(event.getPlayer()) && isDisplayed) {
            Bukkit.getScheduler().runTaskLater(CrispyCommons.getPlugin(), () -> show(event.getPlayer()), 20L);
        }
    }

    /**
     * Prepares the boss bar for display by starting the text element.
     */
    @Override
    protected void prepareShow() {
        data.getText().start();
    }

    /**
     * Prepares the boss bar for hiding by stopping the text element.
     */
    @Override
    protected void prepareHide() {
        data.getText().stop();
    }

    /**
     * Updates the boss bar globally. This method is empty as global updates are not used in this implementation.
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }

    /**
     * Sets the text element of the boss bar.
     * <p>
     * This method stops the current text element, updates the boss bar with the new text,
     * and starts the text element if the boss bar is being viewed by any players.
     * </p>
     *
     * @param text the new {@link TextElement} to set as the boss bar text.
     */
    @Override
    public void setText(TextElement<?> text) {
        data.getText().stop();
        data.setText(text);
        data.getText().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getText().start();
            update();
        }
    }

    /**
     * Sets the progress of the boss bar.
     * <p>
     * This method stops the current progress element, updates the boss bar with the new progress,
     * and starts the progress element if the boss bar is being viewed by any players.
     * </p>
     *
     * @param progress the new {@link GeneralElement} to set as the boss bar progress.
     */
    @Override
    public void setProgress(GeneralElement<Float, ?> progress) {
        data.getProgress().stop();
        data.setProgress(progress);
        data.getProgress().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getProgress().start();
            update();
        }
    }

    /**
     * Sets the color of the boss bar.
     * <p>
     * This method stops the current color element, updates the boss bar with the new color,
     * and starts the color element if the boss bar is being viewed by any players.
     * </p>
     *
     * @param color the new {@link GeneralElement} to set as the boss bar color.
     */
    @Override
    public void setColor(GeneralElement<BossBar.Color, ?> color) {
        data.getColor().stop();
        data.setColor(color);
        data.getColor().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getColor().start();
            update();
        }
    }

    /**
     * Sets the overlay of the boss bar.
     * <p>
     * This method stops the current overlay element, updates the boss bar with the new overlay,
     * and starts the overlay element if the boss bar is being viewed by any players.
     * </p>
     *
     * @param overlay the new {@link GeneralElement} to set as the boss bar overlay.
     */
    @Override
    public void setOverlay(GeneralElement<BossBar.Overlay, ?> overlay) {
        data.getOverlay().stop();
        data.setOverlay(overlay);
        data.getOverlay().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getOverlay().start();
            update();
        }
    }

    /**
     * Retrieves the text element of the boss bar.
     *
     * @return the {@link TextElement} that represents the boss bar's text.
     */
    @Override
    public TextElement<?> getText() {
        return data.getText();
    }

    /**
     * Retrieves the progress element of the boss bar.
     *
     * @return the {@link GeneralElement} that represents the boss bar's progress.
     */
    @Override
    public GeneralElement<Float, ?> getProgress() {
        return data.getProgress();
    }

    /**
     * Retrieves the color element of the boss bar.
     *
     * @return the {@link GeneralElement} that represents the boss bar's color.
     */
    @Override
    public GeneralElement<BossBar.Color, ?> getColor() {
        return data.getColor();
    }

    /**
     * Retrieves the overlay element of the boss bar.
     *
     * @return the {@link GeneralElement} that represents the boss bar's overlay.
     */
    @Override
    public GeneralElement<BossBar.Overlay, ?> getOverlay() {
        return data.getOverlay();
    }
}

