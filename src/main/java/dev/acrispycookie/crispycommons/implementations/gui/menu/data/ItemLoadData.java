package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class ItemLoadData {

    private final ItemElement<?> display;
    private final ItemElement<?> alternativeDisplay;
    private final BiFunction<CrispyMenu, Player, Boolean> canSee;
    private final BiFunction<CrispyMenu, Player, Void> onClick;

    public ItemLoadData(@NotNull ItemElement<?> display, @NotNull ItemElement<?> alternativeDisplay, @NotNull BiFunction<CrispyMenu, Player, Boolean> canSee, @NotNull BiFunction<CrispyMenu, Player, Void> onClick) {
        this.display = display;
        this.alternativeDisplay = alternativeDisplay;
        this.canSee = canSee;
        this.onClick = onClick;
    }

    public ItemElement<?> getDisplay() {
        return display;
    }

    public ItemElement<?> getAlternativeDisplay() {
        return alternativeDisplay;
    }

    public BiFunction<CrispyMenu, Player, Boolean> getCanSee() {
        return canSee;
    }

    public BiFunction<CrispyMenu, Player, Void> getOnClick() {
        return onClick;
    }
}
