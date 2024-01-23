package dev.acrispycookie.crispycommons.implementations.guis.books.actions;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class BookAction {

    private final UUID uuid;

    private static final Map<UUID, BookAction> pendingActions = new HashMap<>();
    public abstract void run(Player p);

    public BookAction() {
        uuid = getRandomUuid();
        pendingActions.put(uuid, this);
    }

    public void cancel() {
        pendingActions.remove(uuid);
    }

    public void performAction(Player p) {
        run(p);
        pendingActions.remove(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public static BookAction getPendingAction(UUID uuid) {
        return pendingActions.getOrDefault(uuid, null);
    }

    private UUID getRandomUuid() {
        UUID uuid = UUID.randomUUID();
        while (pendingActions.containsKey(uuid)) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

}
