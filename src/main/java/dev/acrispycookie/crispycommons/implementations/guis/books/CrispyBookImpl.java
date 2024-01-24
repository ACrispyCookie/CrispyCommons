package dev.acrispycookie.crispycommons.implementations.guis.books;

import dev.acrispycookie.crispycommons.implementations.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookPage;
import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItem;
import dev.acrispycookie.crispycommons.implementations.wrappers.itemstack.CrispyItemStack;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class CrispyBookImpl implements CrispyBook {

    private final List<BookPage> pages;
    private final CrispyItemStack bookItem;

    public CrispyBookImpl(Collection<? extends BookPage> pages) {
        this.pages = new ArrayList<>(pages);
        this.bookItem = constructBookItem();
    }

    @Override
    public void open(Player p) {
        final int slot = p.getInventory().getHeldItemSlot();
        final ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, bookItem);
        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte)0);
        buf.writerIndex(1);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
        CraftPlayer craftP = (CraftPlayer) p;
        craftP.getHandle().playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }

    private CrispyItemStack constructBookItem() {
        try {
            CrispyItemStack book = new CrispyItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            List<IChatBaseComponent> pageList = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(meta);
            this.pages.forEach(p -> pageList.add(p.getBukkitComponent()));
            book.setItemMeta(meta);
            return book;
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            Bukkit.getLogger().log(Level.SEVERE, "An error has occured while trying to construct a book GUI!");
            Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
