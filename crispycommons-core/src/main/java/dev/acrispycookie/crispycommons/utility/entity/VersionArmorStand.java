package dev.acrispycookie.crispycommons.utility.entity;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface VersionArmorStand extends VersionEntity {

    static VersionArmorStand newInstance(Location location) {
        return VersionManager.createInstance(VersionArmorStand.class, new MappedVersions(), new ArgPair<>(Location.class, location));
    }

    static MappedVersions getRemapped() {
        return new MappedVersions();
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
