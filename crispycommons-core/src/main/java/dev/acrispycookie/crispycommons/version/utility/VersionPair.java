package dev.acrispycookie.crispycommons.version.utility;

public class VersionPair {

    private Version original;
    private Version remapped;

    public VersionPair(Version original, Version remapped) {
        this.original = original;
        this.remapped = remapped;
    }

    public Version getOriginal() {
        return original;
    }

    public Version getRemapped() {
        return remapped;
    }
}
