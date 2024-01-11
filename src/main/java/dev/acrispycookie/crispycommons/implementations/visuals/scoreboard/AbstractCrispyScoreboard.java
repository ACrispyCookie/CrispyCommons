package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public abstract class AbstractCrispyScoreboard extends AbstractCrispyAccessibleVisual<List<ScoreboardLine>> implements CrispyScoreboard {

    protected ScoreboardTitleLine title;
    protected final ArrayList<AbstractScoreboardLine> lines = new ArrayList<>();
    protected Scoreboard bukkitScoreboard;

    public AbstractCrispyScoreboard(ScoreboardTitleLine title, Set<? extends Player> receivers) {
        super(receivers);
        this.bukkitScoreboard = getNewBoard();
        this.title = title;
        this.title.setScoreboard(this);
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        title.show(0);
        for (int i = 0; i < lines.size(); i++) {
            AbstractScoreboardLine line = lines.get(i);
            line.show(i);
        }
        getPlayers().forEach(p -> p.setScoreboard(bukkitScoreboard));
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        lines.forEach(AbstractScoreboardLine::hide);
        getPlayers().forEach((p) -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        isDisplayed = false;
    }

    @Override
    public void update() {
        if (isDisplayed) {
            title.update();
            lines.forEach(AbstractScoreboardLine::update);
        }
    }

    protected void updateScoreboard() {
        if (isDisplayed) {
            title.hide();
            ArrayList<Integer> toShow = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                AbstractScoreboardLine l = lines.get(i);
                if(l.isDisplayed())
                    toShow.add(i);
                l.hide();
            }
            receivers.forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));

            bukkitScoreboard = getNewBoard();
            title.show(0);
            int index = 0;
            for (int i : toShow) {
                AbstractScoreboardLine line = lines.get(i);
                line.show(index);
                index++;
            }
            receivers.forEach((p) -> p.setScoreboard(bukkitScoreboard));
        }
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        player.setScoreboard(bukkitScoreboard);
    }

    @Override
    public void removePlayer(Player player) {
        super.removePlayer(player);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        players.forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        super.setPlayers(players);
        players.forEach(p -> p.setScoreboard(bukkitScoreboard));
    }

    @Override
    public List<ScoreboardLine> getCurrentContent() {
        return new ArrayList<>(lines);
    }

    @Override
    public void addLine(int index, AbstractScoreboardLine line) {
        if (index > lines.size()) {
            return;
        }

        lines.add(index, line);
        line.setScoreboard(this);
        line.setDisplayed(true);
        if (isDisplayed) {
            updateScoreboard();
        }
    }

    @Override
    public void addLine(AbstractScoreboardLine line) {
        addLine(lines.size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= lines.size())
            return;

        AbstractScoreboardLine toRemove = lines.get(index);
        lines.remove(index);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
            updateScoreboard();
        }
    }

    @Override
    public void showLine(int index) {
        if (index >= lines.size()) {
            return;
        }

        AbstractScoreboardLine line = lines.get(index);
        if (isDisplayed && !line.isDisplayed()) {
            line.setDisplayed(true);
            updateScoreboard();
        }
    }

    @Override
    public void hideLine(int index) {
        if (index >= lines.size()) {
            return;
        }

        AbstractScoreboardLine line = lines.get(index);
        if (isDisplayed && line.isDisplayed()) {
            line.hide();
            updateScoreboard();
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

    private Scoreboard getNewBoard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }

}
