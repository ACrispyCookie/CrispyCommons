package dev.acrispycookie.crispycommons.implementations.visuals.particles.implementations.builders;

import dev.acrispycookie.crispycommons.implementations.visuals.particles.implementations.SimpleCrispyParticle;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyParticleBuilder {

    private final JavaPlugin plugin;
    private Effect effect;
    private Location location;
    private int data;
    private long duration, period;

    public CrispyParticleBuilder(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public CrispyParticleBuilder effect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public CrispyParticleBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public CrispyParticleBuilder data(int data) {
        this.data = data;
        return this;
    }

    public CrispyParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public CrispyParticleBuilder period(long period) {
        this.period = period;
        return this;
    }

    public SimpleCrispyParticle build() {
        return new SimpleCrispyParticle(plugin, duration, period, effect, location, data);
    }
}
