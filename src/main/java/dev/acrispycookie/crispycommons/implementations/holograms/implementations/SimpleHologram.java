package dev.acrispycookie.crispycommons.implementations.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleHologram extends AbstractCrispyHologram {

    public SimpleHologram(ArrayList<HologramLine<?>> text, Location location, int timeToLive) {
        super(text, location, timeToLive);
    }

    @Override
    protected Location getLineLocation(int index) {
        return location.clone().add(0, -index * 0.25, 0);
    }
}
