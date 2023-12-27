package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(String content) {
        super(new SimpleTextElement(content));
    }

    public ScoreboardTitleLine(Collection<? extends String> frames, int period) {
        super(null);
        this.element = new TextElement(frames, period) {
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
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', getCurrentContent()));
    }

    @Override
    protected void updateInternal() {
        show(0);
    }
}
