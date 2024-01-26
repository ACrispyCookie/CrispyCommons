package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public abstract class AbstractScoreboard extends AbstractAccessibleVisual<ScoreboardData> implements CrispyScoreboard {

    public AbstractScoreboard(ScoreboardData data, Set<? extends Player> receivers) {
        super(data, receivers);
        this.data.setBukkitScoreboard(getNewBoard());
        this.data.getTitle().setScoreboard(this);
        this.data.getLines().forEach(l -> l.setScoreboard(this));
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        this.data.getTitle().show(0);
        for (int i = 0; i < data.getLines().size(); i++) {
            AbstractScoreboardLine line = data.getLines().get(i);
            line.show(i);
        }
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(this.data.getBukkitScoreboard()));
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        data.getLines().forEach(AbstractScoreboardLine::hide);
        receivers.stream().filter(OfflinePlayer::isOnline).forEach((p) -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        data.setBukkitScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        isDisplayed = false;
    }

    @Override
    public void update() {
        if (isDisplayed) {
            data.getTitle().update();
            data.getLines().forEach(AbstractScoreboardLine::update);
        }
    }

    protected void updateScoreboard() {
        if (isDisplayed) {
            data.getTitle().hide();
            ArrayList<Integer> toShow = new ArrayList<>();
            for (int i = 0; i < data.getLines().size(); i++) {
                AbstractScoreboardLine l = data.getLines().get(i);
                if(l.isDisplayed())
                    toShow.add(i);
                l.hide();
            }
            receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));

            data.setBukkitScoreboard(getNewBoard());
            data.getTitle().show(0);
            int index = 0;
            for (int i : toShow) {
                AbstractScoreboardLine line = data.getLines().get(i);
                line.show(index);
                index++;
            }
            receivers.stream().filter(OfflinePlayer::isOnline).forEach((p) -> p.setScoreboard(data.getBukkitScoreboard()));
        }
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        player.setScoreboard(data.getBukkitScoreboard());
    }

    @Override
    public void removePlayer(Player player) {
        super.removePlayer(player);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        players.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        super.setPlayers(players);
        players.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(data.getBukkitScoreboard()));
    }

    @Override
    public void addLine(int index, SimpleScoreboardLine line) {
        if (index > data.getLines().size()) {
            return;
        }

        List<SimpleScoreboardLine> newLines = data.getLines();
        newLines.add(index, line);
        data.setLines(newLines);
        line.setScoreboard(this);
        line.setDisplayed(true);
        if (isDisplayed) {
            updateScoreboard();
        }
    }

    @Override
    public void addLine(SimpleScoreboardLine line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        SimpleScoreboardLine toRemove = data.getLines().get(index);
        List<SimpleScoreboardLine> newLines = data.getLines();
        newLines.remove(index);
        data.setLines(newLines);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
            updateScoreboard();
        }
    }

    @Override
    public void showLine(int index) {
        if (index >= data.getLines().size()) {
            return;
        }

        AbstractScoreboardLine line = data.getLines().get(index);
        if (isDisplayed && !line.isDisplayed()) {
            line.setDisplayed(true);
            updateScoreboard();
        }
    }

    @Override
    public void hideLine(int index) {
        if (index >= data.getLines().size()) {
            return;
        }

        AbstractScoreboardLine line = data.getLines().get(index);
        if (isDisplayed && line.isDisplayed()) {
            line.hide();
            updateScoreboard();
        }
    }

    @Override
    public void setTitle(ScoreboardTitleLine title) {
        data.setTitle(title);
        if (isDisplayed) {
            data.getTitle().update();
        }
    }

    @Override
    public ScoreboardTitleLine getTitle() {
        return data.getTitle();
    }

    @Override
    public Scoreboard getBukkitScoreboard() {
        return data.getBukkitScoreboard();
    }

    private Scoreboard getNewBoard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }

}
