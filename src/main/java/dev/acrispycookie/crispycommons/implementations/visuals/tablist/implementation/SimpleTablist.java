package dev.acrispycookie.crispycommons.implementations.visuals.tablist.implementation;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.AbstractCrispyTablist;
import dev.acrispycookie.crispycommons.utility.elements.AbstractCrispyElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class SimpleTablist extends AbstractCrispyTablist {

    public SimpleTablist(List<List<StringElement>> content, Set<? extends Player> receivers) {
        super(content, receivers);
    }

    @Override
    protected void show(Player p) {
        String headerText = getHeader()
                .stream()
                .map((AbstractCrispyElement::getRaw))
                .map((TextComponent::getText))
                .collect(Collectors.joining("\n"));
        String footerText = getFooter()
                .stream()
                .map((AbstractCrispyElement::getRaw))
                .map((TextComponent::getText))
                .collect(Collectors.joining("\n"));
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + headerText + "\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footerText + "\"}");

        sendTablistPacket(p, header, footer);
    }

    @Override
    protected void hide(Player p) {
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"\"}");

        sendTablistPacket(p, header, footer);
    }

    @Override
    protected void update(Player p) {
        show(p);
    }

    private void sendTablistPacket(Player p, IChatBaseComponent header, IChatBaseComponent footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);

        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, footer);
        } catch (Exception e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "An error occurred while sending a tablist packet!");
        } finally {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
