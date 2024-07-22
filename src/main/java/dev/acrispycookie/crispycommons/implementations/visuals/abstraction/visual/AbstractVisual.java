package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.visuals.TimeToLiveManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual, Listener {

    private final Set<UUID> receivers = new HashSet<>();
    private final Set<UUID> currentlyDisplaying = new HashSet<>();
    protected TimeToLiveManager timeToLive;
    private final UpdateMode updateMode;
    protected final boolean isPublic;
    protected boolean isDisplayed = false;
    protected T data;
    protected abstract void prepareShow();
    protected abstract void prepareHide();
    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void globalUpdate();
    protected abstract void perPlayerUpdate(Player p);

    public AbstractVisual(T data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode mode, boolean isPublic) {
        this.data = data;
        this.updateMode = mode;
        this.isPublic = isPublic;
        this.receivers.addAll(receivers.stream().map(OfflinePlayer::getUniqueId).collect(Collectors.toSet()));
        this.timeToLive = new TimeToLiveManager(timeToLive);
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (isPublic) {
            addPlayer(event.getPlayer());
            return;
        }
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()))
            showInternal(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent event) {
        if (isPublic) {
            removePlayer(event.getPlayer());
            return;
        }
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()) && currentlyDisplaying.contains(event.getPlayer().getUniqueId()))
            hideInternal(event.getPlayer());
    }

    protected void showInternal(Player p) {
        if (isExpired(p))
            return;
        if (currentlyDisplaying.isEmpty())
            prepareShow();
        currentlyDisplaying.add(p.getUniqueId());
        show(p);
        timeToLive.setupPerPlayer(() -> hideInternal(p), p);
    }

    protected void hideInternal(Player p) {
        currentlyDisplaying.remove(p.getUniqueId());
        if (currentlyDisplaying.isEmpty())
            prepareHide();
        hide(p);
        timeToLive.cancelPlayer(p);
    }

    @Override
    public void show() {
        if (isDisplayed) return;
        isDisplayed = true;

        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> {
            try {
                data.assertReady(p.getPlayer());
                showInternal(p.getPlayer());
            } catch (VisualData.VisualNotReadyException e) {
                CrispyLogger.printException(CrispyCommons.getPlugin(), e, "This visual couldn't be displayed to the player: " + p.getName());
            }
        });

        timeToLive.setupGlobal(this::hide, (p) -> {
            this.hideInternal((Player) p);
            return null;
        }, currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> hideInternal(p.getPlayer()));

        timeToLive.cancelGlobal();
    }

    @Override
    public void update() {
        if (!isDisplayed) return;

        if (updateMode == UpdateMode.BOTH) {
            globalUpdate();
            currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> perPlayerUpdate(p.getPlayer()));
        } else if (updateMode == UpdateMode.GLOBAL) {
            globalUpdate();
        } else if (updateMode == UpdateMode.PER_PLAYER) {
            currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> perPlayerUpdate(p.getPlayer()));
        }
    }

    public void destroy() {
        if (isDisplayed)
            hide();
        receivers.clear();
        currentlyDisplaying.clear();
        data = null;
        timeToLive = null;
        HandlerList.unregisterAll(this);
    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        if (receivers.contains(player.getUniqueId())) return;

        receivers.add(player.getUniqueId());
        if (player.isOnline() && isDisplayed)
            showInternal(player.getPlayer());
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        if (!receivers.contains(player.getUniqueId())) return;

        if (player.isOnline() && isDisplayed)
            hideInternal(player.getPlayer());
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
    public void resetExpired() {
        Set<OfflinePlayer> expired = timeToLive.getExpired();
        timeToLive.resetExpired((p) -> {
            hideInternal((Player) p);
            return null;
        }, currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));

        for (OfflinePlayer player : expired) {
            if (!player.isOnline())
                return;

            Player onlinePlayer = (Player) player;
            if (currentlyDisplaying.isEmpty())
                prepareShow();
            currentlyDisplaying.add(onlinePlayer.getUniqueId());
            show(onlinePlayer);
        }
    }

    @Override
    public void resetExpired(Player player) {
        if (!isExpired(player))
            return;

        timeToLive.resetExpired(player.getUniqueId(), () -> hideInternal(player));

        if (currentlyDisplaying.isEmpty())
            prepareShow();
        currentlyDisplaying.add(player.getUniqueId());
        show(player);
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        return receivers.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }

    @Override
    public Set<Player> getCurrentlyViewing() {
        return currentlyDisplaying.stream().map(Bukkit::getPlayer).collect(Collectors.toSet());
    }

    @Override
    public void setTimeToLive(TimeToLiveElement<?> timeToLive) {
        this.timeToLive.setElement(timeToLive);
    }

    @Override
    public TimeToLiveElement<?> getTimeToLive() {
        return timeToLive.getElement();
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    @Override
    public boolean isAnyoneWatching() {
        return isDisplayed && !currentlyDisplaying.isEmpty();
    }

    @Override
    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public boolean isWatching(Player player) {
        return isDisplayed && currentlyDisplaying.contains(player.getUniqueId());
    }

    @Override
    public boolean isExpired(OfflinePlayer player) {
        return timeToLive.isExpired(player);
    }

    @Override
    public boolean isRunning(OfflinePlayer player) {
        return timeToLive.isRunning(player);
    }

    public enum UpdateMode {
        PER_PLAYER,
        GLOBAL,
        BOTH
    }
}
