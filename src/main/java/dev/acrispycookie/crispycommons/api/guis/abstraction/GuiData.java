package dev.acrispycookie.crispycommons.api.guis.abstraction;

public interface GuiData {

    void assertReady();

    class GuiNotReadyException extends RuntimeException {
        public GuiNotReadyException(String message) {
            super(message);
        }
    }
}
