package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.showable.CrispyShowable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface HologramLine<K> extends CrispyShowable<K> {

    Location getLocation(Player player);
    void setHologram(CrispyHologram hologram);
    K getCurrentContent();
}
