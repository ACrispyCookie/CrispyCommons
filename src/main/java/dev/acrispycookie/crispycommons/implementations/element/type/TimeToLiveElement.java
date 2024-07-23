package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.function.Function;

public class TimeToLiveElement<K> extends AbstractAnimatedElement<Long, K> {
    
    private final StartMode startMode;
    private long startTimestamp;

    protected TimeToLiveElement(MyElementSupplier<K, Long> supplier, Class<K> kClass, StartMode startMode) {
        super(supplier, -1, -1, false, kClass);
        this.startMode = startMode;
        setUpdate(() -> {});
    }

    public StartMode getStartMode() {
        return startMode;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    @Override
    public TimeToLiveElement<K> clone() {
        return new TimeToLiveElement<>(new MyElementSupplier<>(this::getRaw), getContextClass(), getStartMode());
    }

    public static TimeToLiveElement<Void> simple(Long value, StartMode startMode) {
        return new TimeToLiveElement<>(new MyElementSupplier<>((v) -> value), Void.class, startMode);
    }

    public static TimeToLiveElement<OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends Long> function, StartMode startMode) {
        return new TimeToLiveElement<>(new MyElementSupplier<>(function), OfflinePlayer.class, startMode);
    }

    public enum StartMode {
        GLOBAL,
        PER_PLAYER
    }
}
