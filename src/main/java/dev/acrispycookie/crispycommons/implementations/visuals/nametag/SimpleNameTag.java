package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.nametag.AbstractNametag;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleNameTag extends AbstractNametag {
    public SimpleNameTag(NameTagData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }
}
