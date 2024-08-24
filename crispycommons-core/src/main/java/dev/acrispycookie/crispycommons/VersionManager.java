package dev.acrispycookie.crispycommons;

import dev.acrispycookie.crispycommons.nms.utility.*;
import dev.acrispycookie.crispycommons.nms.entity.spigot.*;
import dev.acrispycookie.crispycommons.nms.entity.custom.*;
import dev.acrispycookie.crispycommons.nms.nbt.*;
import dev.acrispycookie.crispycommons.nms.visuals.*;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class VersionManager {

    private static VersionManager instance;
    private final String nmsVersion;
    private Map<Class<?>, Class<?>> mappedWrappers;

    public VersionManager() throws ClassNotFoundException {
        instance = this;
        nmsVersion = getNMSVersion();
        //mappedWrappers = setupMappings();
    }

    public static VersionManager get() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T createInstance(Class<T> clazz, ArgPair<?>... args) {
        try {
            String packageName = clazz.getPackage().getName();
            if (!packageName.contains("nms"))
                return null;

            Class<?> versionSpecific = Class.forName(getVersionSpecificName(clazz));
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

    private HashMap<Class<?>, Class<?>> setupMappings() throws ClassNotFoundException {
        HashMap<Class<?>, Class<?>> map = new HashMap<>();
        for (WrapperClass w : WrapperClass.values()) {
            String className = w.getWrapperClass().getSimpleName();
            String packageName = w.getPackageName();
            String versionSpecificName = "dev.acrispycookie.crispycommons.nms."
                    + nmsVersion + "." + packageName + "." + className + "_" + nmsVersion.substring(1);
            map.put(w.getWrapperClass(), Class.forName(versionSpecificName));
        }
        return map;
    }

    private String getVersionSpecificName(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        int index = packageName.indexOf("nms.") + 4;
        String versionSpecificPackage = packageName.substring(0, index - 1) + "." + getNMSVersion() + "." + packageName.substring(index) + ".";

        return versionSpecificPackage + clazz.getSimpleName() + "_" + getNMSVersion().substring(1);
    }

    private String getNMSVersion(){
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return v.substring(v.lastIndexOf('.') + 1);
    }

    private enum WrapperClass {
        CommandRegister(CommandRegister.class, "utility"),
        ParticleSpawner(ParticleSpawner.class, "utility"),
        ItemMetaEditor(ItemMetaEditor.class, "utility"),
        VersionEntity(VersionEntity.class, "entity.spigot"),
        VersionArmorStand(VersionArmorStand.class, "entity.spigot"),
        VersionItem(VersionItem.class, "entity.spigot"),
        CustomText(CustomText.class, "entity.custom"),
        CustomItem(CustomItem.class, "entity.custom"),
        ItemStackNBT(ItemStackNBT.class, "nbt"),
        BaseTag(BaseTag.class, "nbt"),
        ByteArrayTag(ByteArrayTag.class, "nbt"),
        ByteTag(ByteTag.class, "nbt"),
        CompoundTag(CompoundTag.class, "nbt"),
        DoubleTag(DoubleTag.class, "nbt"),
        FloatTag(FloatTag.class, "nbt"),
        IntArrayTag(IntArrayTag.class, "nbt"),
        IntTag(IntTag.class, "nbt"),
        ListTag(ListTag.class, "nbt"),
        LongTag(LongTag.class, "nbt"),
        ShortTag(ShortTag.class, "nbt"),
        StringTag(StringTag.class, "nbt"),
        VersionScoreboard(VersionScoreboard.class, "visuals");

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
