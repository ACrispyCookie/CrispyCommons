package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations.ItemHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations.TextHologramLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SimpleScoreboardBuilder {

    private boolean isPublic = false;
    private ScoreboardTitleLine title;
    private final SimpleScoreboard scoreboard;
    private final ArrayList<Player> defaultReceivers = new ArrayList<>();
    private final HashMap<ScoreboardLine, Boolean> lines = new HashMap<>();


    public SimpleScoreboardBuilder(String staticTitle) {
        this.scoreboard = new SimpleScoreboard(new ScoreboardTitleLine(staticTitle));
    }

    public SimpleScoreboardBuilder(String staticTitle, Collection<? extends Player> defaultReceivers) {
        this.scoreboard = new SimpleScoreboard(new ScoreboardTitleLine(staticTitle));
        this.defaultReceivers.addAll(defaultReceivers);
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period) {
        this.scoreboard = new SimpleScoreboard(new ScoreboardTitleLine(titleFrames, period));
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period, Collection<? extends Player> defaultReceivers) {
        this.scoreboard = new SimpleScoreboard(new ScoreboardTitleLine(titleFrames, period));
        this.defaultReceivers.addAll(defaultReceivers);
    }

    public SimpleScoreboardBuilder addDefaultReceiver(Player player) {
        defaultReceivers.add(player);
        return this;
    }

    public SimpleScoreboardBuilder setDefaultReceivers(Collection<? extends Player> players) {
        defaultReceivers.clear();
        defaultReceivers.addAll(players);
        return this;
    }

    public SimpleScoreboardBuilder addTextLine(String text) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(text, defaultReceivers);
        scoreboard.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleScoreboardBuilder addTextLine(String text, Collection<? extends Player> receivers) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(text, receivers);
        scoreboard.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleScoreboardBuilder addAnimatedTextLine(Collection<? extends String> frames, int period) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(frames, period, defaultReceivers);
        scoreboard.addLine(line);
        lines.put(line, true);
        return this;
    }

    public SimpleScoreboardBuilder addAnimatedTextLine(Collection<? extends String> frames, int period, Collection<? extends Player> receivers) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(frames, period, receivers);
        scoreboard.addLine(line);
        lines.put(line, false);
        return this;
    }

    public SimpleScoreboardBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleScoreboard build() {
        if(isPublic) {
            return new PublicScoreboard(title);
        }

        lines.forEach((l, b) -> {
            if(b)
                l.setPlayers(defaultReceivers);
        });
        return scoreboard;
    }


}
