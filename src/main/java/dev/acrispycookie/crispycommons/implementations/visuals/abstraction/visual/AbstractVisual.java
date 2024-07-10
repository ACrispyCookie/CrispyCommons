package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
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
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual, Listener {

    private final Set<UUID> receivers = new HashSet<>();
    private final Set<UUID> currentlyDisplaying = new HashSet<>();
    private final UpdateMode updateMode;
    private final Map<UUID, BukkitTask> personalTtlTasks = new HashMap<>();
    private BukkitTask globalTtlTask;
    protected long onlineReceivers;
    protected T data;
    protected boolean isDisplayed = false;
    protected GeneralElement<Long> timeToLive;
    protected abstract void prepareShow();
    protected abstract void prepareHide();
    protected abstract void globalUpdate();
    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void perPlayerUpdate(Player p);

    public AbstractVisual(T data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive, UpdateMode mode) {
        this.data = data;
        this.updateMode = mode;
        this.receivers.addAll(receivers.stream().map(OfflinePlayer::getUniqueId).collect(Collectors.toSet()));
        onlineReceivers = this.receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).count();
        this.timeToLive = timeToLive;
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()) && shouldDisplay(event.getPlayer())) {
            ++onlineReceivers;
            if (onlineReceivers == 1)
                prepareShow();
            if (isDisplayed)
                showInternal(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent event) {
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()) && currentlyDisplaying.contains(event.getPlayer().getUniqueId())) {
            --onlineReceivers;
            if (onlineReceivers == 0)
                prepareHide();

            if (isDisplayed) {
                hide(event.getPlayer());
            }
        }
    }

    protected void showInternal(Player p) {
        if (personalTtlTasks.containsKey(p.getUniqueId()) && Bukkit.getScheduler().isCurrentlyRunning(personalTtlTasks.get(p.getUniqueId()).getTaskId())) {
            show(p);
            return;
        }

        if (timeToLive instanceof PersonalGeneralElement && ((PersonalGeneralElement<Long>) timeToLive).getRaw(p) > 0) {
            personalTtlTasks.put(p.getUniqueId(), new BukkitRunnable() {
                @Override
                public void run() {
                    hideInternal(p);
                }
            }.runTaskLater(CrispyCommons.getPlugin(), ((PersonalGeneralElement<Long>) timeToLive).getRaw(p)));
        }

        if (currentlyDisplaying.isEmpty())
            prepareShow();
        currentlyDisplaying.add(p.getUniqueId());
        show(p);
    }

    protected void hideInternal(Player p) {
        if (personalTtlTasks.containsKey(p.getUniqueId()) && Bukkit.getScheduler().isCurrentlyRunning(personalTtlTasks.get(p.getUniqueId()).getTaskId())) {
            personalTtlTasks.get(p.getUniqueId()).cancel();
        }

        currentlyDisplaying.remove(p.getUniqueId());
        if (currentlyDisplaying.isEmpty())
            prepareHide();
        hide(p);
    }

    @Override
    public void show() {
        if (isDisplayed) return;
        isDisplayed = true;

        if (timeToLive instanceof GlobalGeneralElement && ((GlobalGeneralElement<Long>) timeToLive).getRaw() > 0)
            globalTtlTask = new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), ((GlobalGeneralElement<Long>) timeToLive).getRaw());

        if (onlineReceivers > 0)
            prepareShow();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> {
            try {
                data.assertReady(p.getPlayer());
                showInternal(p.getPlayer());
            } catch (VisualData.VisualNotReadyException e) {
                CrispyLogger.printException(CrispyCommons.getPlugin(), e, "This visual couldn't be displayed to the player: " + p.getName());
            }
        });
    }

    @Override
    public void hide() {
        if (!isDisplayed) return;
        isDisplayed = false;

        if (Bukkit.getScheduler().isCurrentlyRunning(globalTtlTask.getTaskId()))
            globalTtlTask.cancel();

        if (onlineReceivers > 0)
            prepareHide();
        receivers.stream().map(Bukkit::getOfflinePlayer).filter(OfflinePlayer::isOnline).forEach(p -> hideInternal(p.getPlayer()));
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
            ++onlineReceivers;
            if (isDisplayed) {
                if (onlineReceivers == 1)
                    prepareShow();
                showInternal(player.getPlayer());
            }
        }
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        if (!receivers.contains(player.getUniqueId())) return;

        if (player.isOnline()) {
            --onlineReceivers;
            if (isDisplayed) {
                if (onlineReceivers == 0)
                    prepareHide();
                hideInternal(player.getPlayer());
            }
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
    public void setTimeToLive(GeneralElement<Long> timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public GeneralElement<Long> getTimeToLive() {
        return timeToLive;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    protected T getData() {
        return data;
    }

    private boolean shouldDisplay(Player p) {
        boolean isGlobal =  timeToLive instanceof GlobalGeneralElement
                && ((GlobalGeneralElement<Long>) timeToLive).getRaw() > 0
                && globalTtlTask != null
                && Bukkit.getScheduler().isCurrentlyRunning(globalTtlTask.getTaskId());
        boolean isPersonal =  timeToLive instanceof PersonalGeneralElement
                && ((PersonalGeneralElement<Long>) timeToLive).getRaw(p) > 0
                && personalTtlTasks.containsKey(p.getUniqueId())
                && Bukkit.getScheduler().isCurrentlyRunning(personalTtlTasks.get(p.getUniqueId()).getTaskId());
        boolean isInfinite = timeToLive instanceof GlobalGeneralElement
                && ((GlobalGeneralElement<Long>) timeToLive).getRaw() == -1;

        return isGlobal || isPersonal || isInfinite;
    }

    public enum UpdateMode {
        PER_PLAYER,
        GLOBAL,
        BOTH;
    }
}
