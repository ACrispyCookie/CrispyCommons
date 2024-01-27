package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class TextElement extends AnimatedElement<Component> {

    protected TextElement(Collection<? extends String> frames, int period) {
        super(new ArrayList<>(frames
                .stream()
                .map(t -> LegacyComponentSerializer.legacyAmpersand().deserialize(t))
                .collect(Collectors.toList())), period, false);
    }

    protected TextElement(Supplier<? extends String> supplier, int period) {
        super(() -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.get()), period, false);
    }

    protected TextElement(String text) {
        this(() -> text, -1);
        setUpdate(() -> {});
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public static TextElement simple(String text) {
        return new TextElement(text) {};
    }

    public static TextElement animated(Collection<? extends String> frames, int period) {
        return new TextElement(frames, period) {};
    }

    public static TextElement dynamic(Supplier<? extends String> supplier, int period) {
        return new TextElement(supplier, period) {};
    }
}
