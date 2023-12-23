package dev.acrispycookie.crispycommons.implementations.holograms.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.AbstractCrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.HologramLine;
import org.bukkit.Location;

public class SimpleHologram extends AbstractCrispyHologram {

    public SimpleHologram(Location location, int timeToLive) {
        super(location, timeToLive);
    }
}
