package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.showable.CrispyShowable;
import org.bukkit.Location;

public interface HologramLine<K> extends CrispyShowable<K> {

    void updateLocation();
    Location getLocation();
    void setHologram(CrispyHologram hologram);
    K getCurrentContent();
}
