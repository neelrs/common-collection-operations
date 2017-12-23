package com.github.neelrs.operations;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class GenericOperation {

    static <T> void addToMapWithDuplicates(final Collection<T> list, final Map<T, AtomicInteger> objectMap) {
        for (final T o : list) {
            objectMap.putIfAbsent(o, new AtomicInteger(0));
            objectMap.get(o).incrementAndGet();
        }
    }

    static <T> void addToMapWithoutDuplicates(final Collection<T> list, final Map<T, AtomicInteger> objectMap) {
        for (final T o : list) {
            objectMap.put(o, new AtomicInteger(1));
        }
    }
}
