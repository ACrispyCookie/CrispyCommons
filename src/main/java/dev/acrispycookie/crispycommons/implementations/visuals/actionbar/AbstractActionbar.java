package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractActionbar extends AbstractVisual<ActionbarData> implements CrispyActionbar {


    AbstractActionbar(ActionbarData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getText().start();
    }

    @Override
    protected void prepareHide() {
        data.getText().stop();
    }

    @Override
    public void setText(TextElement text) {
        data.getText().stop();
        data.setText(text);
        data.getText().setUpdate(this::update);
        if (isDisplayed) {
            data.getText().start();
            update();
        }
    }

    @Override
    public TextElement getText() {
        return data.getText();
    }
}
