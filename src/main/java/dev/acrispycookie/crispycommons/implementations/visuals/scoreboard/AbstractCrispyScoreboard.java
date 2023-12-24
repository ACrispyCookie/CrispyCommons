package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public abstract class AbstractCrispyScoreboard implements CrispyScoreboard {

    protected final Player player;
    protected ScoreboardTitleLine title;
    protected final ArrayList<ScoreboardLine> lines = new ArrayList<>();
    protected int updateInterval;
    private int updateTaskID;
    protected final JavaPlugin plugin;
    protected abstract void showInternal();
    protected abstract void hideInternal();

    public AbstractCrispyScoreboard(Player player, ScoreboardTitleLine title, ArrayList<ScoreboardLine> lines, int updateInterval) {
        this.plugin = CrispyCommons.getPlugin();
        this.player = player;
        this.title = title;
        this.lines.addAll(lines);
        this.updateInterval = updateInterval;
    }

    @Override
    public void update() {
        hideInternal();
        showInternal();
    }

    @Override
    public void show() {
        updateTaskID = Bukkit.getScheduler().runTaskTimer(plugin, this::update, 0, updateInterval).getTaskId();
        showInternal();
    }

    @Override
    public void hide() {
        Bukkit.getScheduler().cancelTask(updateTaskID);
        hideInternal();
    }

    @Override
    public void setTitle(ScoreboardTitleLine title) {
        this.title = title;
    }

    @Override
    public void addLine(ScoreboardLine line) {
        addLine(0, line);
    }

    @Override
    public void addLine(int index, ScoreboardLine line) {
        lines.add(index, line);
    }

    @Override
    public void removeLine(int index) {
        lines.remove(index);
    }
}
