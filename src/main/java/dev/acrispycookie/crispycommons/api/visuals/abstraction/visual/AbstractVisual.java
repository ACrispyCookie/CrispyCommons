package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual, Listener {

    private final Set<OfflinePlayer> receivers = new HashSet<>();
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
        this.receivers.addAll(receivers);
        this.timeToLive = timeToLive;
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        if (isDisplayed && receivers.contains(event.getPlayer()))
            show(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event) {
        if (isDisplayed && receivers.contains(event.getPlayer()))
            hide(event.getPlayer());
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
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> show(p.getPlayer()));
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        prepareHide();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> hide(p.getPlayer()));
    }

    @Override
    public void update() {
        if (!isDisplayed) return;

        prepareUpdate();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> update(p.getPlayer()));
    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        if (receivers.contains(player)) return;

        receivers.add(player);
        if (player.isOnline() && isDisplayed)
            show(player.getPlayer());
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        if (!receivers.contains(player)) return;

        if (player.isOnline() && isDisplayed)
            hide(player.getPlayer());
        receivers.remove(player);
    }

    @Override
    public void setPlayers(Collection<? extends OfflinePlayer> players) {
        Set <OfflinePlayer> toRemove = receivers.stream().filter(p -> !players.contains(p)).collect(Collectors.toSet());
        Set <OfflinePlayer> toAdd = players.stream().filter(p -> !receivers.contains(p)).collect(Collectors.toSet());
        toRemove.forEach(this::removePlayer);
        toAdd.forEach(this::addPlayer);
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        return receivers;
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
