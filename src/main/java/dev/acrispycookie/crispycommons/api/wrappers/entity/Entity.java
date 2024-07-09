package dev.acrispycookie.crispycommons.api.wrappers.entity;

import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.entity.ItemEntity;
import dev.acrispycookie.crispycommons.implementations.wrappers.entity.TextEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Entity {

    static Entity of(DynamicElement<?> element) {
        if (element instanceof ItemElement)
            return new ItemEntity((ItemElement) element);
        else if (element instanceof TextElement)
            return new TextEntity((TextElement) element);
        else
            throw new IllegalArgumentException("The element must be an instance of ItemElement or TextElement!");
    }
    double offsetPerLine();
    Location getLocation();
    DynamicElement<?> getElement();
    void spawn(Location location, Player player);
    void destroy(Player player);
    void update(Location location, Player player);

}
