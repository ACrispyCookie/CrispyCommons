package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal;

import dev.acrispycookie.crispycommons.CrispyCommons;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.function.Function;

public abstract class PersonalDynamicElement<T> extends PersonalAbstractElement<T> {

    protected Function<OfflinePlayer, ? extends T> supplier;
    private final int period;
    protected boolean async;
    private BukkitTask bukkitTask;
    protected Runnable update;

    protected PersonalDynamicElement(Function<OfflinePlayer, ? extends T> supplier, int period, boolean async) {
        super(new HashMap<>());
        this.supplier = supplier;
        this.period = period;
        this.async = async;
    }

    public void start() {
        if(period == -1 || update == null || bukkitTask != null)
            return;

        if (async) {
            bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                elements.forEach((p, element) -> elements.put(p, getRaw(p)));
                update.run();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), period, period);
            return;
        }

        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                elements.forEach((p, element) -> elements.put(p, getRaw(p)));
                update.run();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), period, period);
    }

    public void stop() {
        if (bukkitTask == null)
            return;
        bukkitTask.cancel();
        bukkitTask = null;
    }

    public boolean isDynamic() {
        return period > 0;
    }

    public boolean isGlobal() {
        return false;
    }

    public int getPeriod() {
        return period;
    }

    public boolean isAsync() {
        return async;
    }

    public void setUpdate(Runnable update) {
        if (this.update != null)
            return;
        this.update = update;
    }
}
