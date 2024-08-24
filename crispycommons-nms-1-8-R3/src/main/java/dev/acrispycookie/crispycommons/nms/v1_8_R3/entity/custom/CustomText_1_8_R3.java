package dev.acrispycookie.crispycommons.nms.v1_8_R3.entity.custom;

import dev.acrispycookie.crispycommons.nms.entity.custom.CustomText;
import dev.acrispycookie.crispycommons.nms.entity.spigot.VersionArmorStand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomText_1_8_R3 implements CustomText {

    private final VersionArmorStand armorStand;

    public CustomText_1_8_R3(Location location, Component initialText) {
        armorStand = VersionArmorStand.newInstance(location);
        armorStand.setInvisible(true);
        armorStand.setNoClip(true); // Disables interaction
        armorStand.setBasePlate(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setSmall(true);
        armorStand.setCustomName(convertToName(initialText));
    }

    @Override
    public void setText(Component component) {
        armorStand.setCustomName(convertToName(component));
    }

    @Override
    public double offsetPerLine() {
        return 0.25;
    }

    @Override
    public Location getLocation() {
        return armorStand != null ? armorStand.getLocation() : null;
    }

    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        if (armorStand.getCustomName().equals(" "))
            return;
        armorStand.spawn(player);
        armorStand.updateLocation(player);
        armorStand.updateMeta(player);
    }

    @Override
    public void destroy(@NotNull Player player) {
        armorStand.destroy(player);
    }

    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        if (armorStand.getCustomName().equals(" ") && armorStand.isDestroyed()) {
            armorStand.destroy(player);
            return;
        } else if (armorStand.isDestroyed()) {
            armorStand.spawn(player);
            return;
        }

        armorStand.setLocation(location);
        armorStand.updateLocation(player);
        armorStand.updateMeta(player);
    }



    private String convertToName(Component component) {
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(component);
        return StringUtils.isBlank(text) ? " " : ChatColor.translateAlternateColorCodes('&', text);
    }
}
