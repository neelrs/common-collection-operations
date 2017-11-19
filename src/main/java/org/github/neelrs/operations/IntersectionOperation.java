package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class IntersectionOperation<T> extends GenericOperation {
    private final Collection<T> aList;
    private final Collection<T> bList;
    private Collection<T> resultContainer = new ArrayList<>();

    IntersectionOperation(final Collection<T> aList, final Collection<T> bList) {
        this.aList = aList;
        this.bList = bList;
    }

    public IntersectionOperation<T> collectionContainer(final Supplier<Collection<T>> container) {
        this.resultContainer = container.get();
        return this;
    }

    public Collection<T> get() {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        addToMapWithoutDuplicates(aList, objectMap);
        final Collection<T> resultList = resultContainer;
        bList.stream().filter(objectMap::containsKey).forEach(resultList::add);
        return resultList;
    }

}
