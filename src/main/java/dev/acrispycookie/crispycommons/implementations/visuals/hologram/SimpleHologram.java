package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.utility.entities.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SimpleHologram extends AbstractHologram implements Listener {
    private final List<Entity> entities = new ArrayList<>();

    public SimpleHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive);
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
        init();
    }

    private void init() {
        for (int i = 0; i < data.getLines().size(); i++) {
            Entity entity = Entity.of(data.getLines().get(i));
            entities.add(entity);
        }
    }

    @Override
    protected void show(Player p) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.spawn(getEntityLocation(entity, i), p);
        }
    }

    @Override
    protected void hide(Player p) {
        entities.forEach(entity -> entity.destroy(p));
    }

    @Override
    protected void update(Player p) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update(getEntityLocation(entity, i), p);
        }
    }

    private Location getEntityLocation(Entity entity, int index) {
        return data.getLocation().clone().add(0, (index * entity.offsetPerLine()), 0);
    }
}
