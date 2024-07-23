package dev.acrispycookie.crispycommons.api.entity;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.entity.ItemEntity;
import dev.acrispycookie.crispycommons.implementations.entity.TextEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Entity {

    static Entity of(DynamicElement<?, ?> element) {
        if (element instanceof ItemElement)
            return new ItemEntity((ItemElement<?>) element);
        else if (element instanceof TextElement)
            return new TextEntity((TextElement<?>) element);
        else
            throw new IllegalArgumentException("The element must be an instance of ItemElement or TextElement!");
    }
    double offsetPerLine();
    Location getLocation();
    DynamicElement<?, ?> getElement();
    void spawn(Location location, Player player);
    void destroy(Player player);
    void update(Location location, Player player);

}
