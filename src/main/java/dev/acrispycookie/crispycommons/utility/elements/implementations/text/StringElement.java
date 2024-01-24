package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class StringElement extends TextElement {

    public StringElement(Collection<? extends String> frames, int period, boolean async) {
        super(new ArrayList<>(frames
                .stream()
                .map(f -> ChatColor.translateAlternateColorCodes('&', f))
                .map(TextComponent::new)
                .collect(Collectors.toList()))
                , period, async);
    }

    public StringElement(Supplier<String> supplier, int period, boolean async) {
        super(() -> new TextComponent(
                ChatColor.translateAlternateColorCodes('&',
                        supplier.get()
                )), period, async);
    }
}
