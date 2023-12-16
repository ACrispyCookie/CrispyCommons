package dev.acrispycookie.crispycommons.text;


import java.util.ArrayList;

public abstract class CrispyText {

    protected ArrayList<String> currentText;
    public abstract String getCurrentText();
    public abstract ArrayList<String> getCurrentLines();
}
