package dev.acrispycookie.crispycommons.holograms.text;


import java.util.ArrayList;

public abstract class HologramText {

    protected ArrayList<String> currentText;
    public abstract String getCurrentText();
    public abstract ArrayList<String> getCurrentLines();
}
