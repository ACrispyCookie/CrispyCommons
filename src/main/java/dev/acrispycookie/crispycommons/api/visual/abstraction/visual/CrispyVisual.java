package dev.acrispycookie.crispycommons.api.visual.abstraction.visual;

import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public interface CrispyVisual {

    void show();
    void hide();
    void update();
    void destroy();
    void setTimeToLive(TimeToLiveElement<?> timeToLive);
    void addPlayer(OfflinePlayer player);
    void removePlayer(OfflinePlayer player);
    void setPlayers(Collection<? extends OfflinePlayer> players);
    void resetExpired();
    void resetExpired(Player player);
    Set<OfflinePlayer> getPlayers();
    Set<Player> getCurrentlyViewing();
    TimeToLiveElement<?> getTimeToLive();
    boolean isPublic();
    boolean isDisplayed();
    boolean isDestroyed();
    boolean isAnyoneWatching();
    boolean isWatching(Player player);
    boolean isExpired(OfflinePlayer player);
    boolean isRunning(OfflinePlayer player);

}
