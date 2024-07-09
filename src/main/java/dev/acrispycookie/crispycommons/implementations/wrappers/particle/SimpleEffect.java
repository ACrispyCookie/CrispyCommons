package dev.acrispycookie.crispycommons.implementations.wrappers.particle;

import org.bukkit.Effect;

public class SimpleEffect extends AbstractEffect {

    private Effect effect;
    private int data;

    public SimpleEffect(Effect effect, int data) {
        this.effect = effect;
        this.data = data;
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
