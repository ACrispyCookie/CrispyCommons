package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import dev.acrispycookie.crispycommons.utility.version.Version;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VersionManager {

    private static VersionManager instance;
    private final Version nmsVersion;

    public VersionManager() throws ClassNotFoundException {
        instance = this;
        nmsVersion = getNMSVersion();
    }

    public static VersionManager get() {
        return instance;
    }

    public Version getVersion() {
        return nmsVersion;
    }

    @SuppressWarnings("unchecked")
    public <T> T createInstance(Class<T> clazz, MappedVersions versions, ArgPair<?>... args) {
        try {
            String packageName = clazz.getPackage().getName();
            if (!packageName.contains("nms"))
                return null;

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

    private String getVersionSpecificName(Class<?> clazz, MappedVersions versions) {
        String packageName = clazz.getPackage().getName();
        int index = packageName.indexOf("nms.") + 4;
        Version toUse = versions.hasVersion(nmsVersion) ? versions.getRemapped(nmsVersion) : nmsVersion;
        String versionSpecificPackage = packageName.substring(0, index - 1) + "." + toUse.name() + "." + packageName.substring(index) + ".";

        return versionSpecificPackage + clazz.getSimpleName() + "_" + toUse.name().substring(1);
    }

    private Version getNMSVersion(){
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return Version.valueOf(v.substring(v.lastIndexOf('.') + 1));
    }

}
