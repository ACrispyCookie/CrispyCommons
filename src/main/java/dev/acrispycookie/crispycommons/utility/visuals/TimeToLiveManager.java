package dev.acrispycookie.crispycommons.utility.visuals;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TimeToLiveManager {

    private TimeToLiveElement<?> element;
    private final Map<UUID, Integer> tasks = new HashMap<>();
    private final Map<UUID, Boolean> expired = new HashMap<>();

    public TimeToLiveManager(TimeToLiveElement<?> element) {
        this.element = element;
    }

    public boolean isInfinite() {
        return element.isContext(Void.class) && element.getRaw(null) == -1;
    }

    public boolean isExpired(OfflinePlayer player) {
        return isExpired(player.getUniqueId());
    }

    public boolean isExpired(UUID uuid) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class))
            return expired.containsKey(null) && expired.get(null);
        else
            return expired.containsKey(uuid) && expired.get(uuid);
    }

    public boolean isRunning(OfflinePlayer player) {
        return isRunning(player.getUniqueId());
    }

    public boolean isRunning(UUID uuid) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class))
            return tasks.containsKey(null) && Bukkit.getScheduler().isCurrentlyRunning(tasks.get(null));
        else
            return tasks.containsKey(uuid) && Bukkit.getScheduler().isCurrentlyRunning(tasks.get(uuid));
    }

    public void setupGlobal(Runnable globalRunnable, Function<OfflinePlayer, Void> personalRunnable, Set<OfflinePlayer> currentViewers) {
        if (element.getStartMode() != TimeToLiveElement.StartMode.GLOBAL)
            return;

        element.setStartTimestamp(System.currentTimeMillis());
        if (element.isContext(Void.class))
            startGlobalTask(currentViewers, globalRunnable, element.getRaw(null));
        else
            currentViewers.forEach((v) -> startPlayerTask(v.getUniqueId(), () -> personalRunnable.apply(v), element.getFromContext(OfflinePlayer.class, v)));

    }

    public void setupPerPlayer(Runnable runnable, OfflinePlayer player) {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL) {
            if (!isRunning(player))
                checkForRemainingTime(runnable, player);
            return;
        }

        startPlayerTask(player.getUniqueId(), runnable, element.getFromContext(OfflinePlayer.class, player));
    }

    public void cancelGlobal() {
        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class) && isRunning((UUID) null))
            Bukkit.getScheduler().cancelTask(tasks.get(null));
    }

    public void cancelPlayer(OfflinePlayer player) {
        if (!isRunning(player))
            return;

        if (element.getStartMode() == TimeToLiveElement.StartMode.GLOBAL && element.isContext(Void.class))
            return;

        Bukkit.getScheduler().cancelTask(tasks.get(player.getUniqueId()));
    }

    public void resetExpired(Function<OfflinePlayer, Void> hideFromPlayer, Set<OfflinePlayer> currentViewers) {
        for (UUID uuid : expired.keySet()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            if (expired.get(uuid) && player.isOnline())
                startPlayerTask(uuid, () -> hideFromPlayer.apply(player), element.getFromContext(OfflinePlayer.class, player));
        }
    }

    public void resetExpired(UUID uuid, Runnable hideFromPlayer) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (expired.get(uuid) && player.isOnline())
            startPlayerTask(uuid, hideFromPlayer, element.getFromContext(OfflinePlayer.class, player));
    }

    public TimeToLiveElement<?> getElement() {
        return element;
    }

    public void setElement(TimeToLiveElement<?> element) {
        this.element = element;
    }

    public Set<OfflinePlayer> getExpired() {
        return expired.keySet().stream().filter(expired::get).map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }

    private void checkForRemainingTime(Runnable runnable, OfflinePlayer player) {
        long timeToLive = element.getFromContext(OfflinePlayer.class, player);
        long newDuration = timeToLive - (System.currentTimeMillis() - element.getStartTimestamp()) / 50;

        if (newDuration > 0)
            startPlayerTask(player.getUniqueId(), runnable, newDuration);
        else if(timeToLive != -1)
            expired.put(player.getUniqueId(), true);
    }

    private void startGlobalTask(Set<OfflinePlayer> viewers, Runnable runnable, long duration) {
        viewers.forEach((v) -> expired.put(v.getUniqueId(), false));
        if (duration < 0)
            return;
        tasks.put(null, Bukkit.getScheduler().scheduleSyncDelayedTask(CrispyCommons.getPlugin(), () -> {
            runnable.run();
            viewers.forEach((v) -> expired.put(v.getUniqueId(), true));
        }, duration));
    }

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
