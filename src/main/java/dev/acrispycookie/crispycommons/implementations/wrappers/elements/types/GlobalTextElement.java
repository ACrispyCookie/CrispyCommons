package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.GlobalAnimatedElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class GlobalTextElement extends GlobalAnimatedElement<Component> {

    protected GlobalTextElement(Collection<? extends Component> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    protected GlobalTextElement(Supplier<? extends Component> supplier, int period) {
        super(supplier, period, false);
    }

    protected GlobalTextElement(Component text) {
        this(() -> text, -1);
        setUpdate(() -> {});
    }

    public GlobalTextElement add(GlobalTextElement element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return GlobalTextElement.simple(LegacyComponentSerializer.legacySection().serialize(getRaw().append(element.getRaw())));
        } else {
            return GlobalTextElement.dynamic(() -> LegacyComponentSerializer.legacySection().serialize(getRaw().append(element.getRaw())), newPeriod);
        }
    }

    @Override
    public GlobalTextElement clone() {
        if (isDynamic())
            return GlobalTextElement.dynamic(() -> LegacyComponentSerializer.legacySection().serialize(getRaw()), getPeriod());
        return GlobalTextElement.simple(LegacyComponentSerializer.legacySection().serialize(getRaw()));
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public static GlobalTextElement simple(String text) {
        return simpleComponent(LegacyComponentSerializer.legacyAmpersand().deserialize(text));
    }

    public static GlobalTextElement animated(Collection<? extends String> frames, int period) {
        return animatedComponent(frames.stream().map(LegacyComponentSerializer.legacyAmpersand()::deserialize).collect(Collectors.toList()), period);
    }

    public static GlobalTextElement dynamic(Supplier<? extends String> supplier, int period) {

        return dynamicComponent(() -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.get()), period);
    }

    public static GlobalTextElement simpleComponent(Component text) {
        return new GlobalTextElement(text) {};
    }

    public static GlobalTextElement animatedComponent(Collection<? extends Component> frames, int period) {
        return new GlobalTextElement(frames, period) {};
    }

    public static GlobalTextElement dynamicComponent(Supplier<? extends Component> supplier, int period) {
        return new GlobalTextElement(supplier, period) {};
    }
}
