package dev.acrispycookie.crispycommons;

public class CommonsSettings {

    private boolean bookActionEnabled;
    private String bookCommand;
    private boolean menusEnabled;
    private int maximumMenuHistory;

    public CommonsSettings(boolean bookActionEnabled, String bookCommand, boolean menusEnabled, int maximumMenuHistory) {
        this.bookActionEnabled = bookActionEnabled;
        this.bookCommand = bookCommand;
        this.menusEnabled = menusEnabled;
        this.maximumMenuHistory = maximumMenuHistory;
    }

    public CommonsSettings() {
        this.bookActionEnabled = false;
        this.menusEnabled = true;
        this.maximumMenuHistory = 10;
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

    public CommonsSettings setMaximumMenuHistory(int maximumMenuHistory) {
        this.maximumMenuHistory = maximumMenuHistory;
        return this;
    }

    public boolean isBookActionEnabled() {
        return bookActionEnabled;
    }

    public String getBookCommand() {
        return bookCommand;
    }

    public int getMaximumMenuHistory() {
        return maximumMenuHistory;
    }

    public boolean isMenusEnabled() {
        return menusEnabled;
    }
}
