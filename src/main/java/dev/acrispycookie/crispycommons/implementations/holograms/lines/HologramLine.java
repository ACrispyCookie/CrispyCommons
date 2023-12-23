package dev.acrispycookie.crispycommons.implementations.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import org.bukkit.entity.Player;

public interface HologramLine<K> {

    void updateLocation();
    void display();
    void destroy();
    void updateElement();
    boolean isDisplayed();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Player... players);
    void setHologram(CrispyHologram hologram);
    K getCurrentContent();
}
