package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.function.Function;

public abstract class AbstractDynamicElement<T, K> extends AbstractElement<T, K> implements DynamicElement<T, K> {

    protected Function<K, ? extends T> supplier;
    private final int period;
    protected final boolean async;
    private BukkitTask bukkitTask;
    protected Runnable update;

    protected AbstractDynamicElement(Function<K, ? extends T> supplier, int period, boolean async, Class<K> kClass) {
        super(new HashMap<>(), kClass);
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
                    elements.forEach((p, element) -> elements.put(p, supplier.apply(p)));
                    update.run();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), period, period);
            return;
        }

        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                elements.forEach((p, element) -> elements.put(p, supplier.apply(p)));
                update.run();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), period, period);
    }

    @Override
    public T getRaw(K player) {
        if (!elements.containsKey(player))
            elements.put(player, supplier.apply(player));
        return elements.get(player);
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

    public boolean isAsync() {
        return async;
    }

    public void setUpdate(Runnable update) {
        if (this.update != null)
            return;
        this.update = update;
    }
}
