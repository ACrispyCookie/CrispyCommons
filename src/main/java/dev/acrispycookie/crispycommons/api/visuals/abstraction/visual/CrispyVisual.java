package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

public interface CrispyVisual<T extends VisualData> {

    boolean isDisplayed();
    T getData();

}
