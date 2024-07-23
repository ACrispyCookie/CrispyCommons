package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.function.Function;

public abstract class AbstractDynamicElement<T, K> extends AbstractElement<T, K> implements DynamicElement<T, K> {

    protected Function<K, ? extends T> supplier;
    private final int period;
    private final int delay;
    protected final boolean async;
    private BukkitTask bukkitTask;
    protected Runnable update;

    protected AbstractDynamicElement(Function<K, ? extends T> supplier, int delay, int period, boolean async, Class<K> kClass) {
        super(new HashMap<>(), kClass);
        this.supplier = supplier;
        this.period = period;
        this.delay = delay;
        this.async = async;
    }

    public void start() {
        if(period == -1 || update == null || bukkitTask != null)
            return;

        if (async) {
            bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                    elements.forEach((p, element) -> elements.put(p, supplier.apply(p)));
                    update.run();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), delay, period);
            return;
        }

        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                elements.forEach((k, element) -> elements.put(k, supplier.apply(k)));
                update.run();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), delay, period);
    }

    @Override
    public T getRaw(K context ) {
        if (!elements.containsKey(context))
            elements.put(context, supplier.apply(context));
        return elements.get(context);
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

    public int getPeriod() {
        return period;
    }

    public int getDelay() {
        return delay;
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
