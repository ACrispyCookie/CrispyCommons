package dev.acrispycookie.crispycommons.implementations.visual.hologram;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.api.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.visual.hologram.data.HologramData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * A concrete implementation of {@link AbstractHologram} that represents a simple hologram.
 * <p>
 * {@code SimpleHologram} handles the creation, display, and management of hologram lines
 * as entities in the Minecraft world. It allows for per-player customization and real-time updates.
 * </p>
 */
public class SimpleHologram extends AbstractHologram {

    /**
     * A map storing the hologram entities associated with each player by their UUID.
     */
    private final HashMap<UUID, List<EntityInfo>> entities = new HashMap<>();

    /**
     * Constructs a {@code SimpleHologram} with the specified data, receivers, time-to-live, and visibility.
     *
     * @param data       the {@link HologramData} containing the data for the hologram.
     * @param receivers  the collection of players who will receive the hologram.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the hologram.
     * @param isPublic   whether the hologram should be visible to all players.
     */
    public SimpleHologram(HologramData data, Collection<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the hologram to the specified player.
     * <p>
     * Constructs the hologram entities and spawns them at the appropriate location for the player.
     * </p>
     *
     * @param p the player to whom the hologram will be shown.
     */
    @Override
    protected void show(Player p) {
        List<EntityInfo> entities = constructHologram(data.getLines(), p);
        this.entities.put(p.getUniqueId(), entities);
        entities.forEach((info) -> {
            Entity e = info.getEntity();
            e.spawn(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    /**
     * Hides the hologram from the specified player.
     * <p>
     * Destroys the hologram entities for the player.
     * </p>
     *
     * @param p the player from whom the hologram will be hidden.
     */
    @Override
    protected void hide(Player p) {
        entities.get(p.getUniqueId()).forEach((info) -> info.getEntity().destroy(p));
    }

    /**
     * Updates the hologram for a specific player.
     * <p>
     * This method updates the positions of the hologram entities for the player.
     * </p>
     *
     * @param p the player for whom the hologram will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {
        entities.get(p.getUniqueId()).forEach((info) -> {
            Entity e = info.getEntity();
            e.update(getEntityLocation(p, e, info.getIndex()), p);
        });
    }

    /**
     * Performs a global update for the hologram.
     * <p>
     * This method is currently empty as global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }

    /**
     * Adds a line to the hologram at the specified index for all players.
     * <p>
     * This method adjusts the indices of existing entities and adds the new line entity
     * at the appropriate position.
     * </p>
     *
     * @param index the index at which to add the new line.
     */
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
            if (p != null && isWatching(p))
                newLine.spawn(getEntityLocation(p, newLine, index), p);

            infos.add(index, new EntityInfo(index, newLine));
            entities.put(e.getKey(), infos);
        }
        if (isAnyoneWatching())
            update();
    }

    /**
     * Removes a line from the hologram at the specified index for all players.
     * <p>
     * This method adjusts the indices of existing entities and removes the specified line entity.
     * </p>
     *
     * @param index the index of the line to remove.
     */
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
            if (p != null && isWatching(p))
                toRemove.getEntity().destroy(p);
            infos.remove(toRemove);
            entities.put(e.getKey(), infos);
        }
        if (isAnyoneWatching())
            update();
    }

    /**
     * Updates all lines in the hologram.
     * <p>
     * This method hides and re-shows the hologram to all currently viewing players to reflect the updates.
     * </p>
     */
    @Override
    protected void updateLines() {
        for (Player p : getCurrentlyViewing()) {
            hide(p);
            show(p);
        }
    }

    /**
     * Calculates the location of an entity within the hologram.
     * <p>
     * The location is based on the player's perspective, the entity, and its index within the hologram.
     * </p>
     *
     * @param player the player viewing the hologram.
     * @param entity the entity representing a line in the hologram.
     * @param index  the index of the line.
     * @return the calculated {@link Location} for the entity.
     */
    private Location getEntityLocation(Player player, Entity entity, int index) {
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        return location.clone().add(0, (index * entity.offsetPerLine()), 0);
    }

    /**
     * Constructs the hologram entities for a player based on the hologram's lines.
     *
     * @param elements the list of {@link DynamicElement} representing the hologram's lines.
     * @param player   the player viewing the hologram.
     * @return a list of {@link EntityInfo} representing the constructed entities.
     */
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

    /**
     * A helper class to store information about a hologram entity, including its index and the entity itself.
     */
    private static class EntityInfo {
        private int index;
        private final Entity entity;

        /**
         * Constructs an {@code EntityInfo} with the specified index and entity.
         *
         * @param index  the index of the entity within the hologram.
         * @param entity the {@link Entity} representing the hologram line.
         */
        public EntityInfo(int index, Entity entity) {
            this.index = index;
            this.entity = entity;
        }

        /**
         * Retrieves the index of the entity within the hologram.
         *
         * @return the index of the entity.
         */
        public int getIndex() {
            return index;
        }

        /**
         * Retrieves the entity representing the hologram line.
         *
         * @return the {@link Entity}.
         */
        public Entity getEntity() {
            return entity;
        }

        /**
         * Sets the index of the entity within the hologram.
         *
         * @param index the new index to set.
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }
}

