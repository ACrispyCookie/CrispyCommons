package dev.acrispycookie.crispycommons.nms.wrappers.entity;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface EntityArmorStand extends Entity {

    static EntityArmorStand newInstance(Location location) {
        EntityArmorStand armorStand = VersionManager.get().createInstance(EntityArmorStand.class);
        armorStand.init(location);
        return armorStand;
    }

    void init(Location location);
    void setInvisible(boolean invisible);
    void setNoClip(boolean noClip);
    void setBasePlate(boolean basePlate);
    void setGravity(boolean gravity);
    void setCustomNameVisible(boolean visible);
    void setSmall(boolean small);
    void setCustomName(String name);
    void attachEntity(Player player, Entity entity);
}
