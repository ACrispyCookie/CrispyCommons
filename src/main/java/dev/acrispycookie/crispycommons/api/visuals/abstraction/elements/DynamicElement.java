package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements;

import dev.acrispycookie.crispycommons.CrispyCommons;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Supplier;

public abstract class DynamicElement<T> extends AbstractCrispyElement<T> {

    protected Supplier<? extends T> supplier;
    private final int period;
    private final boolean async;
    private int taskId;
    protected abstract void update();

    public DynamicElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier.get());
        this.supplier = supplier;
        this.period = period;
        this.async = async;
    }

    public void start() {
        if (async) {
            taskId = new BukkitRunnable() {
                @Override
                public void run() {
                    element = supplier.get();
                    update();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), period, period).getTaskId();
            return;
        }

        taskId = new BukkitRunnable() {
            @Override
            public void run() {
                element = supplier.get();
                update();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), period, period).getTaskId();
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public int getPeriod() {
        return period;
    }

    public boolean isAsync() {
        return async;
    }
}
