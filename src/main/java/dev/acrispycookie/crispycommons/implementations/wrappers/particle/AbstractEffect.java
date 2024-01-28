package dev.acrispycookie.crispycommons.implementations.wrappers.particle;

import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import org.bukkit.Location;

public abstract class AbstractEffect implements Effect {

    private Location location;

    public AbstractEffect(Location location) {
        this.location = location;
    }

    @Override
    public Effect location(Location location) {
        this.location = location;
        return this;
    }
    public Location getLocation() {
        return location;
    }
}
