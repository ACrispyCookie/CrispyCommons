package dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

public class BossbarData implements VisualData {

    private GeneralElement<Float> progress;
    private GeneralElement<BossBar.Color> color;
    private GeneralElement<BossBar.Overlay> overlay;
    private TextElement text;

    public BossbarData(GeneralElement<Float> progress, GeneralElement<BossBar.Color> color, GeneralElement<BossBar.Overlay> overlay, TextElement text) {
        this.progress = progress;
        this.color = color;
        this.overlay = overlay;
        this.text = text;
    }

    public GeneralElement<Float> getProgress() {
        return progress;
    }

    public void setProgress(GeneralElement<Float> progress) {
        this.progress = progress;
    }

    public GeneralElement<BossBar.Color> getColor() {
        return color;
    }

    public void setColor(GeneralElement<BossBar.Color> color) {
        this.color = color;
    }

    public GeneralElement<BossBar.Overlay> getOverlay() {
        return overlay;
    }

    public void setOverlay(GeneralElement<BossBar.Overlay> overlay) {
        this.overlay = overlay;
    }

    public TextElement getText() {
        return text;
    }

    public void setText(TextElement text) {
        this.text = text;
    }

    @Override
    public void assertReady(Player player) {
        if (text == null)
            throw new VisualNotReadyException("The bossbar text was not set!");
        if (color == null)
            throw new VisualNotReadyException("The bossbar color was not set!");
        if (overlay == null)
            throw new VisualNotReadyException("The bossbar overlay was not set!");
        if (progressCheck(player))
            throw new VisualNotReadyException("The bossbar progress must be between 0 and 1!");
    }

    private boolean progressCheck(Player player) {
        return progress instanceof GlobalGeneralElement &&
                (((GlobalGeneralElement<Float>) progress).getRaw() < 0 || ((GlobalGeneralElement<Float>) progress).getRaw() > 1) ||
                progress instanceof PersonalGeneralElement &&
                (((PersonalGeneralElement<Float>) progress).getRaw(player) < 0 || ((PersonalGeneralElement<Float>) progress).getRaw(player) > 1);
    }
}
