package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import org.bukkit.Location;

public interface HologramLine<T> extends CrispyVisual<T> {

    Location getLocation();
    void setHologram(CrispyHologram hologram);
    CrispyHologram getHologram();
}
