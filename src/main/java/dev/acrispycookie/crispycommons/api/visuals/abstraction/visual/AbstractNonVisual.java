package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

public abstract class AbstractNonVisual<T extends VisualData> implements CrispyNonVisual<T> {

    protected boolean isDisplayed = false;
    protected T data;

    public AbstractNonVisual(T data) {
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
