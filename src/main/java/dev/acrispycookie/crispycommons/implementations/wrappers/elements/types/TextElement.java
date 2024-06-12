package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class TextElement extends AnimatedElement<Component> {

    protected TextElement(Map<OfflinePlayer, Collection<? extends Component>> frames, int period) {
        super(frames, period, false);
    }

    protected TextElement(Function<OfflinePlayer, ? extends Component> supplier, int period) {
        super(supplier, period, false);
    }

    protected TextElement(Map<OfflinePlayer, Component> text) {
        this(text::get, -1);
        setUpdate(() -> {});
    }

    public TextElement add(TextElement element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
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

    public static TextElement simple(Map<OfflinePlayer, String> text) {
        return simpleComponent(LegacyComponentSerializer.legacyAmpersand().deserialize(text));
    }

    public static TextElement animated(Map<OfflinePlayer, Collection<? extends String>> frames, int period) {
        return animatedComponent(frames.stream().map(LegacyComponentSerializer.legacyAmpersand()::deserialize).collect(Collectors.toList()), period);
    }

    public static TextElement dynamic(Function<OfflinePlayer, ? extends String> supplier, int period) {
        return dynamicComponent((p) -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.apply(p)), period);
    }

    public static TextElement simpleComponent(Map<OfflinePlayer, Component> text) {
        return new TextElement(text) {};
    }

    public static TextElement animatedComponent(Map<OfflinePlayer, Collection<? extends Component>> frames, int period) {
        return new TextElement(frames, period) {};
    }

    public static TextElement dynamicComponent(Function<OfflinePlayer, ? extends Component> supplier, int period) {
        return new TextElement(supplier, period) {};
    }
}
