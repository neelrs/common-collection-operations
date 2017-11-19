package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

class UnionOperation<T> extends GenericOperation {

    private final Collection<T> aList;
    private final Collection<T> bList;
    private boolean withDuplicates;
    private Collection<T> resultContainer = new ArrayList<>();

    UnionOperation(final Collection<T> aList, final Collection<T> bList) {
        this.aList = aList;
        this.bList = bList;
    }

    public UnionOperation<T> withDuplicates() {
        this.withDuplicates = true;
        return this;
    }

    public UnionOperation<T> collectionContainer(final Supplier<Collection<T>> container) {
        this.resultContainer = container.get();
        return this;
    }

    public Collection<T> get() {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        validate();
        if (this.withDuplicates) {
            addToMapWithDuplicates(aList, objectMap);
            addToMapWithDuplicates(bList, objectMap);
        } else {
            addToMapWithoutDuplicates(aList, objectMap);
            addToMapWithoutDuplicates(bList, objectMap);
        }
        final Collection<T> resultList = resultContainer;
        for (final Map.Entry<T, AtomicInteger> entry : objectMap.entrySet()) {
            final int count = entry.getValue().get();
            for (int i = 0; i < count; i++) {
                resultList.add(entry.getKey());
            }
        }
        return resultList;
    }

    private void validate() {
        if (withDuplicates && resultContainer instanceof Set) {
            throw new IllegalArgumentException(resultContainer.getClass() + " cannot contain duplicates. " +
                    "You might want to remove duplicates of use List");
        }
    }
}
