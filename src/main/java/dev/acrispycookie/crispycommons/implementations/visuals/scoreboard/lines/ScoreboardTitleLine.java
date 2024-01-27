package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardLineData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardTitleLine extends AbstractScoreboardLine {

    public ScoreboardTitleLine(TextElement element) {
        super(new ScoreboardLineData(element, 0, null));
        this.data.getElement().setUpdate(this::update);
    }


    @Override
    protected void initialize() {
        Scoreboard bukkitScoreboard = data.getScoreboard().getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(data.getElement().getRaw())));
    }

    @Override
    protected void updateInternal() {
        initialize();
    }
}
