package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractCrispyVisual<T> implements CrispyVisual<T> {

    protected boolean isDisplayed = false;

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
