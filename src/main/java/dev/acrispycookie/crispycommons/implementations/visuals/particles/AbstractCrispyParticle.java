package dev.acrispycookie.crispycommons.implementations.visuals.particles;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.utility.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.awt.*;
import java.util.Set;

public abstract class AbstractCrispyParticle<T extends CrispyEffect> extends AbstractCrispyAccessibleVisual<CrispyEffect> implements CrispyParticle<T> {

    protected final JavaPlugin plugin;
    protected BukkitTask bukkitTask;
    protected final ParticleElement<T> element;
    protected long duration;
    protected long period;
    protected abstract void playOnce(Player p);

    protected AbstractCrispyParticle(ParticleElement<T> element, long duration, long period, Set<? extends Player> receivers) {
        super(receivers);
        this.plugin = CrispyCommons.getPlugin();
        this.bukkitTask = null;
        this.element = element;
        this.duration = duration < 0 ? -1 : duration;
        this.period = period < 1 ? -1 : period;
    }

    protected AbstractCrispyParticle(ParticleElement<T> element, Set<? extends Player> receivers) {
        super(receivers);
        this.plugin = CrispyCommons.getPlugin();
        this.bukkitTask = null;
        this.element = element;
        this.duration = -1;
        this.period = -1;
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        if (duration == -1 || period == -1) {
            receivers.forEach(this::playOnce);
            isDisplayed = false;
            return;
        }



        bukkitTask = new BukkitRunnable() {
            long i = 0;
            @Override
            public void run() {
                if (i >= duration) {
                    cancel();
                    isDisplayed = false;
                    return;
                }
                receivers.forEach(AbstractCrispyParticle.this::playOnce);
                i += period;
            }
        }.runTaskTimer(plugin, 0, period);
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        if (bukkitTask != null)
            bukkitTask.cancel();
        isDisplayed = false;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public long getPeriod() {
        return period;
    }

    @Override
    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public T getCurrentContent() {
        return element.getContent();
    }
}
