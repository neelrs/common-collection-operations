package com.github.neelrs.operations.fixture;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public class CollectionCreator {
    public static Collection<RandomPOJO> getCollection(Supplier<Collection<RandomPOJO>> collectionSupplier, Object... field) {
        final Collection<RandomPOJO> aList = collectionSupplier.get();
        Arrays.stream(field).forEach(f -> {
            final RandomPOJO a = new RandomPOJO();
            a.setField(f);
            aList.add(a);
        });
        return aList;
    }
}
