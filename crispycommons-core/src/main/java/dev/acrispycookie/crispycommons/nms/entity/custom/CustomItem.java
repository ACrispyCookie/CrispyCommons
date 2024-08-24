package dev.acrispycookie.crispycommons.nms.entity.custom;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import org.bukkit.Location;

public interface CustomItem extends CustomEntity {

    static CustomItem newInstance(Location location, CrispyItemStack initialDisplay) {
        return VersionManager.get().createInstance(CustomItem.class, new ArgPair<>(Location.class, location), new ArgPair<>(CrispyItemStack.class, initialDisplay));
    }

    void setDisplay(CrispyItemStack itemStack);
}
