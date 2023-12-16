package dev.acrispycookie.crispycommons.itemstack;

import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CrispyUrlHeadItem extends CrispyHeadItem {

    private final String url;

    public CrispyUrlHeadItem(String url, JavaPlugin plugin) {
        super(plugin);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public CrispyUrlHeadItem update() {
        SkullMeta meta = (SkullMeta) getItemMeta();
        setSkinToBase64(meta, getUrlResponse(url));
        return this;
    }

    @Override
    public CrispyUrlHeadItem updateAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, this::update);
        return this;
    }
}
