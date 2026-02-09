package dev.acrispycookie.crispycommons.utility.nms.entity;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface VersionTextDisplay extends VersionEntity {

    static @NotNull VersionTextDisplay newInstance(@NotNull Location location) {
        return VersionManager.createInstance(VersionTextDisplay.class, new MappedVersions(), new ArgPair<>(Location.class, location));
    }

    static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }

    void setBackgroundColor(@NotNull Color color);
    void setTextOpacity(byte opacity);
    void setText(@NotNull String text);
    @NotNull String getContent();
    void attachEntity(@NotNull Player player, @NotNull VersionEntity versionEntity);
}
