package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Supplier;
import java.util.logging.Level;

public abstract class DynamicElement<T> extends AbstractElement<T> {

    protected Supplier<? extends T> supplier;
    private final int period;
    protected boolean async;
    private BukkitTask bukkitTask;
    protected Runnable update;

    protected DynamicElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier.get());
        this.supplier = supplier;
        this.period = period;
        this.async = async;
    }

    public void start() {
        if(period == -1 || update == null)
            return;

        if (async) {
            bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                    element = supplier.get();
                    update.run();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), period, period);
            return;
        }

        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                element = supplier.get();
                update.run();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), period, period);
    }

    public void stop() {
        if (bukkitTask == null)
            return;
        bukkitTask.cancel();
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
