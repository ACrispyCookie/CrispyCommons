package dev.acrispycookie.crispycommons.api.guis.inventory;

import com.google.gson.JsonObject;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

public interface CrispyInventoryBuilder {

    static RyseInventory.Builder builder() {
        return RyseInventory.builder();
    }
    static RyseInventory.Builder fromJson(JsonObject object) {
        return null;
    }
}
