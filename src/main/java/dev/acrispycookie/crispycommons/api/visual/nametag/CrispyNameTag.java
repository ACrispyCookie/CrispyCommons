package dev.acrispycookie.crispycommons.api.visual.nametag;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.data.NameTagData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import org.bukkit.entity.Player;

public interface CrispyNameTag extends CrispyVisual {

    static NameTagBuilder builder() {
        return new NameTagBuilder();
    }
    void setPrefix(NameTagElement<String, ?> prefix);
    void setSuffix(NameTagElement<String, ?> suffix);
    void setBelowName(NameTagElement<String, ?> belowName);
    void setBelowNameValue(NameTagElement<Integer, ?> belowNameValue);
    void setAboveName(NameTagElement<String, ?> aboveName);
    NameTagElement<String, ?> getPrefix();
    NameTagElement<String, ?> getSuffix();
    NameTagElement<String, ?> getBelowName();
    NameTagElement<Integer, ?> getBelowNameValue();
    NameTagElement<String, ?> getAboveName();

    class NameTagBuilder extends AbstractVisualBuilder<CrispyNameTag> {

        private final NameTagData data = new NameTagData(null, null, null, null, null, null);

        public NameTagBuilder setPrefix(NameTagElement<String, ?> prefix) {
            this.data.setPrefix(prefix);
            this.data.getPrefix().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setSuffix(NameTagElement<String, ?> suffix) {
            this.data.setSuffix(suffix);
            this.data.getSuffix().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setAboveName(NameTagElement<String, ?> aboveName) {
            this.data.setAboveName(aboveName);
            this.data.getAboveName().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setBelowName(NameTagElement<String, ?> belowName) {
            this.data.setBelowName(belowName);
            this.data.getBelowName().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setBelowNameValue(NameTagElement<Integer, ?> belowNameValue) {
            this.data.setBelowNameValue(belowNameValue);
            this.data.getBelowNameValue().setUpdate(() -> toBuild.update());
            return this;
        }

        public NameTagBuilder setPlayer(Player player) {
            this.data.setPlayer(player);
            return this;
        }

        @Override
        public CrispyNameTag build() {
            toBuild = new SimpleNameTag(data, receivers, timeToLive, isPublic);

            return toBuild;
        }
    }
}
