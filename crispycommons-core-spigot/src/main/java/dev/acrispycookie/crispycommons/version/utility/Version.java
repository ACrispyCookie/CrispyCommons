package dev.acrispycookie.crispycommons.version.utility;

import org.jetbrains.annotations.NotNull;

public enum Version {
    PAPER, // 1.20.5 and later
    v1_21_R4, // 1.21.5
    v1_20_R3, // 1.20.4
    v1_8_R3; // 1.8.8

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
