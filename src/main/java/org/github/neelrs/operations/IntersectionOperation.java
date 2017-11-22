package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class IntersectionOperation<T> extends GenericOperation {
    private final Collection<T> aList;
    private final Collection<T> bList;

    IntersectionOperation(final Collection<T> aList, final Collection<T> bList) {
        this.aList = aList;
        this.bList = bList;
    }

    public <U extends Collection<T>> U get(final Supplier<U> container) {
        final U resultList = container.get();
        return findIntersection(resultList);
    }

    public List<T> get() {
        final List<T> resultList = new ArrayList<>();
        return findIntersection(resultList);
    }

    private <U extends Collection<T>> U findIntersection(final U resultList) {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        addToMapWithoutDuplicates(aList, objectMap);
        bList.stream().filter(objectMap::containsKey).forEach(resultList::add);
        return resultList;
    }

}
