package dev.acrispycookie.crispycommons.implementations.wrappers.particle;

import dev.acrispycookie.crispycommons.api.wrappers.particle.AbstractCrispyEffect;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;

public class ColoredEffect extends AbstractCrispyEffect {

    private float r = 0;
    private float g = 0;
    private float b = 0;

    public ColoredEffect(Location location) {
        super(location);
    }

    public ColoredEffect(Location location, Color color) {
        super(location);
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    public ColoredEffect(Location location, int r, int b, int g) {
        super(location);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public ColoredEffect color(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        return this;
    }

    public ColoredEffect color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        return this;
    }

    public ColoredEffect red(float red) {
        this.r = red;
        return this;
    }

    public ColoredEffect green(float green) {
        this.g = green;
        return this;
    }

    public ColoredEffect blue(float blue) {
        this.b = blue;
        return this;
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public Effect getEffect() {
        return Effect.COLOURED_DUST;
    }
}
