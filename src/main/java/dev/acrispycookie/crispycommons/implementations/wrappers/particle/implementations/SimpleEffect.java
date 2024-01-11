package dev.acrispycookie.crispycommons.implementations.wrappers.particle.implementations;

import dev.acrispycookie.crispycommons.implementations.wrappers.particle.AbstractCrispyEffect;
import org.bukkit.Effect;
import org.bukkit.Location;

public class SimpleEffect extends AbstractCrispyEffect {

    private Effect effect;
    private int data;

    public SimpleEffect(Location location, Effect effect, int data) {
        super(location);
        this.effect = effect;
        this.data = data;
    }

    public SimpleEffect(Location location) {
        super(location);
    }

    public SimpleEffect effect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public SimpleEffect data(int data) {
        this.data = data;
        return this;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getData() {
        return data;
    }
}
