package dev.acrispycookie.crispycommons.implementations.visual.hologram;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.api.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.visual.hologram.data.HologramData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
     * A list of players' UUIDs that are in a different world from the hologram.
     */
    private final Set<UUID> cannotView = new HashSet<>();

    /**
     * Constructs a {@code SimpleHologram} with the specified data, receivers, time-to-live, and visibility.
     *
     * @param data       the {@link HologramData} containing the data for the hologram.
     * @param receivers  the collection of players who will receive the hologram.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the hologram.
     * @param isPublic   whether the hologram should be visible to all players.
     */
    public SimpleHologram(@NotNull HologramData data, @NotNull Collection<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.GLOBAL, isPublic);
    }

    /**
     * Displays the hologram to the specified player.
     * <p>
     * Constructs the hologram entities and spawns them at the appropriate location for the player.
     * </p>
     *
     * @param player the player to whom the hologram will be shown.
     */
    @Override
    protected void show(@NotNull Player player) {
        List<EntityInfo> entities = constructHologram(getLines(), player);
        entities.stream().map(EntityInfo::getEntity).forEach((entity) -> entity.spawn(player));
        this.entities.put(player.getUniqueId(), entities);
    }

    /**
     * Hides the hologram from the specified player.
     * <p>
     * Destroys the hologram entities for the player.
     * </p>
     *
     * @param player the player from whom the hologram will be hidden.
     */
    @Override
    protected void hide(@NotNull Player player) {
        entities.get(player.getUniqueId()).forEach((info) -> info.getEntity().destroy(player));
    }

    /**
     * Updates the hologram for a specific player.
     * <p>
     * This method updates the positions of the hologram entities for the player.
     * </p>
     *
     * @param player the player for whom the hologram will be updated.
     */
    @Override
    protected void perPlayerUpdate(@NotNull Player player) {
        // No per player action needed.
    }

    /**
     * Performs a global update for the hologram.
     * <p>
     * This method is currently empty as global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        for (int i = 0; i < data.getLines().size(); i++) {
            updateLine(i);
        }
        updateLocation();
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
            OfflinePlayer player = Bukkit.getOfflinePlayer(e.getKey());

            double offset = index == 0 ? 0 : infos.get(index - 1).getLocationOffset() + infos.get(index - 1).getEntity().offsetPerLine();
            Location location = infos.get(0).getEntity().getLocation().clone();
            Entity newLine = Entity.of(getLines().get(index), location.add(0, offset, 0));
            infos.add(index, new EntityInfo(offset, newLine));
            entities.put(e.getKey(), infos);

            if (player.getPlayer() != null && isWatching((Player) player))
                newLine.spawn((Player) player);

            if (index != infos.size() - 1)
                offsetLines(index + 1, newLine.offsetPerLine(), infos, player.getPlayer());
        }
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
            EntityInfo toRemove = infos.get(index);
            Player player = Bukkit.getPlayer(e.getKey());
            infos.remove(toRemove);
            entities.put(e.getKey(), infos);

            if (player != null && isWatching(player))
                toRemove.getEntity().destroy(player);

            if (player != null && index != infos.size())
                offsetLines(index, -toRemove.getEntity().offsetPerLine(), infos, player);

        }
    }

    /**
     * Updates all lines in the hologram.
     * <p>
     * This method hides and re-shows the hologram to all currently viewing players to reflect the updates.
     * </p>
     */
    @Override
    protected void resetLines() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            hide(player);
            show(player);
        }
    }

    @Override
    public void updateLocation() {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            Location location = getLocation().getFromContext(OfflinePlayer.class, player);
            assert location.getWorld() != null : "World is null. Contact developer.";
            if (!updateWorld(location.getWorld(), player))
                continue;
            for (int i = 0; i < data.getLines().size(); i++) {
                EntityInfo info = entities.get(player.getUniqueId()).get(i);
                Entity entity = info.getEntity();
                entity.setLocation(location.clone().add(0, info.getLocationOffset(), 0));
                entity.updateLocation(player);
            }
        }
    }

    @Override
    public void updateLine(int index) {
        Set<Player> viewers = getCurrentlyViewing();
        for (Player player : viewers) {
            if (cannotView.contains(player.getUniqueId()))
                continue;
            EntityInfo info = entities.get(player.getUniqueId()).get(index);
            info.getEntity().update(player);
        }
    }

    private boolean updateWorld(@NotNull World newWorld, @NotNull Player player) {
        if (!getLocation().isDynamic()) {
            if (newWorld.equals(player.getWorld()))
                return true;
            hideInternal(player);
            return false;
        }

        if (!player.getWorld().equals(newWorld)) {
            cannotView.add(player.getUniqueId());
            hide(player);
            return false;
        }

        World previousWorld = entities.get(player.getUniqueId()).iterator().next().getEntity().getLocation().getWorld();
        assert previousWorld != null : "World is null. Contact developer.";
        if (cannotView.contains(player.getUniqueId()) || !previousWorld.equals(newWorld)) {
            show(player);
            cannotView.remove(player.getUniqueId());
        }
        return true;
    }

    /**
     * Offsets the lines starting from the specified index for a specified amount for
     * the specified player.
     *
     * @param index the index to start from.
     * @param offsetAmount the amount to offset each line.
     * @param infos the entities to offset
     * @param player the player to render the new locations
     */
    private void offsetLines(int index, double offsetAmount, @NotNull List<EntityInfo> infos, @NotNull Player player) {
        for (int i = index; i < infos.size(); i++) {
            EntityInfo info = infos.get(i);
            Entity entity = info.getEntity();
            info.addOffset(offsetAmount);
            entity.setLocation(entity.getLocation().add(0, offsetAmount, 0));

            if (isWatching(player)) {
                entity.updateLocation(player);
            }
        }
    }

    /**
     * Constructs the hologram entities for a player based on the hologram's lines.
     *
     * @param elements the list of {@link DynamicElement} representing the hologram's lines.
     * @param player   the player viewing the hologram.
     * @return a list of {@link EntityInfo} representing the constructed entities.
     */
    private @NotNull List<EntityInfo> constructHologram(@NotNull List<DynamicElement<?, ?>> elements, @NotNull Player player) {
        List<EntityInfo> entities = new ArrayList<>();
        Location location = getLocation().getFromContext(OfflinePlayer.class, player).clone();
        double offset = 0;

        for (DynamicElement<?, ?> t : elements) {
            Entity entity = Entity.of(t, location.clone().add(0, offset, 0));
            entities.add(new EntityInfo(offset, entity));
            offset += entity.offsetPerLine();
        }

        return entities;
    }

    /**
     * A helper class to store information about a hologram entity, including its index and the entity itself.
     */
    private static class EntityInfo {
        private double locationOffset;
        private final Entity entity;

        /**
         * Constructs an {@code EntityInfo} with the specified index and entity.
         *
         * @param locationOffset the location offset of the entity within the hologram.
         * @param entity the {@link Entity} representing the hologram line.
         */
        public EntityInfo(double locationOffset, @NotNull Entity entity) {
            this.locationOffset = locationOffset;
            this.entity = entity;
        }

        /**
         * Retrieves the entity representing the hologram line.
         *
         * @return the {@link Entity}.
         */
        public @NotNull Entity getEntity() {
            return entity;
        }


        /**
         * Retrieves the location offset of the entity.
         *
         * @return the Y-offset of the entity.
         */
        public double getLocationOffset() {
            return locationOffset;
        }

        /**
         * Sets the location offset of the entity within the hologram.
         *
         * @param locationOffset the new location offset to set.
         */
        public void setLocationOffset(double locationOffset) {
            this.locationOffset = locationOffset;
        }

        /**
         * Adds the given amount to the location offset.
         *
         * @param amount the amount to add.
         */
        public void addOffset(double amount) {
            this.locationOffset += amount;
        }
    }
}

