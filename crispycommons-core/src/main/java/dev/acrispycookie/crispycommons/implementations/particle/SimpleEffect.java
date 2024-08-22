package dev.acrispycookie.crispycommons.implementations.particle;

import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Effect;

/**
 * Represents a simple implementation of an {@link Effect}, combining an effect and associated data.
 * <p>
 * This class extends {@link AbstractEffect} and provides a straightforward way to store and manipulate
 * an effect along with an associated data value, which can be used to customize the behavior of the effect.
 * </p>
 */
public class SimpleEffect extends AbstractEffect {

    /**
     * The effect associated with this {@code SimpleEffect}.
     * <p>
     * This field holds the actual {@link XParticle} that is being represented by this class. The effect defines
     * the type of visual or auditory phenomenon that will occur in the game.
     * </p>
     */
    private XParticle effect;

    /**
     * The data value associated with this {@code SimpleEffect}.
     * <p>
     * This field holds an integer value that can be used to customize or modify the behavior of the effect.
     * The exact meaning of this data value depends on the type of effect being used.
     * </p>
     */
    private int data;

    /**
     * Constructs a {@code SimpleEffect} with the specified effect and associated data.
     *
     * @param effect the effect to be wrapped by this class.
     * @param data the data value associated with the effect.
     */
    public SimpleEffect(XParticle effect, int data) {
        this.effect = effect;
        this.data = data;
    }

    /**
     * Sets the effect for this {@code SimpleEffect}.
     *
     * @param effect the effect to set.
     * @return this {@code SimpleEffect} instance, for method chaining.
     */
    public SimpleEffect effect(XParticle effect) {
        this.effect = effect;
        return this;
    }

    /**
     * Sets the data value for this {@code SimpleEffect}.
     *
     * @param data the data value to set.
     * @return this {@code SimpleEffect} instance, for method chaining.
     */
    public SimpleEffect data(int data) {
        this.data = data;
        return this;
    }

    /**
     * Returns the effect associated with this {@code SimpleEffect}.
     *
     * @return the wrapped effect.
     */
    public XParticle getEffect() {
        return effect;
    }

    /**
     * Returns the data value associated with this {@code SimpleEffect}.
     *
     * @return the data value.
     */
    public int getData() {
        return data;
    }
}

