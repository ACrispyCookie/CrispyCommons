package dev.acrispycookie.crispycommons.implementations.particle;

import dev.acrispycookie.crispycommons.utility.visual.XParticle;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a color-based effect that can be used to create colored particles.
 * <p>
 * This class extends {@link AbstractEffect} and allows the customization of color components
 * (red, green, blue) for effects such as colored dust particles.
 * </p>
 */
public class ColoredEffect extends AbstractEffect {

    /**
     * The red component of the color used in this effect.
     * <p>
     * This value is normalized between 0 and 1, where 0 represents no red and 1 represents full red intensity.
     * </p>
     */
    private float r;

    /**
     * The green component of the color used in this effect.
     * <p>
     * This value is normalized between 0 and 1, where 0 represents no green and 1 represents full green intensity.
     * </p>
     */
    private float g;

    /**
     * The blue component of the color used in this effect.
     * <p>
     * This value is normalized between 0 and 1, where 0 represents no blue and 1 represents full blue intensity.
     * </p>
     */
    private float b;

    /**
     * Constructs a {@code ColoredEffect} from a {@link Color} object.
     *
     * @param color the color to be used for the effect.
     */
    public ColoredEffect(@NotNull Color color) {
        this.r = (float) numberInRange(color.getRed()) / 255;
        this.g = (float) numberInRange(color.getGreen()) / 255;
        this.b = (float) numberInRange(color.getBlue()) / 255;
    }

    /**
     * Constructs a {@code ColoredEffect} from individual color components.
     *
     * @param r the red component (0-255).
     * @param g the green component (0-255).
     * @param b the blue component (0-255).
     */
    public ColoredEffect(int r, int g, int b) {
        this.r = (float) numberInRange(r) / 255;
        this.g = (float) numberInRange(g) / 255;
        this.b = (float) numberInRange(b) / 255;
    }

    /**
     * Sets the color of the effect using a {@link Color} object.
     *
     * @param color the color to set.
     * @return this {@code ColoredEffect} instance, for method chaining.
     */
    public @NotNull ColoredEffect color(Color color) {
        this.r = (float) numberInRange(color.getRed()) / 255;
        this.g = (float) numberInRange(color.getGreen()) / 255;
        this.b = (float) numberInRange(color.getBlue()) / 255;
        return this;
    }

    /**
     * Sets the color of the effect using individual color components.
     *
     * @param r the red component (0-255).
     * @param g the green component (0-255).
     * @param b the blue component (0-255).
     * @return this {@code ColoredEffect} instance, for method chaining.
     */
    public @NotNull ColoredEffect color(int r, int g, int b) {
        this.r = (float) numberInRange(r) / 255;
        this.g = (float) numberInRange(g) / 255;
        this.b = (float) numberInRange(b) / 255;
        return this;
    }

    /**
     * Sets the red component of the effect.
     *
     * @param red the red component (0-255).
     * @return this {@code ColoredEffect} instance, for method chaining.
     */
    public @NotNull ColoredEffect red(int red) {
        this.r = (float) numberInRange(red) / 255;
        return this;
    }

    /**
     * Sets the green component of the effect.
     *
     * @param green the green component (0-255).
     * @return this {@code ColoredEffect} instance, for method chaining.
     */
    public @NotNull ColoredEffect green(int green) {
        this.g = (float) numberInRange(green) / 255;
        return this;
    }

    /**
     * Sets the blue component of the effect.
     *
     * @param blue the blue component (0-255).
     * @return this {@code ColoredEffect} instance, for method chaining.
     */
    public ColoredEffect blue(int blue) {
        this.b = (float) numberInRange(blue) / 255;
        return this;
    }

    /**
     * Returns the red component of the effect (0-255).
     *
     * @return the red component.
     */
    public float getRed() {
        return r * 255;
    }

    /**
     * Returns the green component of the effect (0-255).
     *
     * @return the green component.
     */
    public float getGreen() {
        return g * 255;
    }

    /**
     * Returns the blue component of the effect (0-255).
     *
     * @return the blue component.
     */
    public float getBlue() {
        return b * 255;
    }

    /**
     * Returns the normalized red component of the effect (0-1).
     *
     * @return the normalized red component.
     */
    public float getNormalisedRed() {
        return r;
    }

    /**
     * Returns the normalized green component of the effect (0-1).
     *
     * @return the normalized green component.
     */
    public float getNormalisedGreen() {
        return g;
    }

    /**
     * Returns the normalized blue component of the effect (0-1).
     *
     * @return the normalized blue component.
     */
    public float getNormalisedBlue() {
        return b;
    }

    /**
     * Returns the effect type associated with this colored effect.
     *
     * @return the {@link XParticle} type, specifically {@code XParticle.DUST}.
     */
    public @NotNull XParticle getEffect() {
        return XParticle.DUST;
    }

    private int numberInRange(int number) {
        return Math.max(Math.min(number, 255), 0);
    }
}

