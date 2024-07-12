package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.SimpleBossBar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossBarData;
import net.kyori.adventure.bossbar.BossBar;

public interface CrispyBossBar extends CrispyVisual {

    static BossBarBuilder builder() {
        return new BossBarBuilder();
    }
    void setText(TextElement<?> text);
    void setProgress(GeneralElement<Float, ?> progress);
    void setColor(GeneralElement<BossBar.Color, ?> color);
    void setOverlay(GeneralElement<BossBar.Overlay, ?> overlay);
    TextElement<?> getText();
    GeneralElement<Float, ?> getProgress();
    GeneralElement<BossBar.Color, ?> getColor();
    GeneralElement<BossBar.Overlay, ?> getOverlay();

    class BossBarBuilder extends AbstractVisualBuilder<CrispyBossBar> {

        private final BossBarData data = new BossBarData(GeneralElement.simple((float) -1), null, null, null);

        public BossBarBuilder setText(TextElement<?> text) {
            this.data.setText(text);
            this.data.getText().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossBarBuilder setProgress(GeneralElement<Float, ?> progress) {
            this.data.setProgress(progress);
            this.data.getProgress().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossBarBuilder setColor(GeneralElement<BossBar.Color, ?> color) {
            this.data.setColor(color);
            this.data.getColor().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossBarBuilder setOverlay(GeneralElement<BossBar.Overlay, ?> overlay) {
            this.data.setOverlay(overlay);
            this.data.getOverlay().setUpdate(() -> toBuild.update());
            return this;
        }

        @Override
        public CrispyBossBar build() {
            this.toBuild = new SimpleBossBar(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
