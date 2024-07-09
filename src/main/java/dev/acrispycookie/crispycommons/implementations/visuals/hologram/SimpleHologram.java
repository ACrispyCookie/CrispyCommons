package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.GlobalElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.PersonalElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleHologram extends AbstractHologram {

    private final HashMap<OfflinePlayer, List<EntityInfo>> entities = new HashMap<>();

    public SimpleHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        List<EntityInfo> entities = constructHologram(data.getLines(), p);
        this.entities.put(p, entities);
        entities.forEach((info) -> {
            Entity e = info.getEntity();
            e.spawn(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    @Override
    protected void hide(Player p) {
        entities.get(p).forEach((info) -> info.getEntity().destroy(p));
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        entities.get(p).forEach((info) -> {
            Entity e = info.getEntity();
            e.update(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    @Override
    protected void globalUpdate() {

    }

    @Override
    protected void updateEntities() {
        entities.forEach((p, l) -> {
            if (p.isOnline())
                l.forEach(info -> info.getEntity().destroy(p.getPlayer()));
            entities.put(p, constructHologram(data.getLines(), p));
        });
        update();
    }

    private Location getEntityLocation(Player player, Entity entity, int index) {
        Location location = data.getLocation() instanceof GlobalGeneralElement ?
                ((GlobalGeneralElement<Location>) data.getLocation()).getRaw() :
                ((PersonalGeneralElement<Location>) data.getLocation()).getRaw(player);
        return location.clone().add(0, (index * entity.offsetPerLine()), 0);
    }

    private List<EntityInfo> constructHologram(List<DynamicElement<?>> elements, OfflinePlayer player) {
        List<EntityInfo> entities = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            DynamicElement<?> t = elements.get(i);
            Object toAdd = t instanceof PersonalElement<?> ? ((PersonalElement<?>) t).getRaw(player) : ((GlobalElement<?>) t).getRaw();
            if (toAdd == null)
                continue;
            entities.add(new EntityInfo(i, Entity.of(t)));
        }

        return entities;
    }

    private static class EntityInfo {
        private final int index;
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
    }
}
