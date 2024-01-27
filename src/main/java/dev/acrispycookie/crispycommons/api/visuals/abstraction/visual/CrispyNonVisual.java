package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

public interface CrispyNonVisual<T extends VisualData> {

    boolean isDisplayed();
    T getData();

}
