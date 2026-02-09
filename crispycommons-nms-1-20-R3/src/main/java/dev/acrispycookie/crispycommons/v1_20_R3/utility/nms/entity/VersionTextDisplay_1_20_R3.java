package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.entity;

import dev.acrispycookie.crispycommons.utility.nms.entity.VersionEntity;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionTextDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display.TextDisplay;
import net.minecraft.world.entity.EntityTypes;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class VersionTextDisplay_1_20_R3 extends VersionEntity_1_20_R3 implements VersionTextDisplay {

    private final TextDisplay textDisplay;

    public VersionTextDisplay_1_20_R3(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        textDisplay = new TextDisplay(EntityTypes.aY, world.getHandle());
        textDisplay.a(world.getHandle(), location.getX(), location.getY(), location.getZ(), new HashSet<>(), (float) 0, (float) 0);
    }

    @Override
    public void setBackgroundColor(@NotNull Color color) {
        textDisplay.c(color.asARGB());
    }

    @Override
    public void setTextOpacity(byte opacity) {
        textDisplay.c(opacity);
    }

    @Override
    public void setText(@NotNull String text) {
        textDisplay.c(IChatBaseComponent.b(text));
    }

    @Override
    public void setGravity(boolean gravity) {
        textDisplay.e(gravity);
    }

    @Override
    public @NotNull String getContent() {
        IChatBaseComponent component = textDisplay.u();
        if (component != null)
            return component.getString();
        return " ";
    }

    @Override
    public void attachEntity(@NotNull Player player, @NotNull VersionEntity versionEntity) {
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(((VersionEntity_1_20_R3) versionEntity).getInternalEntity(), textDisplay);
        ((CraftPlayer) player).getHandle().c.b(attach);
    }

    public void spawn(@NotNull Player player) {
        PacketPlayOutSpawnEntity spawnArmorStand = new PacketPlayOutSpawnEntity(textDisplay);
        ((CraftPlayer) player).getHandle().c.b(spawnArmorStand);
    }

    public void destroy(@NotNull Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(textDisplay.aj());
        ((CraftPlayer) player).getHandle().c.b(destroy);
    }

    public void updateLocation(@NotNull Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(textDisplay);
        ((CraftPlayer) player).getHandle().c.b(teleport);
    }

    public void updateMeta(@NotNull Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(textDisplay.aj(), textDisplay.an().c());
        ((CraftPlayer) player).getHandle().c.b(metadata);
    }

    public void setDead(boolean dead) {
        if (dead)
            textDisplay.al();
    }

    public void setLocation(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        textDisplay.a(world.getHandle(), location.getX(), location.getY(), location.getZ(), new HashSet<>(), (float) 0, (float) 0);
    }

    public @NotNull Location getLocation() {
        return textDisplay.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public @NotNull Entity getInternalEntity() {
        return textDisplay;
    }
}
