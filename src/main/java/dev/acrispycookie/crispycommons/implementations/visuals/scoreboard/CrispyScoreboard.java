package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;

public interface CrispyScoreboard {

    void setTitle(String title);
    void addLine(int index, ScoreboardLine line);
    void addLine(ScoreboardLine line);
    void removeLine(int index);
    void removeLine(ScoreboardLine line);
    void setLine(int index, ScoreboardLine line);
    void setUpdateInterval(int updateInterval);
    void show();
    void hide();
    void update();
}
