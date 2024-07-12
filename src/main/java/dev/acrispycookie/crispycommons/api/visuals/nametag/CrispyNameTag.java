package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import org.bukkit.entity.Player;

public interface CrispyNameTag extends CrispyVisual {

    static NameTagBuilder builder() {
        return new NameTagBuilder();
    }
    void setPrefix(TextElement<?> prefix);
    void setSuffix(TextElement<?> suffix);
    void setBelowName(TextElement<?> belowName);
    void setAboveName(TextElement<?> aboveName);
    TextElement<?> getPrefix();
    TextElement<?> getSuffix();
    TextElement<?> getBelowName();
    TextElement<?> getAboveName();

    class NameTagBuilder extends AbstractVisualBuilder<CrispyNameTag> {
        private final NameTagData data = new NameTagData(null, null, null, null, null);

        public NameTagBuilder setPrefix(TextElement<?> prefix) {
            this.data.setPrefix(prefix);
            this.data.getPrefix().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setSuffix(TextElement<?> suffix) {
            this.data.setSuffix(suffix);
            this.data.getSuffix().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setAboveName(TextElement<?> aboveName) {
            this.data.setAboveName(aboveName);
            this.data.getAboveName().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setBelowName(TextElement<?> belowName) {
            this.data.setBelowName(belowName);
            this.data.getBelowName().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setPlayer(GeneralElement<Player, ?> player) {
            this.data.setPlayer(player);
            this.data.getPlayer().setUpdate(() -> toBuild.update());
            return this;
        }

        @Override
        public CrispyNameTag build() {
            toBuild = new SimpleNameTag(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
