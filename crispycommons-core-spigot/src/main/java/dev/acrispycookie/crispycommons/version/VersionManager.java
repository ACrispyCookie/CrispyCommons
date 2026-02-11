package dev.acrispycookie.crispycommons.version;

import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import dev.acrispycookie.crispycommons.version.utility.Version;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Version-specific implementation of the class " + clazz.getCanonicalName() + " was not found! Contact developer.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull String getVersionSpecificName(@NotNull Class<?> clazz, @NotNull MappedVersions versions) {
        String packageName = clazz.getPackage().getName();
        int index = packageName.indexOf("crispycommons.") + 14;
        Version toUse = versions.hasVersion(nmsVersion) ? versions.getRemapped(nmsVersion) : nmsVersion;
        String packageSubname = toUse == Version.PAPER ? toUse.name().toLowerCase() : toUse.name();
        String classSubname = toUse == Version.PAPER ? "Paper" : toUse.name().substring(1);
        String versionSpecificPackage = packageName.substring(0, index - 1) + "." + packageSubname + "." + packageName.substring(index) + ".";

        return versionSpecificPackage + clazz.getSimpleName() + "_" + classSubname;
    }

    /**
     * returns true if the server is running 1.20.5 or later.
     */
    public static boolean isNewNMS() {
        String SERVER_VERSION = Bukkit.getVersion();
        String[] parts = SERVER_VERSION.split("\\.");
        int MAJOR_VERSION = (parts.length >= 2) ? Integer.parseInt(parts[1]) : 0;

        if (MAJOR_VERSION > 20) return true;
        if (MAJOR_VERSION == 20) {
            return parts.length >= 3 && Integer.parseInt(parts[2]) >= 5;
        }

        return false;
    }

    public static Version getNMSVersion() {
        if (isNewNMS()) {
            return Version.PAPER;
        } else {
            String packageName = Bukkit.getServer().getClass().getPackage().getName();
            String version = packageName.substring(packageName.lastIndexOf('.') + 1);
            return Version.valueOf(version);
        }
    }

}
