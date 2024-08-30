package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms;

import dev.acrispycookie.crispycommons.utility.nms.VersionParticle;
import org.bukkit.Effect;

public class VersionParticle_1_8_R3 implements VersionParticle {

    private Effect effect;

    @Override
    public boolean set(String particle) {
        try {
            effect = Effect.valueOf(particle);
            return true;
        } catch (Exception e) {
            effect = Effect.COLOURED_DUST;
            return false;
        }
    }

    public Effect getEffect() {
        return effect;
    }
}
