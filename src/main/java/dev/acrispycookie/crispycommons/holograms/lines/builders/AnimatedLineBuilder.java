package dev.acrispycookie.crispycommons.holograms.lines.builders;

import dev.acrispycookie.crispycommons.holograms.lines.implementations.AnimatedHologramLine;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedLineBuilder {

    private final ArrayList<String> animation;
    private final JavaPlugin plugin;
    private int period;

    public AnimatedLineBuilder(JavaPlugin plugin) {
        this.animation = new ArrayList<>();
        this.plugin = plugin;
    }

    public AnimatedLineBuilder setPeriod(int period) {
        this.period = period;
        return this;
    }

    public AnimatedLineBuilder addFrame(String line) {
        this.animation.add(line);
        return this;
    }

    public AnimatedLineBuilder addFrame(String... lines) {
        this.animation.addAll(Arrays.asList(lines));
        return this;
    }

    public AnimatedHologramLine build() {
        return new AnimatedHologramLine(plugin, period, animation);
    }


}
