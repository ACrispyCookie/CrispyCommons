package dev.acrispycookie.crispycommons.version;

import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import dev.acrispycookie.crispycommons.version.utility.Version;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VersionManager {

    private static final Version nmsVersion = getNMSVersion();

    public static @NotNull Version getVersion() {
        return nmsVersion;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createInstance(@NotNull Class<T> clazz, @NotNull MappedVersions versions, @NotNull ArgPair<?>... args) {
        try {
            if (clazz.isAssignableFrom(Versioned.class))
                throw new RuntimeException("Tried to create instance of non-versionable using version manager. Contact developer.");

            Class<?> versionSpecific = Class.forName(getVersionSpecificName(clazz, versions));
            Class<?>[] parameters = new Class[args.length];
            Object[] objectArgs = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                parameters[i] = args[i].getClazz();
                objectArgs[i] = args[i].getArg();
            }
            Constructor<?> constructor = versionSpecific.getDeclaredConstructor(parameters);
            return (T) constructor.newInstance(objectArgs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull String getVersionSpecificName(@NotNull Class<?> clazz, @NotNull MappedVersions versions) {
        String packageName = clazz.getPackage().getName();
        int index = packageName.indexOf("crispycommons.") + 14;
        Version toUse = versions.hasVersion(nmsVersion) ? versions.getRemapped(nmsVersion) : nmsVersion;
        String versionSpecificPackage = packageName.substring(0, index - 1) + "." + toUse.name() + "." + packageName.substring(index) + ".";

        return versionSpecificPackage + clazz.getSimpleName() + "_" + toUse.name().substring(1);
    }

    private static @NotNull Version getNMSVersion() {
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return Version.valueOf(v.substring(v.lastIndexOf('.') + 1));
    }

}
