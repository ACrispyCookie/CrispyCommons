package dev.acrispycookie.crispycommons.v1_20_R3.implementations.entity;

import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.entity.TextEntity;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionTextDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TextEntity_1_20_R3 extends TextEntity {

    /**
     * The version independent instance of a custom armor stand.
     * <p>
     * This instance manages the spawning, updating and removing of the custom armor stand with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final VersionTextDisplay textDisplay;

    public TextEntity_1_20_R3(@NotNull TextElement<?> element, @NotNull Location location) {
        super(element);
        textDisplay = VersionTextDisplay.newInstance(location);
        textDisplay.setGravity(false);
    }

    @Override
    public double offsetPerLine() {
        return -0.25;
    }

    @Override
    public boolean isDead() {
        return textDisplay.isDestroyed();
    }

    @Override
    public @NotNull String getContent() {
        return textDisplay.getContent();
    }

    @Override
    public void spawn(@NotNull Player player) {
        Component elementValue = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(elementValue);

        if (name.equals(" ")) {
            textDisplay.destroy(player);
            return;
        }
        textDisplay.setText(name);
        textDisplay.spawn(player);
        textDisplay.updateLocation(player);
        textDisplay.updateMeta(player);
    }

    @Override
    public void destroy(@NotNull Player player) {
        textDisplay.destroy(player);
    }

    @Override
    public void update(@NotNull Player player) {
        Component text = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(text);

        if (name.equals(" ") && !textDisplay.isDestroyed()) {
            textDisplay.destroy(player);
            return;
        } else if (!name.equals(" ") && textDisplay.isDestroyed()) {
            textDisplay.spawn(player);
        }

        textDisplay.setText(name);
        textDisplay.updateMeta(player);
    }

    @Override
    public void updateLocation(@NotNull Player player) {
        textDisplay.updateLocation(player);
    }

    @Override
    public @NotNull Location getLocation() {
        return textDisplay.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location) {
        textDisplay.setLocation(location);
    }

    private String convertToName(Component component) {
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(component);
        return StringUtils.isBlank(text) ? " " : ChatColor.translateAlternateColorCodes('&', text);
    }
}
