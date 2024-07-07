package dev.acrispycookie.crispycommons.api.wrappers.entity;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalAnimatedElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalItemElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.entity.ItemEntity;
import dev.acrispycookie.crispycommons.implementations.wrappers.entity.TextEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Entity {

    static Entity of(GlobalAnimatedElement<?> element) {
        if (element instanceof GlobalItemElement)
            return new ItemEntity((GlobalItemElement) element);
        else if (element instanceof GlobalTextElement)
            return new TextEntity((GlobalTextElement) element);
        else
            throw new IllegalArgumentException("The element must be an instance of ItemElement or TextElement!");
    }
    double offsetPerLine();
    Location getLocation();
    GlobalAnimatedElement<?> getElement();
    void spawn(Location location, Player player);
    void destroy(Player player);
    void update(Location location, Player player);

}
