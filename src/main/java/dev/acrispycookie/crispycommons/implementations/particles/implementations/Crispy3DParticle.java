package dev.acrispycookie.crispycommons.implementations.particles.implementations;

import dev.acrispycookie.crispycommons.implementations.particles.AbstractCrispyParticle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Crispy3DParticle extends AbstractCrispyParticle {

    protected Crispy3DParticle(JavaPlugin plugin, long duration, long period) {
        super(plugin, duration, period);
    }

    @Override
    public void playOnce(Player player) {
        // Who knows?
    }
}
