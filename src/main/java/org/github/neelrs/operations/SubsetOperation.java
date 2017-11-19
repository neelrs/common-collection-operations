package org.github.neelrs.operations;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SubsetOperation<T> extends GenericOperation {

    private final Collection<T> aList;

    SubsetOperation(final Collection<T> aList) {
        this.aList = aList;
    }

    public boolean of(final Collection<T> bList) {
        final Map<T, AtomicInteger> objectMap = new ConcurrentHashMap<>();
        addToMapWithoutDuplicates(bList, objectMap);
        return aList.stream().allMatch(objectMap::containsKey);
    }

}
