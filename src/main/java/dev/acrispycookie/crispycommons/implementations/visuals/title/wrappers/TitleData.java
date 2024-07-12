package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TitleData implements VisualData {

    private TextElement<?> title;
    private TextElement<?> subtitle;
    private GeneralElement<Integer, ?> fadeIn;
    private GeneralElement<Integer, ?> fadeOut;
    private int smallestPeriod;

    public TitleData(TextElement<?> title, TextElement<?> subtitle, GeneralElement<Integer, ?> fadeIn, GeneralElement<Integer, ?> fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
    }

    public TextElement<?> getTitle() {
        return title;
    }

    public TextElement<?> getSubtitle() {
        return subtitle;
    }

    public GeneralElement<Integer, ?> getFadeIn() {
        return fadeIn;
    }

    public GeneralElement<Integer, ?> getFadeOut() {
        return fadeOut;
    }

    public void setTitle(TextElement<?> title) {
        this.title = title;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    public void setSubtitle(TextElement<?> subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    public void setFadeIn(GeneralElement<Integer, ?> fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setFadeOut(GeneralElement<Integer, ?> fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getSmallestPeriod() {
        return smallestPeriod;
    }

    @Override
    public void assertReady(Player player) {
        if (title.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The title was not set!");
        if (subtitle.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The subtitle was not set!");
        if (fadeIn.isDynamic())
            throw new VisualNotReadyException("The fade in cannot be dynamic!");
        if (fadeOut.isDynamic())
            throw new VisualNotReadyException("The fade out cannot be dynamic!");
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }
}
