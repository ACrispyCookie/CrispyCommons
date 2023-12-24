package dev.acrispycookie.crispycommons.implementations.visuals.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.HologramLine;
import org.bukkit.Location;

public class SimpleHologram extends AbstractCrispyHologram {

    public SimpleHologram(Location location, int timeToLive) {
        super(location, timeToLive);
    }
}
