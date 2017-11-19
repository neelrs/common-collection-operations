package org.github.neelrs.operations;

import java.util.Collection;

public final class Operations {

    public static <T> UnionOperation<T> union(final Collection<T> aList, final Collection<T> bList) {
        return new UnionOperation<>(aList, bList);
    }

    public static <T> IntersectionOperation<T> intersection(final Collection<T> aList, final Collection<T> bList) {
        return new IntersectionOperation<>(aList, bList);
    }

    public static <T> SubsetOperation<T> isSubset(final Collection<T> aList) {
        return new SubsetOperation<>(aList);
    }
}
