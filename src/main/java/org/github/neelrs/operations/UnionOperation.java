package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class UnionOperation<T> extends GenericOperation {

    private final Collection<T> aList;
    private final Collection<T> bList;
    private boolean withDuplicates;

    UnionOperation(final Collection<T> aList, final Collection<T> bList) {
        this.aList = aList;
        this.bList = bList;
    }

    public UnionOperation<T> withDuplicates() {
        this.withDuplicates = true;
        return this;
    }

    public <U extends Collection<T>> U get(final Supplier<U> container) {
        final U resultList = container.get();
        return findUnion(resultList);
    }

    public List<T> get() {
        final List<T> resultList = new ArrayList<>();
        return findUnion(resultList);
    }

    private <U extends Collection<T>> U findUnion(final U resultList) {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        validate(resultList);
        if (this.withDuplicates) {
            addToMapWithDuplicates(aList, objectMap);
            addToMapWithDuplicates(bList, objectMap);
        } else {
            addToMapWithoutDuplicates(aList, objectMap);
            addToMapWithoutDuplicates(bList, objectMap);
        }
        for (final Map.Entry<T, AtomicInteger> entry : objectMap.entrySet()) {
            final int count = entry.getValue().get();
            for (int i = 0; i < count; i++) {
                resultList.add(entry.getKey());
            }
        }
        return resultList;
    }

    private void validate(final Collection<T> resultList) {
        if (withDuplicates && resultList instanceof Set) {
            throw new IllegalArgumentException(resultList.getClass() + " cannot contain duplicates. " +
                    "You might want to remove duplicates of use List");
        }
    }
}
