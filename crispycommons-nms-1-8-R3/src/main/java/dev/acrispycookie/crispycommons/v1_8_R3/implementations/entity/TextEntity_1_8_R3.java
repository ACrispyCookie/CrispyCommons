package dev.acrispycookie.crispycommons.v1_8_R3.implementations.entity;

import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.entity.TextEntity;
import dev.acrispycookie.crispycommons.utility.entity.VersionArmorStand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TextEntity_1_8_R3 extends TextEntity {

    /**
     * The version independent instance of a custom armor stand.
     * <p>
     * This instance manages the spawning, updating and removing of the custom armor stand with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final VersionArmorStand armorStand;

    public TextEntity_1_8_R3(@NotNull TextElement<?> element, @NotNull Location location) {
        super(element, location);
        armorStand = VersionArmorStand.newInstance(location);
        armorStand.setInvisible(true);
        armorStand.setNoClip(true); // Disables interaction
        armorStand.setBasePlate(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setSmall(true);
    }

    @Override
    public double offsetPerLine() {
        return -0.25;
    }

    @Override
    public void spawn(@NotNull Player player) {
        Component elementValue = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(elementValue);

        if (name.equals(" ")) {
            armorStand.destroy(player);
            return;
        }
        armorStand.setCustomName(name);
        armorStand.spawn(player);
        armorStand.updateLocation(player);
        armorStand.updateMeta(player);
    }

    @Override
    public void destroy(@NotNull Player player) {
        armorStand.destroy(player);
    }

    @Override
    public void update(@NotNull Player player) {
        Component text = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(text);

        if (name.equals(" ") && !armorStand.isDestroyed()) {
            armorStand.destroy(player);
            return;
        } else if (!name.equals(" ") && armorStand.isDestroyed()) {
            armorStand.spawn(player);
        }

        armorStand.setCustomName(name);
        armorStand.updateMeta(player);
    }

    @Override
    public @NotNull Location getLocation() {
        return armorStand.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location, @NotNull Player player) {
        armorStand.setLocation(location);
        armorStand.updateLocation(player);
    }

    private String convertToName(Component component) {
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(component);
        return StringUtils.isBlank(text) ? " " : ChatColor.translateAlternateColorCodes('&', text);
    }
}
