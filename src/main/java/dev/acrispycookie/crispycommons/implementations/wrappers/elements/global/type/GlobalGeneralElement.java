package dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalAnimatedElement;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class GlobalGeneralElement<T> extends GlobalAnimatedElement<T> implements GeneralElement<T> {

    protected GlobalGeneralElement(Collection<? extends T> frames, int period, boolean async) {
        super(frames, period, async);
    }

    protected GlobalGeneralElement(Supplier<? extends T> supplier, int period, boolean async) {
        super(supplier, period, async);
    }

    public GlobalGeneralElement(T t) {
        this(() -> t, -1, false);
        setUpdate(() -> {});
    }

    @Override
    public GlobalGeneralElement<T> clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod(), isAsync());
        return simple(this.getRaw());
    }
    public static <T> GlobalGeneralElement<T> simple(T item) {
        return new GlobalGeneralElement<T>(item) {};
    }

    public static <T> GlobalGeneralElement<T> animated(Collection<? extends T> frames, int period, boolean async) {
        return new GlobalGeneralElement<T>(frames, period, async) {};
    }

    public static <T> GlobalGeneralElement<T> dynamic(Supplier<T> supplier, int period, boolean async) {
        return new GlobalGeneralElement<T>(supplier, period, async) {};
    }
}
