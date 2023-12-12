package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.entity.Player;

public interface CrispyHologram {

    void display();
    void update();
    void destroy();
    void onClick(Player player, int lineIndex);
}
