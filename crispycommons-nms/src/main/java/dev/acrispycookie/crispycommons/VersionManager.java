package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.nms.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.nms.wrappers.entity.EntityArmorStand;
import dev.acrispycookie.crispycommons.nms.wrappers.entity.EntityItem;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class VersionManager {

    private static VersionManager instance;
    private final String nmsVersion;
    private final Map<Class<?>, Class<?>> mappedWrappers;

    public VersionManager() throws ClassNotFoundException {
        instance = this;
        nmsVersion = getNMSVersion();
        mappedWrappers = setupMappings();
    }

    public static VersionManager get() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T createInstance(Class<T> clazz) {
        try {
            Class<?> versionSpecific = mappedWrappers.get(clazz);
            return (T) versionSpecific.getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<Class<?>, Class<?>> setupMappings() throws ClassNotFoundException {
        HashMap<Class<?>, Class<?>> map = new HashMap<>();
        for (WrapperClass w : WrapperClass.values()) {
            String className = w.getWrapperClass().getName();
            String packageName = w.getPackageName();
            String versionSpecificName = "dev.acrispycookie.crispycommons.nms."
                    + nmsVersion + "." + packageName + "." + className + "_" + nmsVersion.substring(1);
            map.put(w.getWrapperClass(), Class.forName(versionSpecificName));
        }
        return map;
    }

    private String getNMSVersion(){
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return v.substring(v.lastIndexOf('.') + 1);
    }

    private boolean biggerThanOrEqual(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length; i++) {
            int a = Integer.parseInt(v1[i]);
            int b = Integer.parseInt(v2[i]);
            if (a > b) {
                return true;
            }
            if (a < b) {
                return false;
            }
        }
        return true;
    }

    private enum WrapperClass {
        CommandRegister(dev.acrispycookie.crispycommons.nms.wrappers.utilities.CommandRegister.class, "utility"),
        ParticleSpawner(dev.acrispycookie.crispycommons.nms.wrappers.utilities.ParticleSpawner.class, "utility"),
        ItemMetaEditor(dev.acrispycookie.crispycommons.nms.wrappers.utilities.ItemMetaEditor.class, "utility"),
        WrapperEntity(Entity.class, "entity"),
        WrapperEntityArmorStand(EntityArmorStand.class, "entity"),
        WrapperEntityItem(EntityItem.class, "entity"),
        ItemStackNBT(dev.acrispycookie.crispycommons.nms.wrappers.nbt.ItemStackNBT.class, "nbt"),
        WrapperNBTBase(NBTBase.class, "nbt");

        private final Class<?> wrapperClass;
        private final String packageName;

        WrapperClass(Class<?> wrapperClass, String packageName) {
            this.wrapperClass = wrapperClass;
            this.packageName = packageName;
        }

        public Class<?> getWrapperClass() {
            return wrapperClass;
        }

        public String getPackageName() {
            return packageName;
        }
    }
}
