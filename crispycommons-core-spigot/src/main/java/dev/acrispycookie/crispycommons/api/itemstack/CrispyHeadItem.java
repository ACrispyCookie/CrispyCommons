package dev.acrispycookie.crispycommons.api.itemstack;

import com.cryptomorin.xseries.XMaterial;
import dev.acrispycookie.crispycommons.implementations.itemstack.PlayerHeadItem;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.serialization.ConfigurationSerializable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * An abstract extension of {@link CrispyItemStack} specifically designed for handling player heads (skulls).
 * <p>
 * The {@link CrispyHeadItem} class provides a foundation for creating and updating custom head items asynchronously.
 * It includes a method for retrieving data from a URL, allowing for dynamic updates of the head's texture or other
 * properties based on external resources.
 * </p>
 */
public abstract class CrispyHeadItem extends CrispyItemStack implements ConfigurationSerializable {

    /**
     * Updates the {@code CrispyHeadItem} instance.
     * <p>
     * This method should be implemented by subclasses to define how the head item is updated.
     * </p>
     *
     * @return the updated {@code CrispyHeadItem} instance.
     */
    public abstract @NotNull CrispyHeadItem update();

    /**
     * Constructs a {@code CrispyHeadItem} from an existing {@link ItemStack}.
     *
     * @param itemStack the existing {@link ItemStack} to wrap.
     */
    public CrispyHeadItem(@NotNull ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Constructs a default {@code CrispyHeadItem} as a skull item with a quantity of 1 and a data value of 3
     * (indicating a player head).
     */
    public CrispyHeadItem() {
        super(XMaterial.PLAYER_HEAD, 1);
    }

    /**
     * Updates the {@code CrispyHeadItem} asynchronously using the specified {@link JavaPlugin} to schedule the task.
     * <p>
     * This method allows the update process to be offloaded to a separate thread, preventing it from blocking the main
     * server thread.
     * </p>
     *
     * @param plugin the {@link JavaPlugin} instance used to run the update task asynchronously.
     * @return the {@code CrispyHeadItem} instance.
     */
    public @NotNull CrispyHeadItem updateAsync(@NotNull JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskAsynchronously(plugin);
        return this;
    }

    /**
     * Retrieves the response from the specified URL as a string.
     * <p>
     * This method handles the connection and reading of data from the URL, returning the full response content as a
     * string. It uses {@link StandardCharsets#UTF_8} for decoding the input stream.
     * </p>
     *
     * @param urlStr the URL to connect to and retrieve data from.
     * @return the response content as a string, or an empty string if an error occurs.
     */
    protected @NotNull String getUrlResponse(@NotNull String urlStr) {
        URL url;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ignored) {
            // Handle the exception if necessary
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignored) {
                // Handle the exception if necessary
            }
        }
        return sb.toString();
    }
}
