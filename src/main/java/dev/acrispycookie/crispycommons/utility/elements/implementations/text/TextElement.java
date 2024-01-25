package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class TextElement extends AnimatedElement<TextComponent> {

    public TextElement(ArrayList<? extends TextComponent> frames, int period, boolean async) {
        super(new ArrayList<>(frames
                .stream()
                .map(t -> new TextComponent(
                        ChatColor.translateAlternateColorCodes('&', t.toPlainText()
                        )
                )).collect(Collectors.toList())), period, async);
    }

    public TextElement(Supplier<? extends TextComponent> supplier, int period, boolean async) {
        super(() -> new TextComponent(
                ChatColor.translateAlternateColorCodes('&',
                        supplier.get().toPlainText()
                )), period, async);
    }
}
