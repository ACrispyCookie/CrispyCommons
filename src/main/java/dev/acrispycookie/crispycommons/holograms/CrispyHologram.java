package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.entity.Player;

public interface CrispyHologram {

    void display();
    void display(int tickLifetime);
    void update();
    void destroy();
    void click(Player p);
}
