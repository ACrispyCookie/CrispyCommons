package dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items.ItemElement;
import net.minecraft.server.v1_8_R3.EntityItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ItemHologramLine extends ClickableHologramLine<ItemElement> {

    private final EntityItem ei = null;

    public ItemHologramLine(ItemElement element) {
        super(element);
        this.data.getElement().setUpdate(this::update);
    }

    @Override
    public Location getLocation() {
        int index = data.getHologram().getData().getLines().indexOf(this);
        Location location = data.getHologram().getLocation().clone();
        location.subtract(0, index * 0.25, 0);
        return location;
    }

    @Override
    public void show(Player player) {

    }

    @Override
    public void hide(Player player) {

    }

    @Override
    public void update(Player player) {

    }
}
