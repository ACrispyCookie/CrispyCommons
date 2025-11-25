package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms;

import dev.acrispycookie.crispycommons.utility.nms.VersionParticle;
import org.bukkit.Effect;
import org.jetbrains.annotations.NotNull;

public class VersionParticle_1_8_R3 implements VersionParticle {

    private Effect effect;

    @Override
    public boolean set(@NotNull String particle) {
        try {
            effect = Effect.valueOf(particle);
            return true;
        } catch (Exception e) {
            effect = Effect.COLOURED_DUST;
            return false;
        }
    }

    public @NotNull Effect getEffect() {
        return effect;
    }
}
