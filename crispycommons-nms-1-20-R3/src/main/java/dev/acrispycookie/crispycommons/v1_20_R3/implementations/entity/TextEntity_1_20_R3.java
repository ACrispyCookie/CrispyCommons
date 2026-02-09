package dev.acrispycookie.crispycommons.v1_20_R3.implementations.entity;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.entity.TextEntity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TextEntity_1_20_R3 extends TextEntity {

    /**
     * The version independent instance of a custom armor stand.
     * <p>
     * This instance manages the spawning, updating and removing of the custom armor stand with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final TextDisplay textDisplay;

    public TextEntity_1_20_R3(@NotNull TextElement<?> element, @NotNull Location location) {
        super(element);
        textDisplay = (TextDisplay) location.getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        textDisplay.setVisibleByDefault(false);
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setGravity(false);
    }

    @Override
    public double offsetPerLine() {
        return -0.25;
    }

    @Override
    public boolean isDead() {
        return textDisplay.isDead();
    }

    @Override
    public @NotNull String getContent() {
        return Objects.requireNonNull(textDisplay.getText());
    }

    @Override
    public void spawn(@NotNull Player player) {
        Component elementValue = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(elementValue);

        if (name.equals(" ")) {
            return;
        }
        textDisplay.setText(name);
        player.showEntity(SpigotCrispyCommons.getInstance().getBukkitPlugin(), textDisplay);
    }

    @Override
    public void destroy(@NotNull Player player) {
        player.hideEntity(SpigotCrispyCommons.getInstance().getBukkitPlugin(), textDisplay);
    }

    @Override
    public void update(@NotNull Player player) {
        Component text = element.getFromContext(OfflinePlayer.class, player);
        String name = convertToName(text);

        if (name.equals(" ") && !textDisplay.isDead()) {
            player.hideEntity(SpigotCrispyCommons.getInstance().getBukkitPlugin(), textDisplay);
            return;
        } else if (!name.equals(" ") && textDisplay.isDead()) {
            player.showEntity(SpigotCrispyCommons.getInstance().getBukkitPlugin(), textDisplay);
        }

        textDisplay.setText(name);
    }

    @Override
    public void updateLocation(@NotNull Player player) {

    }

    @Override
    public @NotNull Location getLocation() {
        return textDisplay.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location) {
        textDisplay.teleport(location);
    }

    private String convertToName(Component component) {
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(component);
        return StringUtils.isBlank(text) ? " " : ChatColor.translateAlternateColorCodes('&', text);
    }
}
