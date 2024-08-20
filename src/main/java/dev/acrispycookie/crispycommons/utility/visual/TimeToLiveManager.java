package dev.acrispycookie.crispycommons.utility.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Manages the lifecycle of a {@link TimeToLiveElement} for individual players or globally.
 * <p>
 * This class handles the timing and expiration of elements, allowing for tasks to be scheduled
 * when the time-to-live (TTL) expires. It supports both global TTL management and per-player TTL management.
 * </p>
 */
public class TimeToLiveManager {

    /**
     * The {@link TimeToLiveElement} being managed by this {@code TimeToLiveManager}.
     * <p>
     * This element defines the time-to-live (TTL) for various contexts, determining how long they remain active
     * before expiring. The manager uses this element to control when and how TTL tasks are scheduled and executed.
     * </p>
     */
    private TimeToLiveElement<?> element;

    /**
     * A map that stores the task IDs associated with each player's TTL task.
     * <p>
     * The keys in this map are the UUIDs of players, and the values are the IDs of the scheduled tasks
     * that manage their TTL. This allows the manager to track and control the lifecycle of each player's TTL task.
     * </p>
     */
    private final Map<UUID, Integer> tasks = new HashMap<>();

    /**
     * A map that tracks whether a player's TTL has expired.
     * <p>
     * The keys in this map are the UUIDs of players, and the values are booleans indicating whether their TTL
     * has expired ({@code true}) or is still active ({@code false}). This allows the manager to quickly check
     * the expiration status of each player.
     * </p>
     */
    private final Map<UUID, Boolean> expired = new HashMap<>();


    /**
     * Constructs a {@code TimeToLiveManager} with the specified {@link TimeToLiveElement}.
     *
     * @param element the TTL element to be managed.
     */
    public TimeToLiveManager(TimeToLiveElement<?> element) {
        this.element = element;
    }

    /**
     * Checks if the managed TTL element is infinite.
     *
     * @return {@code true} if the TTL element is infinite; {@code false} otherwise.
     */
    public boolean isInfinite() {
        return element.isContext(Void.class) && element.getRaw(null) == -1;
    }

    /**
     * Checks if the TTL for the specified player has expired.
     *
     * @param player the player to check.
     * @return {@code true} if the TTL has expired; {@code false} otherwise.
     */
    public boolean isExpired(OfflinePlayer player) {
        return isExpired(player.getUniqueId());
    }

