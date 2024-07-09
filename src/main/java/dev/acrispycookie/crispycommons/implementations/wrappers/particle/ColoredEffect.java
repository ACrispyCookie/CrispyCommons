package dev.acrispycookie.crispycommons.implementations.wrappers.particle;

import org.bukkit.Color;
import org.bukkit.Effect;

public class ColoredEffect extends AbstractEffect {

    private float r = 0;
    private float g = 0;
    private float b = 0;

    public ColoredEffect(Color color) {
        this.r = (float) color.getRed() / 255;
        this.g = (float) color.getGreen() / 255;
        this.b = (float) color.getBlue() / 255;
    }

    public ColoredEffect(int r, int b, int g) {
        this.r = (float) r / 255;
        this.g = (float) g / 255;
        this.b = (float) b / 255;
    }

    public ColoredEffect color(Color color) {
        this.r = (float) color.getRed() / 255;
        this.g = (float) color.getGreen() / 255;
        this.b = (float) color.getBlue() / 255;
        return this;
    }

    public ColoredEffect color(float r, float g, float b) {
        this.r = r / 255;
        this.g = g / 255;
        this.b = b / 255;
        return this;
    }

    public ColoredEffect red(float red) {
        this.r = red / 255;
        return this;
    }

    public ColoredEffect green(float green) {
        this.g = green / 255;
        return this;
    }

    public ColoredEffect blue(float blue) {
        this.b = blue / 255;
        return this;
    }

    public float getRed() {
        return r * 255;
    }

    public float getGreen() {
        return g * 255;
    }

    public float getBlue() {
        return b * 255;
    }

    public float getNormalisedRed() {
        return r;
    }

    public float getNormalisedGreen() {
        return g;
    }

    public float getNormalisedBlue() {
        return b;
    }

    public Effect getEffect() {
        return Effect.COLOURED_DUST;
    }
}
