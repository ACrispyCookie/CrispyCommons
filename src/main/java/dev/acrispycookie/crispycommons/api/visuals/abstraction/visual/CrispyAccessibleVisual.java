package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.VisualBuilder;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public interface CrispyAccessibleVisual<T extends VisualData> extends CrispyVisual<T> {

    void show();
    void hide();
    void update();
    void setTimeToLive(long timeToLive);
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    Set<Player> getPlayers();
    long getTimeToLive();
}
