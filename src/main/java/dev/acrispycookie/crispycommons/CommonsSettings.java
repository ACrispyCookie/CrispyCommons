package dev.acrispycookie.crispycommons;

public class CommonsSettings {

    private boolean bookActionEnabled;
    private String bookCommand;
    private boolean inventoriesEnabled;

    public CommonsSettings(boolean bookActionEnabled, String bookCommand, boolean inventoriesEnabled) {
        this.bookActionEnabled = bookActionEnabled;
        this.bookCommand = bookCommand;
        this.inventoriesEnabled = inventoriesEnabled;
    }

    public CommonsSettings() {

    }

    public CommonsSettings bookAction(String bookCommand) {
        this.bookActionEnabled = bookCommand != null;
        this.bookCommand = bookCommand;
        return this;
    }

    public boolean isBookActionEnabled() {
        return bookActionEnabled;
    }

    public String getBookCommand() {
        return bookCommand;
    }

    public boolean isInventoriesEnabled() {
        return inventoriesEnabled;
    }
}
