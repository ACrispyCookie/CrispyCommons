package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractParticle<T extends CrispyEffect> extends AbstractAccessibleVisual<ParticleData<T>> implements CrispyParticle<T> {

    protected abstract void playOnce(Player p);

    AbstractParticle(ParticleData<T> data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        if (data.getDuration() == -1 || data.getPeriod() == -1) {
            receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::playOnce);
            isDisplayed = false;
            return;
        }

        data.setTask(new BukkitRunnable() {
            long i = 0;
            @Override
            public void run() {
                if (i >= data.getDuration()) {
                    cancel();
                    isDisplayed = false;
                    return;
                }
                receivers.stream().filter(OfflinePlayer::isOnline).forEach(AbstractParticle.this::playOnce);
                i += data.getPeriod();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), 0, data.getPeriod()));
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        if (data.getTask() != null)
            data.getTask().cancel();
        isDisplayed = false;
    }

    @Override
    public long getDuration() {
        return data.getDuration();
    }

    @Override
    public void setDuration(long duration) {
        data.setDuration(duration);
    }

    @Override
    public long getPeriod() {
        return data.getPeriod();
    }

    @Override
    public void setPeriod(long period) {
        data.setPeriod(period);
    }
}
