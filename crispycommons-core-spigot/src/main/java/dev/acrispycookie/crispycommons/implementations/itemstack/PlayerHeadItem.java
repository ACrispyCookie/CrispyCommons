package dev.acrispycookie.crispycommons.implementations.itemstack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyHeadItem;
import dev.dejvokep.boostedyaml.serialization.standard.TypeAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a player head item that can be customized with a player's skin based on their UUID.
 * <p>
 * This class extends {@link CrispyHeadItem} and provides methods to update the player head with
 * either the current player's skin if they are online, or the stored skin if they are offline.
 * It also supports asynchronous updates.
 * </p>
 */
public class PlayerHeadItem extends CrispyHeadItem {

    /**
     * The UUID of the player whose head skin will be used.
     */
    private final UUID uuid;

    /**
     * Constructs a {@code PlayerHeadItem} with the specified player's UUID.
     *
     * @param uuid the UUID of the player whose head skin will be used.
     */
    public PlayerHeadItem(@NotNull UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the UUID of the player associated with this head item.
     *
     * @return the player's UUID.
     */
    public @NotNull UUID getUuid() {
        return uuid;
    }

    /**
     * Updates the player head item with the latest skin based on the player's UUID.
     * <p>
     * If the player is online, the head will be updated with their current skin.
     * If the player is offline, the head will be updated with the stored skin data from Mojang's session server.
     * </p>
     *
     * @return the updated {@code PlayerHeadItem} instance.
     */
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull PlayerHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        assert meta != null : "Skull meta was null. Contact developer.";
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            meta.setOwner(player.getName());
        } else {
            setSkinToBase64(meta, getOfflinePlayerSkin(uuid));
        }
        setItemMeta(meta);
        return this;
    }

    /**
     * Updates the player head item asynchronously with the latest skin based on the player's UUID.
     * <p>
     * This method is useful for updating the head item without blocking the main server thread.
     * </p>
     *
     * @param plugin the plugin instance required to schedule the asynchronous task.
     * @return the updated {@code PlayerHeadItem} instance.
     */
    @Override
    public @NotNull PlayerHeadItem updateAsync(@NotNull JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskAsynchronously(plugin);
        return this;
    }

    /**
     * Retrieves the skin data for an offline player from Mojang's session server.
     *
     * @param uuid the UUID of the offline player.
     * @return the base64-encoded skin data.
     */
    private @NotNull String getOfflinePlayerSkin(@NotNull UUID uuid) {
        Gson g = new Gson();
        String signature = getUrlResponse("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
        JsonObject obj = g.fromJson(signature, JsonObject.class);
        return obj.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
    }

    /**
     * Sets the skin of the {@link SkullMeta} to the specified base64-encoded texture data.
     *
     * @param meta   the {@link SkullMeta} to which the skin will be applied.
     * @param base64 the base64-encoded texture data.
     */
    private void setSkinToBase64(@NotNull SkullMeta meta, @NotNull String base64) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static TypeAdapter<PlayerHeadItem> getHeadAdapter() {
        return new TypeAdapter<PlayerHeadItem>() {
            @Override
            public @NotNull Map<Object, Object> serialize(PlayerHeadItem item) {
                final Map<Object, Object> mappedObject = new LinkedHashMap<>();
                mappedObject.put("owner", item.getUuid());
                mappedObject.put("amount", item.getAmount());
                if (item.getItemMeta() != null) {
                    mappedObject.put("name", item.getItemMeta().getDisplayName());
                    mappedObject.put("lore", item.getItemMeta().getLore());
                }
                return mappedObject;
            }

            @Override
            public @NotNull PlayerHeadItem deserialize(Map<Object, Object> mappedObject) {
                PlayerHeadItem itemStackBuilder = new PlayerHeadItem(UUID.fromString((String) mappedObject.get("owner")));
                if (mappedObject.containsKey("amount"))
                    itemStackBuilder.amount((int) mappedObject.get("amount"));
                String name = (String) mappedObject.get("name");
                if (name != null)
                    itemStackBuilder.name(name);
                List<String> lines = (List<String>) mappedObject.get("lore");
                StringBuilder lore = new StringBuilder();
                for(String line : lines){
                    lore.append(line).append("\n");
                }
                itemStackBuilder.lore(lore.substring(0, Math.max(lore.toString().length() - 1, 0)));
                return itemStackBuilder;
            }
        };
    }
}

