package dev.acrispycookie.crispycommons.implementations.visuals.nametag.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.nametag.CrispyNametag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.function.Supplier;

public class NameTagBuilder extends AbstractVisualBuilder<CrispyNametag> {

    private Player player;
    private TextElement prefix;
    private TextElement suffix;
    private TextElement belowName;
    private TextElement aboveName;
    private CrispyNametag nametag;

    public NameTagBuilder(Collection<? extends Player> initialReceivers) {
        super(initialReceivers);
        this.prefix = new SimpleTextElement("");
        this.suffix = new SimpleTextElement("");
        this.belowName = new SimpleTextElement("");
        this.aboveName = new SimpleTextElement("");
    }

    public NameTagBuilder() {
        this.prefix = new SimpleTextElement("");
        this.suffix = new SimpleTextElement("");
        this.belowName = new SimpleTextElement("");
        this.aboveName = new SimpleTextElement("");
    }

    public NameTagBuilder setPrefix(String prefix) {
        this.prefix = new SimpleTextElement(prefix);
        return this;
    }

    public NameTagBuilder setPrefix(Collection<? extends String> frames, int period) {
        this.prefix = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setPrefix(Supplier<? extends String> supplier, int period) {
        this.prefix = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setSuffix(String suffix) {
        this.suffix = new SimpleTextElement(suffix);
        return this;
    }

    public NameTagBuilder setSuffix(Collection<? extends String> frames, int period) {
        this.suffix = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setSuffix(Supplier<? extends String> supplier, int period) {
        this.suffix = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setBelowName(String belowName) {
        this.belowName = new SimpleTextElement(belowName);
        return this;
    }

    public NameTagBuilder setBelowName(Collection<? extends String> frames, int period) {
        this.belowName = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setBelowName(Supplier<? extends String> supplier, int period) {
        this.belowName = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setAboveName(String aboveName) {
        this.aboveName = new SimpleTextElement(aboveName);
        return this;
    }

    public NameTagBuilder setAboveName(Collection<? extends String> frames, int period) {
        this.aboveName = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setAboveName(Supplier<? extends String> supplier, int period) {
        this.aboveName = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                nametag.update();
            }
        };
        return this;
    }

    public NameTagBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public CrispyNametag build() {
        NameTagData data = new NameTagData(player, prefix, suffix, belowName, aboveName);
        nametag = new SimpleNameTag(data, receivers);
        return nametag;
    }
}
