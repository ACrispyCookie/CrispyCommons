package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class PersonalTextElement extends PersonalAnimatedElement<Component> implements TextElement {

    protected PersonalTextElement(Function<OfflinePlayer, Collection<? extends Component>> supplier, int period) {
        super(supplier, period, false);
    }

    protected PersonalTextElement(TSupplier<Component> supplier, int period) {
        super(supplier, period, false);
        if (period < 0)
            setUpdate(() -> {});
    }

    public PersonalTextElement add(PersonalTextElement element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return PersonalTextElement.simpleComponent((p) -> getRaw(p).append(element.getRaw(p)));
        } else {
            return PersonalTextElement.dynamicComponent((p) -> getRaw(p).append(element.getRaw(p)), newPeriod);
        }
    }

    @Override
    public PersonalTextElement clone() {
        if (isDynamic())
            return PersonalTextElement.dynamicComponent(this::getRaw, getPeriod());
        return PersonalTextElement.simpleComponent(this::getRaw);
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public static PersonalTextElement simple(Function<OfflinePlayer, ? extends String> text) {
        return simpleComponent((p) -> LegacyComponentSerializer.legacyAmpersand().deserialize(text.apply(p)));
    }

    public static PersonalTextElement animated(Function<OfflinePlayer, Collection<? extends String>> supplier, int period) {
        return animatedComponent((p) -> supplier.apply(p)
                .stream()
                .map(s -> LegacyComponentSerializer.legacyAmpersand().deserialize(s))
                .collect(Collectors.toList()), period);
    }

    public static PersonalTextElement dynamic(Function<OfflinePlayer, ? extends String> supplier, int period) {
        return dynamicComponent((p) -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.apply(p)), period);
    }

    public static PersonalTextElement simpleComponent(Function<OfflinePlayer, Component> text) {
        return new PersonalTextElement(new TSupplier<>(text), -1) {};
    }

    public static PersonalTextElement animatedComponent(Function<OfflinePlayer, Collection<? extends Component>> frames, int period) {
        return new PersonalTextElement(frames, period) {};
    }

    public static PersonalTextElement dynamicComponent(Function<OfflinePlayer, ? extends Component> supplier, int period) {
        return new PersonalTextElement(new TSupplier<>(supplier), period) {};
    }
}
