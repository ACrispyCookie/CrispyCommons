package dev.acrispycookie.crispycommons.api.itemstack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class CrispyHeadItem extends CrispyItemStack {

    public abstract CrispyHeadItem update();

    public CrispyHeadItem(ItemStack itemStack) {
        super(itemStack);
    }

    public CrispyHeadItem() {
        super(Material.SKULL_ITEM, 1, (short) 3);
    }

    public CrispyHeadItem updateAsync(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskAsynchronously(plugin);
        return this;
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
