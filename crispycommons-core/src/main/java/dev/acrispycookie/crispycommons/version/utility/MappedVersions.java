package dev.acrispycookie.crispycommons.version.utility;

import java.util.HashMap;

public class MappedVersions {

    private final HashMap<Version, Version> mapped = new HashMap<>();

    public MappedVersions() {

    }

    public MappedVersions(VersionPair... pairs) {
        for (VersionPair pair : pairs) {
            mapped.put(pair.getOriginal(), pair.getRemapped());
        }
    }

    public boolean hasVersion(Version original) {
        return mapped.containsKey(original);
    }

    public Version getRemapped(Version original) {
        return mapped.get(original);
    }
}
