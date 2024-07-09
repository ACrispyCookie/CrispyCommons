package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.SimpleBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import net.kyori.adventure.bossbar.BossBar;

public interface CrispyBossbar extends CrispyVisual {

    static BossbarBuilder builder() {
        return new BossbarBuilder();
    }
    void setText(TextElement text);
    void setProgress(float progress);
    void setColor(BossBar.Color color);
    void setOverlay(BossBar.Overlay overlay);
    TextElement getText();
    float getProgress();
    BossBar.Color getColor();
    BossBar.Overlay getOverlay();

    class BossbarBuilder extends AbstractVisualBuilder<CrispyBossbar> {

        private CrispyBossbar bossbar;
        private final BossbarData data = new BossbarData( -1, null, null, null);

        public BossbarBuilder setText(TextElement text) {
            this.data.setText(text);
            this.data.getText().setUpdate(() -> bossbar.update());
            return this;
        }

        public BossbarBuilder setProgress(float progress) {
            this.data.setProgress(progress);
            return this;
        }

        public BossbarBuilder setColor(BossBar.Color color) {
            this.data.setColor(color);
            return this;
        }

        public BossbarBuilder setOverlay(BossBar.Overlay overlay) {
            this.data.setOverlay(overlay);
            return this;
        }

        @Override
        public CrispyBossbar build() {
            this.bossbar = new SimpleBossbar(data, receivers, timeToLive);
            return bossbar;
        }
    }
}
