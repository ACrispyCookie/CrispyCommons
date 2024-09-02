package dev.acrispycookie.crispycommons.version.utility;

import org.jetbrains.annotations.NotNull;

public enum Version {
    v1_20_R3,
    v1_8_R3;

    public boolean isHigherOrEqual(@NotNull Version compare) {
        return this.ordinal() >= compare.ordinal();
    }

    public boolean isHigher(@NotNull Version compare) {
        return this.ordinal() > compare.ordinal();
    }

    public boolean isLowerOrEqual(@NotNull Version compare) {
        return this.ordinal() <= compare.ordinal();
    }

    public boolean isLower(@NotNull Version compare) {
        return this.ordinal() < compare.ordinal();
    }
}
