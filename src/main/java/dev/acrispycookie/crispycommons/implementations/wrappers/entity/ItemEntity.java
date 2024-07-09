package dev.acrispycookie.crispycommons.implementations.wrappers.entity;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import net.minecraft.server.v1_8_R3.EntityItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ItemEntity extends ClickableEntity<ItemElement> {

    private final EntityItem ei = null;

    public ItemEntity(ItemElement element) {
        super(element);
    }

    @Override
    public double offsetPerLine() {
        return 0;
    }

    @Override
    public void spawn(Location location, Player player) {

    }

    @Override
    public void destroy(Player player) {

    }

    @Override
    public void update(Location location, Player player) {

    }

    @Override
    public Location getLocation() {

        return null;
    }
}
