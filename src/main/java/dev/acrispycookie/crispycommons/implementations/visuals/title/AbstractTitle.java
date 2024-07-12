package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.title.CrispyTitle;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import org.bukkit.OfflinePlayer;
import java.util.Set;

public abstract class AbstractTitle extends AbstractVisual<TitleData> implements CrispyTitle {

    AbstractTitle(TitleData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getTitle().start();
        data.getSubtitle().start();
    }

    @Override
    protected void prepareHide() {
        data.getTitle().stop();
        data.getSubtitle().stop();
    }

    @Override
    public void setTitle(TextElement<?> text) {
        data.getTitle().stop();
        data.setTitle(text);
        data.getTitle().setUpdate(this::update);
        if (isDisplayed) {
            data.getTitle().start();
            update();
        }
    }

    @Override
    public void setSubtitle(TextElement<?> text) {
        data.getSubtitle().stop();
        data.setSubtitle(text);
        data.getSubtitle().setUpdate(this::update);
        if (isDisplayed) {
            data.getSubtitle().start();
            update();
        }
    }

    @Override
    public void setFadeIn(GeneralElement<Integer, ?> fadeIn) {
        data.setFadeIn(fadeIn);
    }

    @Override
    public void setFadeOut(GeneralElement<Integer, ?> fadeOut) {
        data.setFadeOut(fadeOut);
    }

    @Override
    public TextElement<?> getTitle() {
        return this.data.getTitle();
    }

    @Override
    public TextElement<?> getSubtitle() {
        return this.data.getSubtitle();
    }

    @Override
    public GeneralElement<Integer, ?> getFadeIn() {
        return this.data.getFadeIn();
    }

    @Override
    public GeneralElement<Integer, ?> getFadeOut() {
        return this.data.getFadeOut();
    }
}
