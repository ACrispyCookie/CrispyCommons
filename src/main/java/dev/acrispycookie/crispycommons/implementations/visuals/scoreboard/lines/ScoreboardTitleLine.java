package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleStringElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.function.Supplier;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(String content) {
        super(new SimpleStringElement(content));
    }

    public ScoreboardTitleLine(Collection<? extends String> frames, int period) {
        super(null);
        this.content = new StringElement(frames, period, false) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        };
    }

    public ScoreboardTitleLine(Supplier<String> supplier, int period) {
        super(null);
        this.content = new StringElement(supplier, period, false) {
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
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', getContent().getRaw().toPlainText()));
    }

    @Override
    protected void updateInternal() {
        initialize();
    }
}
