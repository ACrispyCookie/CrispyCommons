package dev.acrispycookie.crispycommons.api.visuals.hologram;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramLineData;
import org.bukkit.Location;

public interface HologramLine<T extends AnimatedElement<?>> extends CrispyVisual<HologramLineData<T>> {

    Location getLocation();
    void setHologram(CrispyHologram hologram);
    CrispyHologram getHologram();
}
