package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import net.kyori.adventure.bossbar.BossBar;

public interface CrispyBossbar extends CrispyAccessibleVisual<TextElement> {

    void setText(TextElement text);
    void setProgress(float progress);
    void setColor(BossBar.Color color);
    void setOverlay(BossBar.Overlay overlay);
}
