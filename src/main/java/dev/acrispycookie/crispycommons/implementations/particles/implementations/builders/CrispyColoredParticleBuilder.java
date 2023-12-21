package dev.acrispycookie.crispycommons.implementations.particles.implementations.builders;

import dev.acrispycookie.crispycommons.implementations.particles.implementations.ColoredCrispyParticle;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class CrispyColoredParticleBuilder {

    private final JavaPlugin plugin;

    private final Effect effect = Effect.COLOURED_DUST;
    private final int data = 0;
    private Location location;
    private Color color;
    private long duration, period;

    public CrispyColoredParticleBuilder(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public CrispyColoredParticleBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public CrispyColoredParticleBuilder color(Color color) {
        this.color = color;
        return this;
    }

    public CrispyColoredParticleBuilder color(int r, int g, int b) {
        this.color = new Color(r, g, b);
        return this;
    }

    public CrispyColoredParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public CrispyColoredParticleBuilder period(long period) {
        this.period = period;
        return this;
    }

    public ColoredCrispyParticle build() {
        return new ColoredCrispyParticle(plugin ,duration, period, location, color);
    }
}
