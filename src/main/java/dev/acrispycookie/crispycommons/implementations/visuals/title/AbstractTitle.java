package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.title.CrispyTitle;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractTitle extends AbstractVisual<TitleData> implements CrispyTitle {

    AbstractTitle(TitleData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive, UpdateMode updateMode) {
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
    public void setTitle(TextElement text) {
        this.data.setTitle(text);
    }

    @Override
    public void setSubtitle(TextElement text) {
        this.data.setSubtitle(text);
    }

    @Override
    public void setFadeIn(GeneralElement<Integer> fadeIn) {
        this.data.setFadeIn(fadeIn);
    }

    @Override
    public void setFadeOut(GeneralElement<Integer> fadeOut) {
        this.data.setFadeOut(fadeOut);
    }

    @Override
    public TextElement getTitle() {
        return this.data.getTitle();
    }

    @Override
    public TextElement getSubtitle() {
        return this.data.getSubtitle();
    }

    @Override
    public GeneralElement<Integer> getFadeIn() {
        return this.data.getFadeIn();
    }

    @Override
    public GeneralElement<Integer> getFadeOut() {
        return this.data.getFadeOut();
    }

    protected Component getTitle(Player player) {
        return data.getTitle() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getTitle()).getRaw() :
                ((PersonalTextElement) data.getTitle()).getRaw(player);
    }

    protected Component getSubtitle(Player player) {
        return data.getSubtitle() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getSubtitle()).getRaw() :
                ((PersonalTextElement) data.getSubtitle()).getRaw(player);
    }

    protected int getFadeIn(Player player) {
        return data.getFadeIn() instanceof GlobalGeneralElement ?
                ((GlobalGeneralElement<Integer>) data.getFadeIn()).getRaw() :
                ((PersonalGeneralElement<Integer>) data.getFadeIn()).getRaw(player);
    }

    protected long getTTL(Player player) {
        return timeToLive instanceof GlobalGeneralElement ?
                ((GlobalGeneralElement<Long>) timeToLive).getRaw() :
                ((PersonalGeneralElement<Long>) timeToLive).getRaw(player);
    }

    protected long getFadeOut(Player player) {
        return data.getFadeOut() instanceof GlobalGeneralElement ?
                ((GlobalGeneralElement<Integer>) data.getFadeOut()).getRaw() :
                ((PersonalGeneralElement<Integer>) data.getFadeOut()).getRaw(player);
    }
}
