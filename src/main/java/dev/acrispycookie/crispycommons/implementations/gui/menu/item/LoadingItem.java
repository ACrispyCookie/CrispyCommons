package dev.acrispycookie.crispycommons.implementations.gui.menu.item;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.ItemLoadData;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * A base class for menu items that require asynchronous loading.
 * <p>
 * The {@code LoadingItem} class extends {@link AbstractMenuItem} and provides the framework for items that are not
 * immediately available but need to be loaded asynchronously. The actual loading logic is provided by the
 * {@link #loadData()} method, which subclasses must implement.
 * </p>
 */
public abstract class LoadingItem extends AbstractMenuItem {

    /**
     * Constructs a {@code LoadingItem} with the specified loading display element.
     * <p>
     * The loading display is shown to the player while the item is being asynchronously loaded.
     * </p>
     *
     * @param loadingDisplay the display element shown while the item is loading.
     *
     * @throws NullPointerException if {@code loadingDisplay} is {@code null}.
     */
    public LoadingItem(@NotNull ItemElement<?> loadingDisplay) {
        super(null, loadingDisplay, null);
    }

    /**
     * Provides the data necessary to fully load the item.
     * <p>
     * Subclasses must implement this method to return the {@link ItemLoadData} that contains the display elements,
     * visibility rules, and click actions for the item once it has finished loading.
     * </p>
     *
     * @return the {@link ItemLoadData} containing the loaded item's data.
     */
    protected abstract @NotNull ItemLoadData loadData();

    /**
     * Asynchronously loads the item data and updates the item's state.
     * <p>
     * This method runs the loading process asynchronously using the Bukkit scheduler. Once loading is complete,
     * the item is marked as loaded, and the provided {@code onLoad} runnable is executed.
     * </p>
     *
     * @param onLoad the runnable to execute after the item has finished loading.
     */
    @Override
    public void load(@NotNull Runnable onLoad) {
        if (isLoaded) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(CrispyCommons.getPlugin(), () -> {
            ItemLoadData data = loadData();
            setLoadedDisplay(data.getDisplay());
            setAlternativeDisplay(data.getAlternativeDisplay());
            setCanSee(data.getCanSee());
            setOnClick(data.getOnClick());
            this.isLoaded = true;
            onLoad.run();
        });
    }
}

