package dev.acrispycookie.crispycommons.version.utility;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MappedVersions {

    private final HashMap<Version, Version> mapped = new HashMap<>();

    public MappedVersions() {

    }

    public MappedVersions(@NotNull VersionPair... pairs) {
        for (VersionPair pair : pairs) {
            mapped.put(pair.getOriginal(), pair.getRemapped());
        }
    }

    public boolean hasVersion(@NotNull Version original) {
        return mapped.containsKey(original);
    }

    public @NotNull Version getRemapped(@NotNull Version original) {
        return mapped.get(original);
    }
}
