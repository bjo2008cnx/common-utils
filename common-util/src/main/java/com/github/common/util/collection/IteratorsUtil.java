package com.github.common.util.collection;

import java.util.*;

/**
 * Created by Aladin on 2018/5/26.
 */
public class IteratorsUtil {
    /**
     * Returns the single element contained in {@code iterator}.
     *
     * @throws java.util.NoSuchElementException if the iterator is empty
     * @throws IllegalArgumentException         if the iterator contains multiple
     *                                          elements.  The state of the iterator is unspecified.
     */
    public static <T> T getOnlyElement(Iterator<T> iterator) {
        T first = iterator.next();
        if (!iterator.hasNext()) {
            return first;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <" + first);
        for (int i = 0; i < 4 && iterator.hasNext(); i++) {
            sb.append(", " + iterator.next());
        }
        if (iterator.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');

        throw new IllegalArgumentException(sb.toString());
    }

    /**
     * Divides an iterator into unmodifiable sublists of the given size (the final
     * list may be smaller). For example, partitioning an iterator containing
     * {@code [a, b, c, d, e]} with a partition size of 3 yields {@code
     * [[a, b, c], [d, e]]} -- an outer iterator containing two inner lists of
     * three and two elements, all in the original order.
     * <p/>
     * <p>The returned lists implement {@link java.util.RandomAccess}.
     *
     * @param iterator the iterator to return a partitioned view of
     * @param size     the desired size of each partition (the last may be smaller)
     * @return an iterator of immutable lists containing the elements of {@code
     * iterator} divided into partitions
     * @throws IllegalArgumentException if {@code size} is nonpositive
     */
    public static <T> UnmodifiableIterator<List<T>> partition(
            Iterator<T> iterator, int size) {
        return partitionImpl(iterator, size, false);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(
            final Iterator<T> iterator, final int size, final boolean pad) {
        checkNotNull(iterator);
        checkArgument(size > 0);
        return new UnmodifiableIterator<List<T>>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public List<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object[] array = new Object[size];
                int count = 0;
                for (; count < size && iterator.hasNext(); count++) {
                    array[count] = iterator.next();
                }
                for (int i = count; i < size; i++) {
                    array[i] = null; // for GWT
                }

                @SuppressWarnings("unchecked") // we only put Ts in it
                        List<T> list = Collections.unmodifiableList(
                        (List<T>) Arrays.asList(array));
                return (pad || count == size) ? list : list.subList(0, count);
            }
        };
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

}
