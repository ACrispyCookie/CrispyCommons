package dev.acrispycookie.crispycommons.particles.implementations;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class ColoredCrispyParticle extends SimpleCrispyParticle {

    private float r = 0;
    private float g = 0;
    private float b = 0;

    public ColoredCrispyParticle(JavaPlugin plugin, long duration, long period, Location location, Color color) {
        super(plugin, duration, period, Effect.COLOURED_DUST, location, 0);
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    public ColoredCrispyParticle(JavaPlugin plugin, long duration, long period, Location location, int r, int b, int g) {
        super(plugin, duration, period, Effect.COLOURED_DUST, location, 0);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void playOnce(Player player) {
        player.spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 160);
    }
}
