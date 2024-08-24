package dev.acrispycookie.crispycommons.nms.visuals;

import dev.acrispycookie.crispycommons.VersionManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public interface VersionScoreboard {

    VersionScoreboard instance = VersionManager.get().createInstance(VersionScoreboard.class);

    static VersionScoreboard newInstance() {
        return instance;
    }

    void show(Player p, Component Title, List<Component> lines);
    void hide(Player p, int lineCount);
    void updateContent(Player p, Component title, List<Component> lines);
    void resetTitle(Player p, Component title);
    void resetLines(Player p, int lineCount, List<Component> lines);
}
