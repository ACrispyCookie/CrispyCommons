package dev.acrispycookie.crispycommons.nms.entity.custom;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

public interface CustomText extends CustomEntity {

    static CustomText newInstance(Location location, Component initialText) {
        return VersionManager.get().createInstance(CustomText.class, new MappedVersions(), new ArgPair<>(Location.class, location), new ArgPair<>(Component.class, initialText));
    }

    void setText(Component text);
}
