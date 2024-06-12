package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalTextElement;
import org.bukkit.entity.Player;

public interface CrispyNametag extends CrispyVisual {

    static NameTagBuilder builder() {
        return new NameTagBuilder();
    }
    void setPrefix(GlobalTextElement prefix);
    void setSuffix(GlobalTextElement suffix);
    void setBelowName(GlobalTextElement belowName);
    void setAboveName(GlobalTextElement aboveName);
    GlobalTextElement getPrefix();
    GlobalTextElement getSuffix();
    GlobalTextElement getBelowName();
    GlobalTextElement getAboveName();

    class NameTagBuilder extends AbstractVisualBuilder<CrispyNametag> {
        private CrispyNametag nametag;
        private final NameTagData data = new NameTagData(null, null, null, null, null, false, false);

        public NameTagBuilder setPrefix(GlobalTextElement prefix) {
            this.data.setPrefix(prefix);
            this.data.getPrefix().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setSuffix(GlobalTextElement suffix) {
            this.data.setSuffix(suffix);
            this.data.getSuffix().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setAbpveName(GlobalTextElement aboveName) {
            this.data.setAboveName(aboveName);
            this.data.getAboveName().setUpdate(() -> nametag.update());
            return this;
        }

        public NameTagBuilder setBelowName(GlobalTextElement belowName) {
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
