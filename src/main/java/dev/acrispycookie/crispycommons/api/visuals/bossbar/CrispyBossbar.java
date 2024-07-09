package dev.acrispycookie.crispycommons.api.visuals.bossbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.SimpleBossbar;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import net.kyori.adventure.bossbar.BossBar;

public interface CrispyBossbar extends CrispyVisual {

    static BossbarBuilder builder() {
        return new BossbarBuilder();
    }
    void setText(TextElement text);
    void setProgress(GeneralElement<Float> progress);
    void setColor(GeneralElement<BossBar.Color> color);
    void setOverlay(GeneralElement<BossBar.Overlay> overlay);
    TextElement getText();
    GeneralElement<Float> getProgress();
    GeneralElement<BossBar.Color> getColor();
    GeneralElement<BossBar.Overlay> getOverlay();

    class BossbarBuilder extends AbstractVisualBuilder<CrispyBossbar> {

        private final BossbarData data = new BossbarData(GlobalGeneralElement.simple((float) -1), null, null, null);

        public BossbarBuilder setText(TextElement text) {
            this.data.setText(text);
            this.data.getText().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossbarBuilder setProgress(GeneralElement<Float> progress) {
            this.data.setProgress(progress);
            this.data.getProgress().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossbarBuilder setColor(GeneralElement<BossBar.Color> color) {
            this.data.setColor(color);
            this.data.getColor().setUpdate(() -> toBuild.update());
            return this;
        }

        public BossbarBuilder setOverlay(GeneralElement<BossBar.Overlay> overlay) {
            this.data.setOverlay(overlay);
            this.data.getOverlay().setUpdate(() -> toBuild.update());
            return this;
        }

        @Override
        public CrispyBossbar build() {
            this.toBuild = new SimpleBossbar(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
