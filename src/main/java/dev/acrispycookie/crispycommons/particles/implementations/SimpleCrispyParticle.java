package dev.acrispycookie.crispycommons.particles.implementations;

import dev.acrispycookie.crispycommons.particles.AbstractCrispyParticle;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleCrispyParticle extends AbstractCrispyParticle {

    protected final Effect effect;
    protected final Location location;
    protected final int data;

    public SimpleCrispyParticle(JavaPlugin plugin, long duration, long period, Effect effect, Location location, int data) {
        super(plugin, duration, period);
        this.effect = effect;
        this.location = location;
        this.data = data;
    }

    @Override
    public void playOnce(Player player) {
        player.spigot().playEffect(location, effect, data, data, 0, 0, 0, 1, 100, 160);
    }
}
