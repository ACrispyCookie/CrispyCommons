package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual<T> {

    protected boolean isDisplayed = false;
    protected T data;

    public AbstractVisual(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }
}
