package dev.acrispycookie.crispycommons.utility.visual;

public abstract class AbstractCrispyVisual<T> implements CrispyVisual<T> {

    protected boolean isDisplayed = false;

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
