package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardLineData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.function.Supplier;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(String title) {
        super(new ScoreboardLineData(new SimpleTextElement(title), 0, null));
    }

    public ScoreboardTitleLine(Collection<? extends String> frames, int period) {
        super(new ScoreboardLineData(null, 0, null));
        this.data.setElement(new TextElement(frames, period, false) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        });
    }

    public ScoreboardTitleLine(Supplier<String> supplier, int period) {
        super(new ScoreboardLineData(null, 0, null));
        this.data.setElement(new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        });
    }


    @Override
    protected void initialize() {
        Scoreboard bukkitScoreboard = data.getScoreboard().getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(data.getElement().getRaw())));
    }

    @Override
    protected void updateInternal() {
        initialize();
    }
}
