package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.title.CrispyTItle;
import dev.acrispycookie.crispycommons.utility.title.TitleType;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.Set;

public class AbstractCrispyTItle extends AbstractCrispyAccessibleVisual<String> implements CrispyTItle {

    private final TitleType type;

    public AbstractCrispyTItle(TitleType type, String text, Set<? extends Player> receivers) {
        super(text, receivers);
        this.type = type;
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void setFadeIn(int fadeIn) {

    }

    @Override
    public void setDuration(int duration) {

    }

    @Override
    public void setFadeOut(int fadeOut) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void update() {

    }
}
