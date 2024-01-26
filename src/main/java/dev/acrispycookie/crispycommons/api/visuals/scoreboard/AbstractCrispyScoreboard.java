package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public abstract class AbstractCrispyScoreboard extends AbstractCrispyAccessibleVisual<List<AbstractScoreboardLine>> implements CrispyScoreboard {

    protected ScoreboardTitleLine title;
    protected Scoreboard bukkitScoreboard;

    public AbstractCrispyScoreboard(ScoreboardTitleLine title, List<AbstractScoreboardLine> content, Set<? extends Player> receivers) {
        super(content, receivers);
        this.bukkitScoreboard = getNewBoard();
        this.title = title;
        this.title.setScoreboard(this);
        this.content.forEach(l -> l.setScoreboard(this));
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        title.show(0);
        for (int i = 0; i < content.size(); i++) {
            AbstractScoreboardLine line = content.get(i);
            line.show(i);
        }
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(bukkitScoreboard));
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        content.forEach(AbstractScoreboardLine::hide);
        receivers.stream().filter(OfflinePlayer::isOnline).forEach((p) -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        isDisplayed = false;
    }

    @Override
    public void update() {
        if (isDisplayed) {
            title.update();
            content.forEach(AbstractScoreboardLine::update);
        }
    }

    protected void updateScoreboard() {
        if (isDisplayed) {
            title.hide();
            ArrayList<Integer> toShow = new ArrayList<>();
            for (int i = 0; i < content.size(); i++) {
                AbstractScoreboardLine l = content.get(i);
                if(l.isDisplayed())
                    toShow.add(i);
                l.hide();
            }
            receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));

            bukkitScoreboard = getNewBoard();
            title.show(0);
            int index = 0;
            for (int i : toShow) {
                AbstractScoreboardLine line = content.get(i);
                line.show(index);
                index++;
            }
            receivers.stream().filter(OfflinePlayer::isOnline).forEach((p) -> p.setScoreboard(bukkitScoreboard));
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
        players.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
        super.setPlayers(players);
        players.stream().filter(OfflinePlayer::isOnline).forEach(p -> p.setScoreboard(bukkitScoreboard));
    }

    @Override
    public void addLine(int index, AbstractScoreboardLine line) {
        if (index > content.size()) {
            return;
        }

        content.add(index, line);
        line.setScoreboard(this);
        line.setDisplayed(true);
        if (isDisplayed) {
            updateScoreboard();
        }
    }

    @Override
    public void addLine(AbstractScoreboardLine line) {
        addLine(content.size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= content.size())
            return;

        AbstractScoreboardLine toRemove = content.get(index);
        content.remove(index);
        if (isDisplayed && toRemove.isDisplayed()) {
            toRemove.hide();
            updateScoreboard();
        }
    }

    @Override
    public void showLine(int index) {
        if (index >= content.size()) {
            return;
        }

        AbstractScoreboardLine line = content.get(index);
        if (isDisplayed && !line.isDisplayed()) {
            line.setDisplayed(true);
            updateScoreboard();
        }
    }

    @Override
    public void hideLine(int index) {
        if (index >= content.size()) {
            return;
        }

        AbstractScoreboardLine line = content.get(index);
        if (isDisplayed && line.isDisplayed()) {
            line.hide();
            updateScoreboard();
        }
    }

    @Override
    public void setTitle(ScoreboardTitleLine title) {
        this.title = title;
        if (isDisplayed) {
            title.update();
        }
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
