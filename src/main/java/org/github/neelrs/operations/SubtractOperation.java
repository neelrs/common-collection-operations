package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class SubtractOperation<T> extends GenericOperation {
    private final Collection<T> aList;
    private final Collection<T> bList;

    SubtractOperation(final Collection<T> aList, final Collection<T> bList) {
        this.aList = aList;
        this.bList = bList;
    }

    public <U extends Collection<T>> U get(final Supplier<U> resultContainer) {
        return getDifference(resultContainer.get());
    }

    public List<T> get() {
        return getDifference(new ArrayList<>());
    }

    private <U extends Collection<T>> U getDifference(final U result) {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        addToMapWithoutDuplicates(bList, objectMap);
        for (final T o : aList) {
            if (!objectMap.containsKey(o)) {
                result.add(o);
            }
        }
        return result;
    }
}
