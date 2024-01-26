package dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items.ItemElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.items.SimpleItemElement;
import net.minecraft.server.v1_8_R3.EntityItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.function.Supplier;

public class ItemHologramLine extends ClickableHologramLine<ItemElement> {

    private final EntityItem ei = null;

    public ItemHologramLine(CrispyItem item) {
        super(new SimpleItemElement(item));
    }

    public ItemHologramLine(Collection<? extends CrispyItem> items, int period) {
        super(null);
        this.data.setElement(new ItemElement(items, period) {
            @Override
            protected void update() {
                ItemHologramLine.this.update();
            }
        });
    }

    public ItemHologramLine(Supplier<CrispyItem> supplier, int period) {
        super(null);
        this.data.setElement(new ItemElement(supplier, period) {
            @Override
            protected void update() {
                ItemHologramLine.this.update();
            }
        });
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
