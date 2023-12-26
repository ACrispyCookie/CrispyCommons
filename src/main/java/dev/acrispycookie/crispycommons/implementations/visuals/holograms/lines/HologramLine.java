package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.showable.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.utility.showable.CrispyVisual;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface HologramLine<K> extends CrispyVisual<K> {

    void show(Player player);
    void hide(Player player);
    void update(Player player);
    Location getLocation();
    void setHologram(CrispyHologram hologram);
    CrispyHologram getHologram();
    K getCurrentContent();
}
