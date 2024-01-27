package dev.acrispycookie.crispycommons.implementations.visuals.nametag.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.nametag.CrispyNametag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.entity.Player;

import java.util.Collection;

public class NameTagBuilder extends AbstractVisualBuilder<CrispyNametag> {

    private Player player;
    private TextElement prefix;
    private TextElement suffix;
    private TextElement belowName;
    private TextElement aboveName;
    private boolean hologramName = false;
    private boolean hologramBelowName = false;
    private CrispyNametag nametag;

    public NameTagBuilder(Collection<? extends Player> initialReceivers) {
        super(initialReceivers);
        this.prefix = TextElement.simple("");
        this.suffix = TextElement.simple("");
        this.belowName = TextElement.simple("");
        this.aboveName = TextElement.simple("");
    }

    public NameTagBuilder() {
        this.prefix = TextElement.simple("");
        this.suffix = TextElement.simple("");
        this.belowName = TextElement.simple("");
        this.aboveName = TextElement.simple("");
    }

    public NameTagBuilder setPrefix(TextElement prefix) {
        this.prefix = prefix;
        this.prefix.setUpdate(() -> nametag.update());
        return this;
    }

    public NameTagBuilder setSuffix(TextElement suffix) {
        this.suffix = suffix;
        this.suffix.setUpdate(() -> nametag.update());
        return this;
    }

    public NameTagBuilder setAbpveName(TextElement aboveName) {
        this.aboveName = aboveName;
        this.aboveName.setUpdate(() -> nametag.update());
        return this;
    }

    public NameTagBuilder setBelowName(TextElement belowName) {
        this.belowName = belowName;
        this.belowName.setUpdate(() -> nametag.update());
        return this;
    }

    public NameTagBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public NameTagBuilder setNameMode(boolean shouldUseHologram) {
        this.hologramName = shouldUseHologram;
        return this;
    }

    public NameTagBuilder setBelowNameMode(boolean shouldUseHologram) {
        this.hologramBelowName = shouldUseHologram;
        return this;
    }

    @Override
    public CrispyNametag build() {
        NameTagData data = new NameTagData(player, prefix, suffix, belowName, aboveName, hologramName, hologramBelowName);
        nametag = new SimpleNameTag(data, receivers);
        return nametag;
    }
}
