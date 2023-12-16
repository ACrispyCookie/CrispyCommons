package dev.acrispycookie.crispycommons.itemstack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public abstract class CrispyHeadItem extends CrispyItemStack {

    protected final JavaPlugin plugin;
    public abstract CrispyHeadItem update();

    public CrispyHeadItem(ItemStack itemStack, JavaPlugin plugin) {
        super(itemStack);
        this.plugin = plugin;
    }

    public CrispyHeadItem(JavaPlugin plugin) {
        super(Material.SKULL_ITEM, 1, (short) 3);
        this.plugin = plugin;
    }

    public CrispyHeadItem updateAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, this::update);
        return this;
    }

    protected SkullMeta setSkinToBase64(SkullMeta meta, String base64) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return meta;
    }

    protected String getUrlResponse(String urlStr) {
        URL url;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try{
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8) );
            String str;
            while((str = in.readLine()) != null) {
                sb.append( str );
            }
        } catch (Exception ignored) { }
        finally{
            try{
                if(in!=null) {
                    in.close();
                }
            }catch(IOException ignored) { }
        }
        return sb.toString();
    }

}
