package dev.acrispycookie.crispycommons;

public class CommonsSettings {

    private boolean bookActionEnabled;
    private String bookCommand;
    private boolean menusEnabled;

    public CommonsSettings(boolean bookActionEnabled, String bookCommand, boolean menusEnabled) {
        this.bookActionEnabled = bookActionEnabled;
        this.bookCommand = bookCommand;
        this.menusEnabled = menusEnabled;
    }

    public CommonsSettings() {

    }

    public CommonsSettings bookAction(String bookCommand) {
        this.bookActionEnabled = bookCommand != null;
        this.bookCommand = bookCommand;
        return this;
    }

    public CommonsSettings setMenus(boolean enabled) {
        this.menusEnabled = enabled;
        return this;
    }

    public boolean isBookActionEnabled() {
        return bookActionEnabled;
    }

    public String getBookCommand() {
        return bookCommand;
    }

    public boolean isMenusEnabled() {
        return menusEnabled;
    }
}
