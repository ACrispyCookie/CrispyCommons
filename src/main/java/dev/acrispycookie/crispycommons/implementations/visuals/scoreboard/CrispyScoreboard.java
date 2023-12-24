package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.showable.CrispyShowable;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public interface CrispyScoreboard extends CrispyShowable<List<ScoreboardLine>> {

    void setTitle(ScoreboardTitleLine title);
    ScoreboardTitleLine getTitle();
    Scoreboard getBukkitScoreboard(Player player);
    void addLine(ScoreboardLine line);
    void addLine(int index, ScoreboardLine line);
    void removeLine(int index);
}
