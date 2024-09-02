package dev.acrispycookie.crispycommons.api.visual.nametag;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.SimpleNameTag;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.data.NameTagData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a customizable name tag for players in the game.
 * <p>
 * The {@code CrispyNameTag} interface provides methods to set and retrieve various components of a name tag,
 * including the prefix, suffix, and additional text elements above and below the name. It also includes
 * a builder class for constructing instances of {@code CrispyNameTag}.
 * </p>
 */
public interface CrispyNameTag extends CrispyVisual {

    /**
     * Creates a new {@link NameTagBuilder} instance for constructing a {@code CrispyNameTag}.
     *
     * @return a new {@link NameTagBuilder} instance.
     */
    static @NotNull NameTagBuilder builder() {
        return new NameTagBuilder();
    }

    /**
     * Sets the prefix of the name tag.
     *
     * @param prefix the {@link NameTagElement} representing the prefix.
     */
    void setPrefix(@NotNull NameTagElement<String, ?> prefix);

    /**
     * Sets the suffix of the name tag.
     *
     * @param suffix the {@link NameTagElement} representing the suffix.
     */
    void setSuffix(@NotNull NameTagElement<String, ?> suffix);

    /**
     * Sets the text below the name in the name tag.
     *
     * @param belowName the {@link NameTagElement} representing the text below the name.
     */
    void setBelowName(@NotNull NameTagElement<String, ?> belowName);

    /**
     * Sets the value displayed below the name in the name tag.
     *
     * @param belowNameValue the {@link NameTagElement} representing the value below the name.
     */
    void setBelowNameValue(@NotNull NameTagElement<Integer, ?> belowNameValue);

    /**
     * Sets the text above the name in the name tag.
     *
     * @param aboveName the {@link NameTagElement} representing the text above the name.
     */
    void setAboveName(@NotNull NameTagElement<String, ?> aboveName);

    /**
     * Retrieves the prefix of the name tag.
     *
     * @return the {@link NameTagElement} representing the prefix.
     */
    @NotNull NameTagElement<String, ?> getPrefix();

    /**
     * Retrieves the suffix of the name tag.
     *
     * @return the {@link NameTagElement} representing the suffix.
     */
    @NotNull NameTagElement<String, ?> getSuffix();

    /**
     * Retrieves the text below the name in the name tag.
     *
     * @return the {@link NameTagElement} representing the text below the name.
     */
    @NotNull NameTagElement<String, ?> getBelowName();

    /**
     * Retrieves the value displayed below the name in the name tag.
     *
     * @return the {@link NameTagElement} representing the value below the name.
     */
    @NotNull NameTagElement<Integer, ?> getBelowNameValue();

    /**
     * Retrieves the text above the name in the name tag.
     *
     * @return the {@link NameTagElement} representing the text above the name.
     */
    @NotNull NameTagElement<String, ?> getAboveName();

    /**
     * Updates the prefix of the name tag.
     */
    void updatePrefix();

    /**
     * Updates the suffix of the name tag.
     */
    void updateSuffix();

    /**
     * Updates the text below the name in the name tag.
     */
    void updateBelowName();

    /**
     * Updates the value displayed below the name in the name tag.
     */
    void updateBelowNameValue();

    /**
     * Updates the text above the name in the name tag.
     */
    void updateAboveName();

    /**
     * Builder class for constructing instances of {@link CrispyNameTag}.
     */
    class NameTagBuilder extends AbstractVisualBuilder<CrispyNameTag> {

        private final NameTagData data = new NameTagData(null, null, null, null, null, null);

        /**
         * Sets the prefix of the name tag.
         *
         * @param prefix the {@link NameTagElement} representing the prefix.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setPrefix(@NotNull NameTagElement<String, ?> prefix) {
            this.data.setPrefix(prefix);
            this.data.getPrefix().setUpdate(() -> toBuild.updatePrefix());
            return this;
        }

        /**
         * Sets the suffix of the name tag.
         *
         * @param suffix the {@link NameTagElement} representing the suffix.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setSuffix(@NotNull NameTagElement<String, ?> suffix) {
            this.data.setSuffix(suffix);
            this.data.getSuffix().setUpdate(() -> toBuild.updateSuffix());
            return this;
        }

        /**
         * Sets the text above the name in the name tag.
         *
         * @param aboveName the {@link NameTagElement} representing the text above the name.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setAboveName(@NotNull NameTagElement<String, ?> aboveName) {
            this.data.setAboveName(aboveName);
            this.data.getAboveName().setUpdate(() -> toBuild.updateAboveName());
            return this;
        }

        /**
         * Sets the text below the name in the name tag.
         *
         * @param belowName the {@link NameTagElement} representing the text below the name.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setBelowName(@NotNull NameTagElement<String, ?> belowName) {
            this.data.setBelowName(belowName);
            this.data.getBelowName().setUpdate(() -> toBuild.updateBelowName());
            return this;
        }

        /**
         * Sets the value displayed below the name in the name tag.
         *
         * @param belowNameValue the {@link NameTagElement} representing the value below the name.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setBelowNameValue(@NotNull NameTagElement<Integer, ?> belowNameValue) {
            this.data.setBelowNameValue(belowNameValue);
            this.data.getBelowNameValue().setUpdate(() -> toBuild.updateBelowNameValue());
            return this;
        }

        /**
         * Sets the player associated with the name tag.
         *
         * @param player the {@link Player} whose name tag will be customized.
         * @return this {@link NameTagBuilder} instance for method chaining.
         */
        public @NotNull NameTagBuilder setPlayer(@NotNull Player player) {
            this.data.setPlayer(player);
            return this;
        }

        /**
         * Builds and returns the {@link CrispyNameTag} instance.
         *
         * @return the constructed {@link CrispyNameTag}.
         */
        @Override
        public @NotNull CrispyNameTag build() {
            toBuild = new SimpleNameTag(data, receivers, timeToLive, isPublic);

            return toBuild;
        }
    }
}

