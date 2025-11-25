package dev.acrispycookie.crispycommons.implementations.itemstack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyHeadItem;
import dev.dejvokep.boostedyaml.serialization.standard.TypeAdapter;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Represents a custom head item that is set using a skin URL.
 * <p>
 * This class extends {@link CrispyHeadItem} and allows the customization of a player head item
 * by applying a skin from a provided URL. The skin can be updated either synchronously or asynchronously.
 * </p>
 */
public class UrlHeadItem extends CrispyHeadItem {

    /**
     * The URL that will be used to retrieve the skin for the skull.
     */
    private final String url;

    /**
     * Constructs a {@code UrlHeadItem} with the specified skin URL.
     *
     * @param url the URL of the skin to be applied to the head item.
     */
    public UrlHeadItem(@NotNull String url) {
        this.url = url;
    }

    /**
     * Returns the URL of the skin associated with this head item.
     *
     * @return the skin URL.
     */
    public @NotNull String getUrl() {
        return url;
    }

    /**
     * Updates the head item by applying the skin from the specified URL.
     *
     * @return the updated {@code UrlHeadItem} instance.
     */
    @Override
    public @NotNull UrlHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        assert meta != null : "SkullMeta is null. Contact developer";
        setSkinToUrl(meta, url);
        setItemMeta(meta);
        return this;
    }

    /**
     * Updates the head item asynchronously by applying the skin from the specified URL.
     * <p>
     * In this implementation, the update is performed synchronously as the method
     * simply calls {@code update()}. This is a placeholder for potential asynchronous behavior.
     * </p>
     *
     * @param plugin the plugin instance required to schedule tasks.
     * @return the updated {@code UrlHeadItem} instance.
     */
    @Override
    public @NotNull UrlHeadItem updateAsync(@NotNull JavaPlugin plugin) {
        update();
        return this;
    }

    /**
     * Sets the skin of the {@link SkullMeta} to the skin located at the specified URL.
     *
     * @param skullMeta the {@link SkullMeta} to which the skin will be applied.
     * @param url       the URL of the skin to apply.
     */
    private void setSkinToUrl(@NotNull SkullMeta skullMeta, @NotNull String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static TypeAdapter<UrlHeadItem> getHeadAdapter() {
        return new TypeAdapter<UrlHeadItem>() {
            @Override
            public @NotNull Map<Object, Object> serialize(UrlHeadItem item) {
                final Map<Object, Object> mappedObject = new LinkedHashMap<>();
                mappedObject.put("url", item.getUrl());
                mappedObject.put("amount", item.getAmount());
                if (item.getItemMeta() != null) {
                    mappedObject.put("name", item.getItemMeta().getDisplayName());
                    mappedObject.put("lore", item.getItemMeta().getLore());
                }
                return mappedObject;
            }

            @Override
            public @NotNull UrlHeadItem deserialize(Map<Object, Object> mappedObject) {
                UrlHeadItem itemStackBuilder = new UrlHeadItem((String) mappedObject.get("url"));
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

