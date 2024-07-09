package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.GlobalElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.PersonalElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.entity.TextEntity;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleHologram extends AbstractHologram {
    private final HashMap<OfflinePlayer, List<EntityInfo>> personalEntities = new HashMap<>();
    private final List<EntityInfo> globalEntities = new ArrayList<>();

    public SimpleHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    public void prepareShow() {
        initGlobalEntities();
        super.prepareShow();
    }

    private void initGlobalEntities() {
        for (int i = 0; i < data.getLines().size(); i++) {
            DynamicElement<?> element = data.getLines().get(i);

            if (element instanceof GlobalElement<?>) {
                globalEntities.add(new EntityInfo(i, Entity.of(element)));
            }
        }
    }

    private List<EntityInfo> getPersonalEntities(Player p) {
        List<EntityInfo> playerEntities = new ArrayList<>();
        for (int i = 0; i < data.getLines().size(); i++) {
            DynamicElement<?> element = data.getLines().get(i);

            playerEntities.add(new EntityInfo(i, Entity.of(element)));
        }
        return playerEntities;
    }

    @Override
    protected void show(Player p) {
        if (!personalEntities.containsKey(p))
            personalEntities.put(p, getPersonalEntities(p));

        globalEntities.forEach((global) -> {
            Entity entity = global.getEntity();
            entity.spawn(getEntityLocation(entity, global.getIndex()), p);
        });
        personalEntities.get(p).forEach((personal) -> {
            Entity entity = personal.getEntity();
            entity.spawn(getEntityLocation(entity, personal.getIndex()), p);
        });
    }

    @Override
    protected void hide(Player p) {
        globalEntities.forEach((global) -> {
            Entity entity = global.getEntity();
            entity.destroy(p);
        });

        if (!personalEntities.containsKey(p))
            return;
        personalEntities.get(p).forEach((personal) -> {
            Entity entity = personal.getEntity();
            entity.destroy(p);
        });
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        globalEntities.forEach((global) -> {
            Entity entity = global.getEntity();
            entity.update(getEntityLocation(entity, global.getIndex()), p);
        });

        if (!personalEntities.containsKey(p))
            return;
        personalEntities.get(p).forEach((personal) -> {
            Entity entity = personal.getEntity();
            entity.update(getEntityLocation(entity, personal.getIndex()), p);
        });
    }

    @Override
    protected void globalUpdate() {

    }

    private Location getEntityLocation(Entity entity, int index) {
        return data.getLocation().clone().add(0, (index * entity.offsetPerLine()), 0);
    }

    private class EntityInfo {
        private int index;
        private Entity entity;

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
    }
}
