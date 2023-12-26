package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public abstract class AbstractCrispyScoreboard extends AbstractCrispyAccessibleVisual<List<ScoreboardLine>> implements CrispyScoreboard {

    protected ScoreboardTitleLine title;
    protected final ArrayList<AbstractScoreboardLine> lines = new ArrayList<>();
    protected final Scoreboard bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public AbstractCrispyScoreboard(ScoreboardTitleLine title, Set<? extends Player> receivers) {
        super(receivers);
        this.title = title;
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        bukkitScoreboard.registerNewObjective("[CrispyCommons]", "dummy").setDisplaySlot(DisplaySlot.SIDEBAR);
        System.out.println("Building scoreboard");
        title.show();
        System.out.println("Built title");
        lines.forEach(l -> {
            System.out.println("Showing: " + l.getCurrentContent());
            l.setPosition(l.getNewPosition());
            l.show();
        });
        System.out.println("Built lines");
        System.out.println("Final:");
        getPlayers().forEach((p) -> {
            System.out.println("Title: " + bukkitScoreboard.getObjective("[CrispyCommons]").getDisplayName());
            for (int i = 0; i < bukkitScoreboard.getTeams().size(); i++) {
                Team team = bukkitScoreboard.getTeam(String.valueOf(i));
                System.out.println("Line: " + i);
                System.out.println("  Prefix: " + team.getPrefix());
                System.out.println("  Entry: " + (team.getEntries().iterator().hasNext() ? team.getEntries().iterator().next() : ""));
                System.out.println("  Suffix: " + team.getSuffix());
            }
            System.out.println("Showing to player:");
            p.setScoreboard(bukkitScoreboard);
        });
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        title.hide();
        lines.forEach(AbstractScoreboardLine::hide);
        getPlayers().forEach((p) -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        isDisplayed = false;
    }

    @Override
    public void update() {
        if (isDisplayed) {
            title.update();
            lines.forEach(AbstractScoreboardLine::update);
        }
    }

    @Override
    public void updateLinePosition() {
        if (isDisplayed) {
            lines.forEach(l -> l.setPosition(l.getNewPosition()));
        }
    }

    @Override
    public List<ScoreboardLine> getCurrentContent() {
        return new ArrayList<>(lines);
    }

    @Override
    public void addLine(int index, AbstractScoreboardLine line) {
        lines.add(index, line);
        line.setScoreboard(this);
        if (isDisplayed) {
            line.show();
        }
    }

    @Override
    public void addLine(AbstractScoreboardLine line) {
        addLine(lines.size(), line);
    }

    @Override
    public void removeLine(int index) {
        AbstractScoreboardLine toRemove = lines.get(index);
        lines.remove(index);
        if (isDisplayed) {
            toRemove.hide();
        }
    }

    @Override
    public void setTitle(ScoreboardTitleLine title) {
        this.title = title;
    }

    @Override
    public ScoreboardTitleLine getTitle() {
        return title;
    }

    @Override
    public Scoreboard getBukkitScoreboard() {
        return bukkitScoreboard;
    }

}
