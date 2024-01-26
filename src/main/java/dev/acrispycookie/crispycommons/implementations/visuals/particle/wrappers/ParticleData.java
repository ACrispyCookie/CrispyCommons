package dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import org.bukkit.scheduler.BukkitTask;

public class ParticleData<T extends CrispyEffect> implements VisualData {
    private ParticleElement<T> element;
    private long duration;
    private long period;
    private BukkitTask task;

    public ParticleData(ParticleElement<T> element, long duration, long period) {
        this.element = element;
        this.duration = duration < 0 ? -1 : duration;
        this.period = period < 1 ? -1 : period;
    }

    public ParticleElement<T> getElement() {
        return element;
    }

    public void setElement(ParticleElement<T> element) {
        this.element = element;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }
}
