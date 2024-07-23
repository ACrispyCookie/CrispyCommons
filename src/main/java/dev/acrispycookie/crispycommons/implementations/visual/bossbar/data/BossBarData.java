package dev.acrispycookie.crispycommons.implementations.visual.bossbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BossBarData implements VisualData {

    private GeneralElement<Float, ?> progress;
    private GeneralElement<BossBar.Color, ?> color;
    private GeneralElement<BossBar.Overlay, ?> overlay;
    private TextElement<?> text;

    public BossBarData(GeneralElement<Float, ?> progress, GeneralElement<BossBar.Color, ?> color, GeneralElement<BossBar.Overlay, ?> overlay, TextElement<?> text) {
        this.progress = progress;
        this.color = color;
        this.overlay = overlay;
        this.text = text;
    }

    public GeneralElement<Float, ?> getProgress() {
        return progress;
    }

    public void setProgress(GeneralElement<Float, ?> progress) {
        this.progress = progress;
    }

    public GeneralElement<BossBar.Color, ?> getColor() {
        return color;
    }

    public void setColor(GeneralElement<BossBar.Color, ?> color) {
        this.color = color;
    }

    public GeneralElement<BossBar.Overlay, ?> getOverlay() {
        return overlay;
    }

    public void setOverlay(GeneralElement<BossBar.Overlay, ?> overlay) {
        this.overlay = overlay;
    }

    public TextElement<?> getText() {
        return text;
    }

    public void setText(TextElement<?> text) {
        this.text = text;
    }

    @Override
    public void assertReady(Player player) {
        if (text.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The boss bar text was not set!");
        if (color.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The boss bar color was not set!");
        if (overlay.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The boss bar overlay was not set!");
        Float progress = this.progress.getFromContext(OfflinePlayer.class, player);
        if (progress != null && (progress > 1 || progress < 0))
            throw new VisualNotReadyException("The boss bar progress must be between 0 and 1!");
    }
}
