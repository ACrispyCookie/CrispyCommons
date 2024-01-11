package dev.acrispycookie.crispycommons.implementations.visuals.holograms;

import dev.acrispycookie.crispycommons.utility.visual.CrispyVisual;
import org.bukkit.Location;

public interface HologramLine<K> extends CrispyVisual<K> {

    Location getLocation();
    void setHologram(CrispyHologram hologram);
    CrispyHologram getHologram();
    K getCurrentContent();
}
