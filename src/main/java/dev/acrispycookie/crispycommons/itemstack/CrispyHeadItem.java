package dev.acrispycookie.crispycommons.itemstack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import java.util.UUID;

public class CrispyHeadItem extends CrispyItemStack {

    private UUID uuid;
    private String url;

    public CrispyHeadItem(UUID uuid) {
        super(Material.SKULL_ITEM, 1, (short) 3);
        this.uuid = uuid;
    }

    public CrispyHeadItem(String url) {
        super(Material.SKULL_ITEM, 1, (short) 3);
        this.url = url;
    }

    public CrispyHeadItem setTextureSync() {
        if(uuid != null)
            setTextureUuid();
        else if(url != null)
            setTextureUrl();
        return this;
    }

    public CrispyHeadItem setTextureAsync() {

        return this;
    }

    private void setTextureUrl() {

    }

    private void setTextureUuid() {

    }


}
