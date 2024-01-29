package dev.acrispycookie.crispycommons.api.visuals.abstraction.visual;

public interface VisualData {

    void assertReady();

    class VisualNotReadyException extends RuntimeException {
        public VisualNotReadyException(String message) {
            super(message);
        }
    }
}
