package dev.acrispycookie.crispycommons.implementations.itemstack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyHeadItem;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class UrlHeadItem extends CrispyHeadItem {

    private final String url;

    public UrlHeadItem(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public UrlHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        setSkinToUrl(meta, url);
        setItemMeta(meta);
        return this;
    }

    @Override
    public @NotNull UrlHeadItem updateAsync(@NotNull JavaPlugin plugin) {
        update();
        return this;
    }

    private SkullMeta setSkinToUrl(SkullMeta skullMeta, String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }

        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return skullMeta;
    }
}
