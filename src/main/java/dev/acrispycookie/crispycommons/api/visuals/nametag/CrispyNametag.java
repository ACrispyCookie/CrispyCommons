package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.entity.Player;

public interface CrispyNametag extends CrispyVisual {

    static NameTagBuilder builder() {
        return new NameTagBuilder();
    }
    void setPrefix(TextElement prefix);
    void setSuffix(TextElement suffix);
    void setBelowName(TextElement belowName);
    void setAboveName(TextElement aboveName);
    TextElement getPrefix();
    TextElement getSuffix();
    TextElement getBelowName();
    TextElement getAboveName();

    class NameTagBuilder extends AbstractVisualBuilder<CrispyNametag> {
        private CrispyNametag nametag;
        private final NameTagData data = new NameTagData(null, TextElement.simple(""), TextElement.simple(""), TextElement.simple(""), TextElement.simple(""), false, false);

        public NameTagBuilder setPrefix(TextElement prefix) {
            this.data.setPrefix(prefix);
            this.data.getPrefix().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setSuffix(TextElement suffix) {
            this.data.setSuffix(suffix);
            this.data.getSuffix().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setAbpveName(TextElement aboveName) {
            this.data.setAboveName(aboveName);
            this.data.getAboveName().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setBelowName(TextElement belowName) {
            this.data.setBelowName(belowName);
            this.data.getBelowName().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setPlayer(Player player) {
            this.data.setPlayer(player);
            return this;
        }

        public NameTagBuilder setNameMode(boolean shouldUseHologram) {
            this.data.setNameMode(shouldUseHologram);
            return this;
        }

        public NameTagBuilder setBelowNameMode(boolean shouldUseHologram) {
            this.data.setBelowNameMode(shouldUseHologram);
            return this;
        }

        @Override
        public CrispyNametag build() {
            nametag = new SimpleNameTag(data, receivers, timeToLive);
            return nametag;
        }
    }
}
