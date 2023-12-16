package dev.acrispycookie.crispycommons.itemstack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.UUID;

public class CrispyPlayerHeadItem extends CrispyHeadItem {

    private final UUID uuid;

    public CrispyPlayerHeadItem(UUID uuid, JavaPlugin plugin) {
        super(plugin);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public CrispyPlayerHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        if (Bukkit.getPlayer(uuid) != null) {
            meta.setOwner(Bukkit.getPlayer(uuid).getName());
        } else {
            meta = setSkinToBase64(meta, getOfflinePlayerSkin(uuid));
        }
        setItemMeta(meta);
        return this;
    }

    @Override
    public CrispyPlayerHeadItem updateAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, this::update);
        return this;
    }

    private String getOfflinePlayerSkin(UUID uuid) {
        Gson g = new Gson();
        String signature = getUrlResponse("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString());
        JsonObject obj = g.fromJson(signature, JsonObject.class);
        return obj.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
    }


}
