package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public abstract class ItemElement extends AnimatedElement<CrispyItemStack> {

    public ItemElement(Map<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        super(frames, period, false);
    }

    public ItemElement(Function<OfflinePlayer, CrispyItemStack> supplier, int period) {
        super(supplier, period, false);
    }

    public ItemElement(Map<OfflinePlayer, CrispyItemStack> items) {
        this(items::get, -1);
        setUpdate(() -> {});
    }

    @Override
    public ItemElement clone() {
        if(isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(this.getRaw());
    }
    public static ItemElement simple(Map<OfflinePlayer, CrispyItemStack> items) {
        return new ItemElement(items) {};
    }

    public static ItemElement animated(Map<OfflinePlayer, Collection<? extends CrispyItemStack>> frames, int period) {
        return new ItemElement(frames, period) {};
    }

    public static ItemElement dynamic(Function<OfflinePlayer, CrispyItemStack> supplier, int period) {
        return new ItemElement(supplier, period) {};
    }
}
