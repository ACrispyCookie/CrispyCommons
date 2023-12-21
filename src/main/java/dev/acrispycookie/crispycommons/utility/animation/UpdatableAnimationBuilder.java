package dev.acrispycookie.crispycommons.utility.animation;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class UpdatableAnimationBuilder<T> extends AnimationBuilder<T> {

    protected final JavaPlugin plugin;
    public abstract void update();

    public UpdatableAnimationBuilder(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Animation<T> build() {
        return new Animation<T>(plugin, frames, period) {
            @Override
            public void update() {
                UpdatableAnimationBuilder.this.update();
            }
        };
    }
}
