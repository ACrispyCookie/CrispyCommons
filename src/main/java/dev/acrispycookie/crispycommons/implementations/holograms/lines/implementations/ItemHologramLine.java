package dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations;

import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.utility.elements.implementations.items.ItemElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.items.SimpleItemElement;
import net.minecraft.server.v1_8_R3.EntityItem;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ItemHologramLine extends ClickableHologramLine<ItemElement, CrispyItem> {

    private EntityItem ei = null;

    public ItemHologramLine(CrispyItem item, List<Player> receivers) {
        super(new SimpleItemElement(item), receivers);
    }

    public ItemHologramLine(ArrayList<CrispyItem> items, int period, List<Player> receivers) {
        super(null, receivers);
        this.element = new ItemElement(items, period) {
            @Override
            protected void update() {
                ItemHologramLine.this.updateElement();
            }
        };
    }

    @Override
    protected void display(Player player) {

    }

    @Override
    protected void destroy(Player player) {

    }

    @Override
    protected void update(Player player) {

    }
}
