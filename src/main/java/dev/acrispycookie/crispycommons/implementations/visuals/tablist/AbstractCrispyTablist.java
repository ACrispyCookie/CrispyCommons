package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class AbstractCrispyTablist extends AbstractCrispyAccessibleVisual<List<TextElement>> implements CrispyTablist {

    public AbstractCrispyTablist(List<TextElement> content, Set<? extends Player> receivers) {
        super(content, receivers);
    }

    @Override
    public void setHeader(TextElement element) {

    }

    @Override
    public void setFooter(TextElement element) {

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
