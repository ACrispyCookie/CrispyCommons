package dev.acrispycookie.crispycommons.implementations.visuals.particles;

import org.bukkit.entity.Player;

public interface CrispyParticle {

    void play(Player player);
    void playOnce(Player player);
    void broadcast();
    void broadcastOnce();
    void stop();

}
