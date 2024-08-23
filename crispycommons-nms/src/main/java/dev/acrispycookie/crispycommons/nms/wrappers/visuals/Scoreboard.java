package dev.acrispycookie.crispycommons.nms.wrappers.visuals;

import dev.acrispycookie.crispycommons.VersionManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public interface Scoreboard {

    static Scoreboard newInstance() {
        return VersionManager.get().createInstance(Scoreboard.class);
    }

    void show(Player p, Component Title, List<Component> lines);
    void hide(Player p);
    void perPlayerUpdate(Player p, Component title, List<Component> lines);
    void updateLines(Player p, Component title, List<Component> lines);
    void addLine(Player p, int index, Component text);
    void removeLine(Player p, int index);
}
