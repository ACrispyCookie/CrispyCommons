package dev.acrispycookie.crispycommons.implementations.guis.books;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.book.CrispyBook;
import dev.acrispycookie.crispycommons.api.guis.book.wrappers.BookPage;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
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
import java.util.stream.Collectors;

public class SimpleCrispyBook implements CrispyBook {

    private final List<BookPage> pages;

    public SimpleCrispyBook(Collection<? extends BookPage> pages) {
        this.pages = new ArrayList<>(pages);
    }

    @Override
    public void open(Player p) {
        Audience player = CrispyCommons.getBukkitAudiences().player(p);
        Book book = Book.book(Component.empty(), Component.empty(), pages
                .stream()
                .map(BookPage::getComponent)
                .collect(Collectors.toList()));
        player.openBook(book);
    }
}