    /**
     * Checks if the TTL for the specified UUID has expired.
     *
     * @param uuid the UUID to check.
     * @return {@code true} if the TTL has expired; {@code false} otherwise.
     */
    public boolean isExpired(UUID uuid) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class)) {
            return expired.containsKey(null) && expired.get(null);
        } else {
            return expired.containsKey(uuid) && expired.get(uuid);
        }
    }

    /**
     * Checks if the TTL task for the specified player is currently running.
     *
     * @param player the player to check.
     * @return {@code true} if the TTL task is running; {@code false} otherwise.
     */
    public boolean isRunning(OfflinePlayer player) {
        return isRunning(player.getUniqueId());
    }

    /**
     * Checks if the TTL task for the specified UUID is currently running.
     *
     * @param uuid the UUID to check.
     * @return {@code true} if the TTL task is running; {@code false} otherwise.
     */
    public boolean isRunning(UUID uuid) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class)) {
            return tasks.containsKey(null) && Bukkit.getScheduler().isCurrentlyRunning(tasks.get(null));
        } else {
            return tasks.containsKey(uuid) && Bukkit.getScheduler().isCurrentlyRunning(tasks.get(uuid));
        }
    }

    /**
     * Sets up a global TTL task with a global and personal runnable, and a set of current viewers.
     *
     * @param globalRunnable the runnable to execute when the global TTL expires.
     * @param personalRunnable the runnable to execute for each player when their TTL expires.
     * @param currentViewers the set of current viewers to monitor.
     */
    public void setupGlobal(Runnable globalRunnable, Function<OfflinePlayer, Void> personalRunnable, Set<OfflinePlayer> currentViewers) {
        if (element.getStartMode() != TimeToLiveElement.StartMode.GLOBAL)
            return;

        element.setStartTimestamp(System.currentTimeMillis());
        if (element.isContext(Void.class))
            startGlobalTask(currentViewers, globalRunnable, element.getRaw(null));
        else
            currentViewers.forEach((v) -> startPlayerTask(v.getUniqueId(), () -> personalRunnable.apply(v), element.getFromContext(OfflinePlayer.class, v)));
    }

    /**
     * Sets up a per-player TTL task with the specified runnable.
     *
     * @param runnable the runnable to execute when the player's TTL expires.
     * @param player the player whose TTL is being managed.
     */
    public void setupPerPlayer(Runnable runnable, OfflinePlayer player) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL) {
            if (!isRunning(player))
                checkForRemainingTime(runnable, player);
            return;
        }

        startPlayerTask(player.getUniqueId(), runnable, element.getFromContext(OfflinePlayer.class, player));
    }

    /**
     * Cancels the global TTL task if it is running.
     */
    public void cancelGlobal() {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class) && isRunning((UUID) null))
            Bukkit.getScheduler().cancelTask(tasks.get(null));
    }

    /**
     * Cancels the TTL task for the specified player if it is running.
     *
     * @param player the player whose TTL task should be canceled.
     */
    public void cancelPlayer(OfflinePlayer player) {
        if (!isRunning(player))
            return;

        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class))
            return;

        Bukkit.getScheduler().cancelTask(tasks.get(player.getUniqueId()));
    }

    /**
     * Resets expired TTL tasks for a set of players and starts their associated hide tasks.
     *
     * @param hideFromPlayer the function to execute to hide the effect from the player.
     * @param currentViewers the set of current viewers to monitor.
     */
    public void resetExpired(Function<OfflinePlayer, Void> hideFromPlayer, Set<OfflinePlayer> currentViewers) {
        for (UUID uuid : expired.keySet()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            if (expired.get(uuid) && player.isOnline())
                startPlayerTask(uuid, () -> hideFromPlayer.apply(player), element.getFromContext(OfflinePlayer.class, player));
        }
    }

    /**
     * Resets the expired TTL task for the specified UUID and starts the associated hide task.
     *
     * @param uuid the UUID of the player whose TTL task should be reset.
     * @param hideFromPlayer the runnable to execute to hide the effect from the player.
     */
    public void resetExpired(UUID uuid, Runnable hideFromPlayer) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (expired.get(uuid) && player.isOnline())
            startPlayerTask(uuid, hideFromPlayer, element.getFromContext(OfflinePlayer.class, player));
    }

    /**
     * Returns the current {@link TimeToLiveElement} managed by this manager.
     *
     * @return the TTL element.
     */
    public TimeToLiveElement<?> getElement() {
        return element;
    }

    /**
     * Sets the {@link TimeToLiveElement} to be managed by this manager.
     *
     * @param element the new TTL element.
     */
    public void setElement(TimeToLiveElement<?> element) {
        this.element = element;
    }

    /**
     * Returns a set of players whose TTL tasks have expired.
     *
     * @return a set of players whose TTL tasks have expired.
     */
    public Set<OfflinePlayer> getExpired() {
        return expired.keySet().stream().filter(expired::get).map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }

    /**
     * Checks for remaining time for a player's TTL task and schedules the runnable if there is time left.
     *
     * @param runnable the runnable to execute when the TTL expires.
     * @param player the player whose TTL is being checked.
     */
    private void checkForRemainingTime(Runnable runnable, OfflinePlayer player) {
        long timeToLive = element.getFromContext(OfflinePlayer.class, player);
        long newDuration = timeToLive - (System.currentTimeMillis() - element.getStartTimestamp()) / 50;

        if (newDuration > 0)
            startPlayerTask(player.getUniqueId(), runnable, newDuration);
        else if (timeToLive != -1)
            expired.put(player.getUniqueId(), true);
    }

    /**
     * Starts a global TTL task that will execute the given runnable after the specified duration.
     *
     * @param viewers the set of players to monitor.
     * @param runnable the runnable to execute when the TTL expires.
     * @param duration the duration of the TTL in ticks.
     */
    private void startGlobalTask(Set<OfflinePlayer> viewers, Runnable runnable, long duration) {
        viewers.forEach((v) -> expired.put(v.getUniqueId(), false));
        if (duration < 0)
            return;
        tasks.put(null, Bukkit.getScheduler().scheduleSyncDelayedTask(CrispyCommons.getPlugin(), () -> {
            runnable.run();
            viewers.forEach((v) -> expired.put(v.getUniqueId(), true));
        }, duration));
    }

    /**
     * Starts a per-player TTL task that will execute the given runnable after the specified duration.
     *
     * @param uuid the UUID of the player.
     * @param runnable the runnable to execute when the TTL expires.
     * @param duration the duration of the TTL in ticks.
     */
    private void startPlayerTask(UUID uuid, Runnable runnable, long duration) {
        expired.put(uuid, false);
        if (duration < 0)
            return;
        tasks.put(uuid, Bukkit.getScheduler().scheduleSyncDelayedTask(CrispyCommons.getPlugin(), () -> {
            runnable.run();
            expired.put(uuid, true);
        }, duration));
    }
}

