package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ItemElement;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public abstract class PersonalItemElement extends PersonalAnimatedElement<CrispyItemStack> implements ItemElement {

    public PersonalItemElement(Map<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        super(frames, period, false);
    }

    public PersonalItemElement(Function<OfflinePlayer, CrispyItemStack> supplier, int period) {
        super(supplier, period, false);
    }

    public PersonalItemElement(Map<OfflinePlayer, CrispyItemStack> items) {
        this(items::get, -1);
        setUpdate(() -> {});
    }

    @Override
    public PersonalItemElement clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(getAllPlayers());
    }
    public static PersonalItemElement simple(Map<OfflinePlayer, CrispyItemStack> items) {
        return new PersonalItemElement(items) {};
    }

    public static PersonalItemElement animated(Map<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        return new PersonalItemElement(frames, period) {};
    }

    public static PersonalItemElement dynamic(Function<OfflinePlayer, CrispyItemStack> supplier, int period) {
        return new PersonalItemElement(supplier, period) {};
    }
}
