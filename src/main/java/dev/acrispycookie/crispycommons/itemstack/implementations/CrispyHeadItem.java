package dev.acrispycookie.crispycommons.itemstack.implementations;

import dev.acrispycookie.crispycommons.itemstack.CrispyItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
