package dev.acrispycookie.crispycommons.utility.visual;

public abstract class AbstractCrispyVisual<T> implements CrispyVisual<T> {

    protected boolean isDisplayed = false;
    protected T content;

    public AbstractCrispyVisual(T content) {
        this.content = content;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
