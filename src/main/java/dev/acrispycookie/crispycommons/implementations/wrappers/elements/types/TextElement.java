package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.DynamicElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class TextElement extends AnimatedElement<Component> {

    protected TextElement(Collection<? extends Component> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    protected TextElement(Supplier<? extends Component> supplier, int period) {
        super(supplier, period, false);
    }

    protected TextElement(Component text) {
        this(() -> text, -1);
        setUpdate(() -> {});
    }

    public TextElement add(TextElement element) {
        int newPeriod = DynamicElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return TextElement.simple(LegacyComponentSerializer.legacySection().serialize(getRaw().append(element.getRaw())));
        } else {
            return TextElement.dynamic(() -> LegacyComponentSerializer.legacySection().serialize(getRaw().append(element.getRaw())), newPeriod);
        }
    }

    @Override
    public TextElement clone() {
        if (isDynamic())
            return TextElement.dynamic(() -> LegacyComponentSerializer.legacySection().serialize(getRaw()), getPeriod());
        return TextElement.simple(LegacyComponentSerializer.legacySection().serialize(getRaw()));
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public static TextElement simple(String text) {
        return simpleComponent(LegacyComponentSerializer.legacyAmpersand().deserialize(text));
    }

    public static TextElement animated(Collection<? extends String> frames, int period) {
        return animatedComponent(frames.stream().map(LegacyComponentSerializer.legacyAmpersand()::deserialize).collect(Collectors.toList()), period);
    }

    public static TextElement dynamic(Supplier<? extends String> supplier, int period) {

        return dynamicComponent(() -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.get()), period);
    }

    public static TextElement simpleComponent(Component text) {
        return new TextElement(text) {};
    }

    public static TextElement animatedComponent(Collection<? extends Component> frames, int period) {
        return new TextElement(frames, period) {};
    }

    public static TextElement dynamicComponent(Supplier<? extends Component> supplier, int period) {
        return new TextElement(supplier, period) {};
    }
}
