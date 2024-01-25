package dev.acrispycookie.crispycommons.implementations.guis.books;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.book.CrispyBook;
import dev.acrispycookie.crispycommons.api.guis.book.wrappers.BookPage;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleCrispyBook implements CrispyBook {

    private final List<BookPage> pages;
    private final CrispyItemStack bookItem;

    public SimpleCrispyBook(Collection<? extends BookPage> pages) {
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
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "An error occurred while constructing a book item!");
            return null;
        }
    }
}
