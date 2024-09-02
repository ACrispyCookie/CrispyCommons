package dev.acrispycookie.crispycommons.utility.menu;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * A stack implementation with a fixed maximum size.
 * <p>
 * This stack behaves like a typical stack but will maintain a maximum number of elements.
 * When the maximum size is reached, the oldest elements are removed to make room for new ones.
 * This ensures that the stack never exceeds the specified size.
 * </p>
 *
 * @param <T> the type of elements in the stack
 */
public class SizedStack<T> extends Stack<T> {

    /**
     * Maximum size of the stack.
     */
    private final int maxSize;

    /**
     * Constructs a {@link SizedStack} with the specified maximum size.
     *
     * @param size the maximum number of elements that the stack can hold
     */
    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    /**
     * Pushes an element onto the top of the stack. If the stack has reached its maximum size,
     * the oldest elements are removed to maintain the maximum size.
     *
     * @param object the element to be pushed onto the stack
     * @return the element that was pushed onto the stack
     */
    @Override
    public @NotNull T push(@NotNull T object) {
        // Remove the oldest elements if the stack has reached its maximum size
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}