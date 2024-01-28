package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Set;

public interface CrispyVisual {

    void show();
    void hide();
    void update();
    void destroy();
    void setTimeToLive(long timeToLive);
    void addPlayer(OfflinePlayer player);
    void removePlayer(OfflinePlayer player);
    void setPlayers(Collection<? extends OfflinePlayer> players);
    Set<OfflinePlayer> getPlayers();
    long getTimeToLive();
    boolean isDisplayed();
}
