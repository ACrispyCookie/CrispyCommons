package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual, Listener {

    private final Set<UUID> receivers = new HashSet<>();
    private final UpdateMode updateMode;
    protected long onlineReceivers;
    protected T data;
    protected boolean isDisplayed = false;
    protected long timeToLive;
    protected abstract void prepareShow();
    protected abstract void prepareHide();
    protected abstract void globalUpdate();
    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void perPlayerUpdate(Player p);

    public AbstractVisual(T data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode mode) {
        this.data = data;
        this.updateMode = mode;
        this.receivers.addAll(receivers.stream().map(OfflinePlayer::getUniqueId).collect(Collectors.toSet()));
        onlineReceivers = this.receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).count();
        this.timeToLive = timeToLive;
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (receivers.contains(event.getPlayer().getUniqueId())) {
            if (isDisplayed)
                show(event.getPlayer());
            ++onlineReceivers;
            if (onlineReceivers == 1)
                prepareShow();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent event) {
        if (receivers.contains(event.getPlayer().getUniqueId())) {
            if (isDisplayed)
                hide(event.getPlayer());
            --onlineReceivers;
            if (onlineReceivers == 0)
                prepareHide();
        }
    }

    @Override
    public void show() {
        if (isDisplayed) return;
        try {
            data.assertReady();
        } catch (VisualData.VisualNotReadyException e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "This visual is not ready to be displayed!");
            return;
        }
        isDisplayed = true;

        if (timeToLive > 0)
            new BukkitRunnable() {
            @Override
            public void run() {
                hide();
            }
        }.runTaskLater(CrispyCommons.getPlugin(), timeToLive);

        if (onlineReceivers > 0)
            prepareShow();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> show(p.getPlayer()));
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        if (onlineReceivers > 0)
            prepareHide();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> hide(p.getPlayer()));
    }

    @Override
    public void update() {
        if (!isDisplayed) return;

        if (updateMode == UpdateMode.BOTH) {
            globalUpdate();
            receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> perPlayerUpdate(p.getPlayer()));
        } else if (updateMode == UpdateMode.GLOBAL) {
            globalUpdate();
        } else if (updateMode == UpdateMode.PER_PLAYER) {
            receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> perPlayerUpdate(p.getPlayer()));
        }
    }

    public void destroy() {
        if (isDisplayed) hide();
        receivers.clear();
        data = null;
        HandlerList.unregisterAll(this);
    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        if (receivers.contains(player.getUniqueId())) return;

        receivers.add(player.getUniqueId());
        if (player.isOnline()) {
            if (isDisplayed)
                show(player.getPlayer());

            ++onlineReceivers;
            if (onlineReceivers == 1)
                prepareShow();
        }
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        if (!receivers.contains(player.getUniqueId())) return;

        if (player.isOnline()) {
            if (isDisplayed)
                hide(player.getPlayer());

            --onlineReceivers;
            if (onlineReceivers == 0)
                prepareHide();
        }
        receivers.remove(player.getUniqueId());
    }

    @Override
    public void setPlayers(Collection<? extends OfflinePlayer> players) {
        Set <OfflinePlayer> toRemove = receivers.stream().map(Bukkit::getOfflinePlayer).filter(p -> !players.contains(p)).collect(Collectors.toSet());
        Set <OfflinePlayer> toAdd = players.stream().filter(p -> !receivers.contains(p.getUniqueId())).collect(Collectors.toSet());
        toRemove.forEach(this::removePlayer);
        toAdd.forEach(this::addPlayer);
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        return receivers.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }

    @Override
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public long getTimeToLive() {
        return timeToLive;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    protected T getData() {
        return data;
    }

    public enum UpdateMode {
        PER_PLAYER,
        GLOBAL,
        BOTH;
    }
}
