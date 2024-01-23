package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.utility.elements.implementations.items.ItemElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.items.SimpleItemElement;
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
        this.element = new ItemElement(items, period, false) {
            @Override
            protected void update() {
                ItemHologramLine.this.update();
            }
        };
    }

    public ItemHologramLine(Supplier<CrispyItem> supplier, int period) {
        super(null);
        this.element = new ItemElement(supplier, period, false) {
            @Override
            protected void update() {
                ItemHologramLine.this.update();
            }
        };
    }

    @Override
    public Location getLocation() {
        int index = hologram.getCurrentContent().indexOf(this);
        Location location = hologram.getLocation().clone();
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
