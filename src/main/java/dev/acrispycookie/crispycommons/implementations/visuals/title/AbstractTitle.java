package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.title.CrispyTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractTitle extends AbstractVisual<TitleData> implements CrispyTitle {

    protected long timeStarted;

    AbstractTitle(TitleData data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        timeStarted = System.currentTimeMillis();
        data.getTitle().start();
        data.getSubtitle().start();
    }

    @Override
    protected void prepareHide() {
        data.getTitle().stop();
        data.getSubtitle().stop();
    }

    @Override
    public void setTitle(GlobalTextElement text) {
        this.data.setTitle(text);
    }

    @Override
    public void setSubtitle(GlobalTextElement text) {
        this.data.setSubtitle(text);
    }

    @Override
    public void setFadeIn(int fadeIn) {
        this.data.setFadeIn(fadeIn);
    }

    @Override
    public void setFadeOut(int fadeOut) {
        this.data.setFadeOut(fadeOut);
    }

    @Override
    public GlobalTextElement getTitle() {
        return this.data.getTitle();
    }

    @Override
    public GlobalTextElement getSubtitle() {
        return this.data.getSubtitle();
    }

    @Override
    public int getFadeIn() {
        return this.data.getFadeIn();
    }

    @Override
    public int getFadeOut() {
        return this.data.getFadeOut();
    }
}
