package dev.acrispycookie.crispycommons;

public class CommonsSettings {

    private boolean bookActionEnabled;
    private String bookCommand;

    public CommonsSettings(boolean bookActionEnabled, String bookCommand) {
        this.bookActionEnabled = bookActionEnabled;
        this.bookCommand = bookCommand;
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
}
