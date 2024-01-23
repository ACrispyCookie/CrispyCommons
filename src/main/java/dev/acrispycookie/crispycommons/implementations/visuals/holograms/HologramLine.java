package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.utility.visual.CrispyVisual;
import org.bukkit.Location;

public interface HologramLine<T> extends CrispyVisual<T> {

    Location getLocation();
    void setHologram(CrispyHologram hologram);
    CrispyHologram getHologram();
    T getCurrentContent();
}
