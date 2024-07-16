package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleHologram extends AbstractHologram {

    private final HashMap<UUID, List<EntityInfo>> entities = new HashMap<>();

    public SimpleHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        List<EntityInfo> entities = constructHologram(data.getLines(), p);
        this.entities.put(p.getUniqueId(), entities);
        entities.forEach((info) -> {
            Entity e = info.getEntity();
            e.spawn(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    @Override
    protected void hide(Player p) {
        entities.get(p.getUniqueId()).forEach((info) -> info.getEntity().destroy(p));
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        entities.get(p.getUniqueId()).forEach((info) -> {
            Entity e = info.getEntity();
            e.update(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    @Override
    protected void globalUpdate() {

    }

    @Override
    protected void addLineInternal(int index) {
        for (Map.Entry<UUID, List<EntityInfo>> e : entities.entrySet()) {
            List<EntityInfo> infos = e.getValue();
            for (EntityInfo entityInfo : infos) {
                if (entityInfo.getIndex() < index)
                    continue;

                entityInfo.setIndex(entityInfo.getIndex() + 1);
            }
            Player p = Bukkit.getPlayer(e.getKey());
            Entity newLine = Entity.of(data.getLines().get(index));
            if (p != null)
                newLine.spawn(getEntityLocation(p, newLine, index), p);

            infos.add(index, new EntityInfo(index, newLine));
            entities.put(e.getKey(), infos);
        }
        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void removeLineInternal(int index) {
        for (Map.Entry<UUID, List<EntityInfo>> e : entities.entrySet()) {
            List<EntityInfo> infos = e.getValue();
            EntityInfo toRemove = null;
            for (EntityInfo entityInfo : infos) {
                if (entityInfo.getIndex() < index)
                    continue;
                if (entityInfo.getIndex() == index)
                    toRemove = entityInfo;

                entityInfo.setIndex(entityInfo.getIndex() - 1);
            }
            Player p = Bukkit.getPlayer(e.getKey());
            if (toRemove == null)
                return;
            if (p != null)
                toRemove.getEntity().destroy(p);
            infos.remove(toRemove);
            entities.put(e.getKey(), infos);
        }
        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void updateLines() {
        for (Player p : getCurrentlyViewing()) {
            hide(p);
            show(p);
        }
    }

    private Location getEntityLocation(Player player, Entity entity, int index) {
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        return location.clone().add(0, (index * entity.offsetPerLine()), 0);
    }

    private List<EntityInfo> constructHologram(List<DynamicElement<?, ?>> elements, OfflinePlayer player) {
        List<EntityInfo> entities = new ArrayList<>();
        int index = 0;
        for (DynamicElement<?, ?> t : elements) {
            Object toAdd = t.getFromContext(OfflinePlayer.class, player);
            if (toAdd == null)
                continue;
            entities.add(new EntityInfo(index, Entity.of(t)));
            index++;
        }

        return entities;
    }

    private static class EntityInfo {
        private int index;
        private final Entity entity;

        public EntityInfo(int index, Entity entity) {
            this.index = index;
            this.entity = entity;
        }

        public int getIndex() {
            return index;
        }

        public Entity getEntity() {
            return entity;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
