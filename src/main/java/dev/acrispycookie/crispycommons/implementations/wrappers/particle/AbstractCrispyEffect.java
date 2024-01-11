package dev.acrispycookie.crispycommons.implementations.wrappers.particle;

import org.bukkit.Location;

public abstract class AbstractCrispyEffect implements CrispyEffect {

    private Location location;

    public AbstractCrispyEffect(Location location) {
        this.location = location;
    }

    @Override
    public CrispyEffect location(Location location) {
        this.location = location;
        return this;
    }
    public Location getLocation() {
        return location;
    }
}
