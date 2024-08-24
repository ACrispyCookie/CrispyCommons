package dev.acrispycookie.crispycommons.nms.entity.spigot;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface VersionArmorStand extends VersionEntity {

    static VersionArmorStand newInstance(Location location) {
        return VersionManager.get().createInstance(VersionArmorStand.class, new ArgPair<>(Location.class, location));
    }

    void setInvisible(boolean invisible);
    void setNoClip(boolean noClip);
    void setBasePlate(boolean basePlate);
    void setCustomNameVisible(boolean visible);
    void setSmall(boolean small);
    void setCustomName(String name);
    String getCustomName();
    void attachEntity(Player player, VersionEntity versionEntity);
}
