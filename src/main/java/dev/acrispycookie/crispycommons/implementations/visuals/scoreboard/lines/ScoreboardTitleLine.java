package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
    protected void show(Player player) {
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard(player);
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', getCurrentContent()));
    }

    @Override
    protected void hide(Player player) {
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard(player);
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName("");
    }

    @Override
    protected void update(Player player) {
        show(player);
    }

    @Override
    protected void updatePosition(Player player) {

    }

    @Override
    public void setScoreboard(CrispyScoreboard scoreboard) {
        super.setScoreboard(scoreboard);
        setPlayers(scoreboard.getPlayers());
    }
}
