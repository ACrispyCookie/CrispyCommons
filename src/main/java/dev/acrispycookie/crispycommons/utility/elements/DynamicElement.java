package dev.acrispycookie.crispycommons.utility.elements;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import org.bukkit.Bukkit;

import java.util.function.Supplier;

public abstract class DynamicElement<T> extends AbstractCrispyElement<T> {

    protected Supplier<T> supplier;
    private final int period;
    private final boolean async;
    private int taskId;
    protected abstract void update();

    public DynamicElement(Supplier<T> supplier, int period, boolean async) {
        super(supplier.get());
        this.supplier = supplier;
        this.period = period;
        this.async = async;
    }

    public void start() {
        if (async) {
            taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(CrispyCommons.getPlugin(), () -> {
                this.element = supplier.get();
                update();
            }, period, period).getTaskId();
            return;
        }

        taskId = Bukkit.getScheduler().runTaskTimer(CrispyCommons.getPlugin(), () -> {
            this.element = supplier.get();
            update();
        }, period, period).getTaskId();
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
