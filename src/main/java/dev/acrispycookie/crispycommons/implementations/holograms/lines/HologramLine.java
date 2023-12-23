package dev.acrispycookie.crispycommons.implementations.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public interface HologramLine<K> {

    void display();
    void destroy();
    void updateContent();
    void updateLocation();
    boolean isDisplayed();
    Location getLocation();
    List<Player> getPlayers();
    void addPlayer(Player player);
    void removePlayer(Player player);
    void setPlayers(Collection<? extends Player> players);
    void setHologram(CrispyHologram hologram);
    K getCurrentContent();
}
