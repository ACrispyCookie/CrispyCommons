package dev.acrispycookie.crispycommons.itemstack.implementations;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class CrispyUrlHeadItem extends CrispyHeadItem {

    private final String url;

    public CrispyUrlHeadItem(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public CrispyUrlHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta = setSkinToUrl(meta, url);
        setItemMeta(meta);
        return this;
    }

    @Override
    public CrispyUrlHeadItem updateAsync(JavaPlugin plugin) {
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
