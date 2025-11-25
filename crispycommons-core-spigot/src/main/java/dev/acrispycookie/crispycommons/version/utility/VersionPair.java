package dev.acrispycookie.crispycommons.version.utility;

import org.jetbrains.annotations.NotNull;

public class VersionPair {

    private final Version original;
    private final Version remapped;

    public VersionPair(@NotNull Version original, @NotNull Version remapped) {
        this.original = original;
        this.remapped = remapped;
    }

    public @NotNull Version getOriginal() {
        return original;
    }

    public @NotNull Version getRemapped() {
        return remapped;
    }
}
