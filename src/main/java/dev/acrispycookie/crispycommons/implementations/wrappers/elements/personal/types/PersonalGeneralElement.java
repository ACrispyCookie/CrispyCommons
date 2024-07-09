package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;

public abstract class PersonalGeneralElement<T> extends PersonalAnimatedElement<T> implements GeneralElement<T> {

    protected PersonalGeneralElement(Function<OfflinePlayer, Collection<? extends T>> frames, int period, boolean async) {
        super(frames, period, async);
    }

    protected PersonalGeneralElement(TSupplier<T> supplier, int period, boolean async) {
        super(supplier, period, async);
    }

    @Override
    public PersonalGeneralElement<T> clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod(), isAsync());
        return simple(this::getRaw);
    }
    public static <T> PersonalGeneralElement<T> simple(Function<OfflinePlayer, T> elements) {
        return new PersonalGeneralElement<T>(new TSupplier<>(elements), -1, false) {};
    }

    public static <T> PersonalGeneralElement<T> animated(Function<OfflinePlayer, Collection<? extends T>> frames, int period, boolean async) {
        return new PersonalGeneralElement<T>(frames, period, async) {};
    }

    public static <T> PersonalGeneralElement<T> dynamic(Function<OfflinePlayer, T> supplier, int period, boolean async) {
        return new PersonalGeneralElement<T>(new TSupplier<>(supplier), period, async) {};
    }
}
