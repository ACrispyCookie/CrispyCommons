package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms;

import dev.acrispycookie.crispycommons.utility.nms.VersionParticle;
import org.bukkit.Particle;

public class VersionParticle_1_20_R3 implements VersionParticle {

    private Particle particle;

    @Override
    public boolean set(String particle) {
        try {
            this.particle = Particle.valueOf(particle);
            return true;
        } catch (Exception e) {
            this.particle = Particle.ASH;
            return false;
        }
    }

    public Particle getParticle() {
        return particle;
    }
}
