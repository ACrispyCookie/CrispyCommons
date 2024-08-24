package dev.acrispycookie.crispycommons.utility.version;

public enum Version {
    v1_20_R3,
    v1_8_R3;

    public boolean isHigherOrEqual(Version compare) {
        return this.ordinal() >= compare.ordinal();
    }

    public boolean isLowerOrEqual(Version compare) {
        return this.ordinal() <= compare.ordinal();
    }
}
