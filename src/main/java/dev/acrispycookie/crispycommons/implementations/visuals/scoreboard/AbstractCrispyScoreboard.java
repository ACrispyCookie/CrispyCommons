package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyShowable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class AbstractCrispyScoreboard extends AbstractCrispyShowable<List<ScoreboardLine>> implements CrispyScoreboard {

    protected ScoreboardTitleLine title;
    protected final ArrayList<ScoreboardLine> lines = new ArrayList<>();
    protected final Map<Player, Scoreboard> bukkitScoreboards = new HashMap<>();

    public AbstractCrispyScoreboard() {
        super(new HashSet<>());
    }

    @Override
    public void show() {
        if (isDisplayed) {
            return;
        }

        isDisplayed = true;
        getPlayers().forEach((p) -> bukkitScoreboards.put(p, Bukkit.getScoreboardManager().getNewScoreboard()));
        title.show();
        lines.forEach(ScoreboardLine::show);
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        isDisplayed = false;
        title.hide();
        lines.forEach(ScoreboardLine::hide);
        getPlayers().forEach((p) -> p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
    }

    @Override
    public void update() {
        if (isDisplayed) {
            title.update();
            lines.forEach(ScoreboardLine::update);
        }
    }

    @Override
    public void addPlayer(Player player) {
        lines.forEach(line -> line.addPlayer(player));
    }

    @Override
    public void removePlayer(Player player) {
        lines.forEach(line -> line.removePlayer(player));
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        lines.forEach(line -> line.setPlayers(players));
    }

    @Override
    public Set<Player> getPlayers() {
        HashSet<Player> players = new HashSet<>();
        lines.forEach(line -> players.addAll(line.getPlayers()));
        return players;
    }

    @Override
    public List<ScoreboardLine> getCurrentContent() {
        return null;
    }

    @Override
    public void addLine(int index, ScoreboardLine line) {
        lines.add(index, line);
        line.setScoreboard(this);
        if (isDisplayed) {
            line.show();
        }
    }

    @Override
    public void addLine(ScoreboardLine line) {
        addLine(lines.size(), line);
    }

    @Override
    public void removeLine(int index) {
        ScoreboardLine toRemove = lines.get(index);
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
    public Scoreboard getBukkitScoreboard(Player player) {
        return bukkitScoreboards.get(player);
    }

}
