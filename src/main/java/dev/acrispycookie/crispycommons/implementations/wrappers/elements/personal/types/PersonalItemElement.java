package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;

public abstract class PersonalItemElement extends PersonalAnimatedElement<CrispyItemStack> implements ItemElement {

    public PersonalItemElement(Function<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        super(frames, period, false);
    }

    public PersonalItemElement(TSupplier<CrispyItemStack> supplier, int period) {
        super(supplier, period, false);
        if (period < 0)
            setUpdate(() -> {});
    }

    @Override
    public PersonalItemElement clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(this::getRaw);
    }
    public static PersonalItemElement simple(Function<OfflinePlayer, CrispyItemStack> items) {
        return new PersonalItemElement(new TSupplier<>(items), -1) {};
    }

    public static PersonalItemElement animated(Function<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        return new PersonalItemElement(frames, period) {};
    }

    public static PersonalItemElement dynamic(Function<OfflinePlayer, CrispyItemStack> supplier, int period) {
        return new PersonalItemElement(new TSupplier<>(supplier), period) {};
    }
}
