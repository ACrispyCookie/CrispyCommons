package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class PersonalTextElement extends PersonalAnimatedElement<Component> implements TextElement {

    protected PersonalTextElement(Map<OfflinePlayer, Collection<? extends Component>> frames, int period) {
        super(frames, period, false);
    }

    protected PersonalTextElement(Function<OfflinePlayer, ? extends Component> supplier, int period) {
        super(supplier, period, false);
    }

    protected PersonalTextElement(Map<OfflinePlayer, Component> text) {
        this(text::get, -1);
        setUpdate(() -> {});
    }

    public PersonalTextElement add(PersonalTextElement element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return PersonalTextElement.simpleComponent(element.getAllPlayers());
        } else {
            return PersonalTextElement.dynamicComponent((p) -> getRaw(p).append(element.getRaw(p)), newPeriod);
        }
    }

    @Override
    public PersonalTextElement clone() {
        if (isDynamic())
            return PersonalTextElement.dynamicComponent(this::getRaw, getPeriod());
        return PersonalTextElement.simpleComponent(getAllPlayers());
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public static PersonalTextElement simple(Map<OfflinePlayer, String> text) {
        Map<OfflinePlayer, Component> mapped = new HashMap<>();
        text.forEach((p, s) -> {
            mapped.put(p, LegacyComponentSerializer.legacyAmpersand().deserialize(s));
        });
        return simpleComponent(mapped);
    }

    public static PersonalTextElement animated(Map<OfflinePlayer, Collection<? extends String>> frames, int period) {
        Map<OfflinePlayer, Collection<? extends Component>> mapped = new HashMap<>();
        frames.forEach((p, c) -> {
            List<Component> mappedList = c.stream().map((s) -> LegacyComponentSerializer.legacyAmpersand().deserialize(s)).collect(Collectors.toList());
            mapped.put(p, mappedList);
        });
        return animatedComponent(mapped, period);
    }

    public static PersonalTextElement dynamic(Function<OfflinePlayer, ? extends String> supplier, int period) {
        return dynamicComponent((p) -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.apply(p)), period);
    }

    public static PersonalTextElement simpleComponent(Map<OfflinePlayer, Component> text) {
        return new PersonalTextElement(text) {};
    }

    public static PersonalTextElement animatedComponent(Map<OfflinePlayer, Collection<? extends Component>> frames, int period) {
        return new PersonalTextElement(frames, period) {};
    }

    public static PersonalTextElement dynamicComponent(Function<OfflinePlayer, ? extends Component> supplier, int period) {
        return new PersonalTextElement(supplier, period) {};
    }
}
