package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual {

    protected T data;
    protected boolean isDisplayed = false;
    protected final Set<OfflinePlayer> receivers = new HashSet<>();
    protected long timeToLive;
    protected abstract void onShow();
    protected abstract void onHide();
    protected abstract void onUpdate();

    public AbstractVisual(T data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        this.data = data;
        this.receivers.addAll(receivers);
        this.timeToLive = timeToLive;
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

        onShow();
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        onHide();
    }

    @Override
    public void update() {
        if (!isDisplayed) return;

        onUpdate();
    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        receivers.add(player);
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        receivers.remove(player);
    }

    @Override
    public void setPlayers(Collection<? extends OfflinePlayer> players) {
        receivers.clear();
        receivers.addAll(players);
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
