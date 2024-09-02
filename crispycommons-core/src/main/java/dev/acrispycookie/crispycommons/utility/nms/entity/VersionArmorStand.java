package dev.acrispycookie.crispycommons.utility.nms.entity;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface VersionArmorStand extends VersionEntity {

    static @NotNull VersionArmorStand newInstance(@NotNull Location location) {
        return VersionManager.createInstance(VersionArmorStand.class, new MappedVersions(), new ArgPair<>(Location.class, location));
    }

    static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }

    void setInvisible(boolean invisible);
    void setNoClip(boolean noClip);
    void setBasePlate(boolean basePlate);
    void setCustomNameVisible(boolean visible);
    void setSmall(boolean small);
    void setCustomName(@NotNull String name);
    @NotNull String getCustomName();
    void attachEntity(@NotNull Player player, @NotNull VersionEntity versionEntity);
}
