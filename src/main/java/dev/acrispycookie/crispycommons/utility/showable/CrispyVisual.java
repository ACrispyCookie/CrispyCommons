package dev.acrispycookie.crispycommons.utility.showable;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public interface CrispyVisual<T> {

    void show();
    void hide();
    void update();
    boolean isDisplayed();
    T getCurrentContent();

}
