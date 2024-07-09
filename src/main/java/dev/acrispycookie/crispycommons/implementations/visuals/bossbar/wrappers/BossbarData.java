package dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import net.kyori.adventure.bossbar.BossBar;

public class BossbarData implements VisualData {

    private float progress;
    private BossBar.Color color;
    private BossBar.Overlay overlay;
    private TextElement text;

    public BossbarData(float progress, BossBar.Color color, BossBar.Overlay overlay, TextElement text) {
        this.progress = progress;
        this.color = color;
        this.overlay = overlay;
        this.text = text;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public BossBar.Color getColor() {
        return color;
    }

    public void setColor(BossBar.Color color) {
        this.color = color;
    }

    public BossBar.Overlay getOverlay() {
        return overlay;
    }

    public void setOverlay(BossBar.Overlay overlay) {
        this.overlay = overlay;
    }

    public TextElement getText() {
        return text;
    }

    public void setText(TextElement text) {
        this.text = text;
    }

    @Override
    public void assertReady() {
        if (text == null)
            throw new VisualNotReadyException("The bossbar text was not set!");
        if (color == null)
            throw new VisualNotReadyException("The bossbar color was not set!");
        if (overlay == null)
            throw new VisualNotReadyException("The bossbar overlay was not set!");
        if (progress < 0 || progress > 1)
            throw new VisualNotReadyException("The bossbar progress must be between 0 and 1!");
    }
}
