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
    protected T data;
    protected boolean isDisplayed = false;
    protected long timeToLive;
    protected abstract void prepareShow();
    protected abstract void prepareHide();
    protected abstract void prepareUpdate();
    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void update(Player p);

    public AbstractVisual(T data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        this.data = data;
        this.receivers.addAll(receivers.stream().map(OfflinePlayer::getUniqueId).collect(Collectors.toSet()));
        this.timeToLive = timeToLive;
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        if (isDisplayed && receivers.contains(event.getPlayer().getUniqueId())) {
            show(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event) {
        if (isDisplayed && receivers.contains(event.getPlayer().getUniqueId())) {
            hide(event.getPlayer());
        }
    }

    @Override
    public void show() {
        if (isDisplayed) return;
        isDisplayed = true;

        if (timeToLive > 0)
            new BukkitRunnable() {
            @Override
            public void run() {
                hide();
            }
        }.runTaskLater(CrispyCommons.getPlugin(), timeToLive);

        prepareShow();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> show(p.getPlayer()));
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        prepareHide();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> hide(p.getPlayer()));
    }

    @Override
    public void update() {
        if (!isDisplayed) return;

        prepareUpdate();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> update(p.getPlayer()));
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
        if (player.isOnline() && isDisplayed) {
            show(player.getPlayer());
        }
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        if (!receivers.contains(player.getUniqueId())) return;

        if (player.isOnline() && isDisplayed)
            hide(player.getPlayer());
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
}
