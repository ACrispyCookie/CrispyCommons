package dev.acrispycookie.crispycommons.implementations.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.CrispyHologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleCrispyHologram extends AbstractCrispyHologram {

    public SimpleCrispyHologram(JavaPlugin plugin, Collection<? extends Player> receiverList, ArrayList<CrispyHologramLine> text, Location location, int timeToLive) {
        super(plugin, receiverList, text, location, timeToLive);
    }

    public SimpleCrispyHologram(JavaPlugin plugin, Player receiver, ArrayList<CrispyHologramLine> text, Location location, int timeToLive) {
        super(plugin, receiver, text, location, timeToLive);
    }

     @Override
    protected void displayToPlayer(Player player) {
        for (int i = 0; i < text.size(); i++) {
            CrispyHologramLine line = text.get(i);
            Location lineLocation = location.clone().subtract(0, i * 0.25, 0);
            line.setLocation(lineLocation);
            line.setReceivers(receiverList);
            line.display();
        }
    }

    @Override
    protected void hideFromPlayer(Player player) {
        text.forEach(CrispyHologramLine::destroy);
    }

    @Override
    public void update() {
        text.forEach(CrispyHologramLine::update);
    }

    @Override
    public void handleTextChange() {
        update();
    }
}
