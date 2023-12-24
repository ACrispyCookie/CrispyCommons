package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.HashSet;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(String content) {
        super(new SimpleTextElement(content), new HashSet<>());
    }

    public ScoreboardTitleLine(Collection<? extends String> frames, int period) {
        super(null, new HashSet<>());
        this.element = new TextElement(frames, period) {
            @Override
            protected void update() {
                ScoreboardTitleLine.this.update();
            }
        };
    }


    @Override
    protected void show(Scoreboard bukkitScoreboard) {
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', getCurrentContent()));
    }

    @Override
    protected void hide(Scoreboard bukkitScoreboard) {
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName("");
    }

    @Override
    protected void update(Scoreboard bukkitScoreboard) {
        show(bukkitScoreboard);
    }

    @Override
    public void setScoreboard(CrispyScoreboard scoreboard) {
        super.setScoreboard(scoreboard);
        setPlayers(scoreboard.getPlayers());
    }
}
