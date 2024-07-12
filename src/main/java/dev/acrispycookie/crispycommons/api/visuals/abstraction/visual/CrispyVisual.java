package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Set;

public interface CrispyVisual {

    void show();
    void hide();
    void update();
    void destroy();
    void setTimeToLive(GeneralElement<Long, ?> timeToLive);
    void addPlayer(OfflinePlayer player);
    void removePlayer(OfflinePlayer player);
    void setPlayers(Collection<? extends OfflinePlayer> players);
    Set<OfflinePlayer> getPlayers();
    GeneralElement<Long, ?> getTimeToLive();
    boolean isDisplayed();

    class ContextNotExpectedException extends RuntimeException {
        public ContextNotExpectedException(String message) {
            super(message);
        }
    }
}
