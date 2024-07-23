package dev.acrispycookie.crispycommons.implementations.visual.actionbar;

import dev.acrispycookie.crispycommons.api.visual.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.data.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractActionbar extends AbstractVisual<ActionbarData> implements CrispyActionbar {


    AbstractActionbar(ActionbarData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
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
    public void setText(TextElement<?> text) {
        data.getText().stop();
        data.setText(text);
        data.getText().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getText().start();
            update();
        }
    }

    @Override
    public TextElement<?> getText() {
        return data.getText();
    }
}
