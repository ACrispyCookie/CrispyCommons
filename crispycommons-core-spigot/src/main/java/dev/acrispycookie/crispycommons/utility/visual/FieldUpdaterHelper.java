package dev.acrispycookie.crispycommons.utility.visual;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class FieldUpdaterHelper {

    public static <T extends DynamicElement<?, ?>> void setNormalField(@NotNull T newElement,
                                                                       @NotNull Supplier<OwnedElement<T>> getter,
                                                                       @NotNull Consumer<T> setter,
                                                                       boolean shouldStart,
                                                                       @NotNull Runnable updateRunnable) {
        if (getter.get() != null)
            getter.get().destroy();
        setter.accept(newElement);
        getter.get().setUpdate(updateRunnable);
        if (shouldStart) {
            getter.get().start();
            updateRunnable.run();
        }
    }

    public static void offsetAfterAdd(int index, List<OwnedElement<DynamicElement<?, ?>>> lines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(index + 1, lines.size()).forEach((idx) -> {
            OwnedElement<DynamicElement<?, ?>> element = lines.get(idx);
            element.updateKey(idx, () -> updateAction.accept(idx));
            if (shouldStart)
                element.start();
        });

        if (shouldStart)
            lines.get(index).start();
    }

    public static void offsetAfterRemove(int index, List<OwnedElement<DynamicElement<?, ?>>> lines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(index, lines.size()).forEach((idx) -> {
            OwnedElement<DynamicElement<?, ?>> element = lines.get(idx);
            element.updateKey(idx, () -> updateAction.accept(idx));
            if (shouldStart)
                element.start();
        });
    }

    public static void resetLines(List<OwnedElement<DynamicElement<?, ?>>> oldLines, List<OwnedElement<DynamicElement<?, ?>>> newLines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(0, newLines.size()).forEach((idx) -> newLines.get(idx).setUpdate(() -> updateAction.accept(idx)));
        oldLines.forEach(OwnedElement::destroy);
        if (shouldStart)
            newLines.forEach(OwnedElement::start);
    }

    public static void offsetAfterAddText(int index, List<OwnedElement<TextElement<?>>> lines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(index + 1, lines.size()).forEach((idx) -> {
            OwnedElement<TextElement<?>> element = lines.get(idx);
            element.updateKey(idx, () -> updateAction.accept(idx));
            if (shouldStart)
                element.start();
        });

        if (shouldStart)
            lines.get(index).start();
    }

    public static void offsetAfterRemoveText(int index, List<OwnedElement<TextElement<?>>> lines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(index, lines.size()).forEach((idx) -> {
            OwnedElement<TextElement<?>> element = lines.get(idx);
            element.updateKey(idx, () -> updateAction.accept(idx));
            if (shouldStart)
                element.start();
        });
    }

    public static void resetLinesText(List<OwnedElement<TextElement<?>>> oldLines, List<OwnedElement<TextElement<?>>> newLines, boolean shouldStart, Consumer<Integer> updateAction) {
        IntStream.range(0, newLines.size()).forEach((idx) -> newLines.get(idx).setUpdate(() -> updateAction.accept(idx)));
        oldLines.forEach(OwnedElement::destroy);
        if (shouldStart)
            newLines.forEach(OwnedElement::start);
    }
}
