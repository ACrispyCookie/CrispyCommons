package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class TextElement extends AnimatedElement<Component> {

    public TextElement(Collection<? extends String> frames, int period, boolean async) {
        super(new ArrayList<>(frames
                .stream()
                .map(t -> LegacyComponentSerializer.legacyAmpersand().deserialize(t))
                .collect(Collectors.toList())), period, async);
    }

    public TextElement(Supplier<? extends String> supplier, int period, boolean async) {
        super(() -> LegacyComponentSerializer.legacySection().deserialize(supplier.get()), period, async);
    }
}
