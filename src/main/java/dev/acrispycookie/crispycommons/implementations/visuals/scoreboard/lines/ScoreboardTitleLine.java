package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractScoreboardLine;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.function.Supplier;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(String content) {
        super(new SimpleTextElement(content));
    }

    public ScoreboardTitleLine(Collection<? extends String> frames, int period) {
        super(null);
        this.content = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        };
    }

    public ScoreboardTitleLine(Supplier<String> supplier, int period) {
        super(null);
        this.content = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        };
    }


    @Override
    protected void initialize() {
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(getContent().getRaw())));
    }

    @Override
    protected void updateInternal() {
        initialize();
    }
}
