package com.github.neelrs.operations;

import com.github.neelrs.operations.fixture.CollectionCreator;
import com.github.neelrs.operations.fixture.RandomPOJO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GenericOperationTest {

    @Test
    public void shouldCaptureCountsOfOccurrenceOfObjects() throws Exception {
        final Object field = new Object();
        final Map<RandomPOJO, AtomicInteger> map = new ConcurrentHashMap<>();
        final Collection<RandomPOJO> list = CollectionCreator.getCollection(ArrayList::new, field, field);
        GenericOperation.addToMapWithDuplicates(list, map);

        final RandomPOJO pojo = new RandomPOJO();
        pojo.setField(field);
        Assert.assertEquals(2, map.get(pojo).get());
    }

    @Test
    public void shouldOverrideDuplicateOccurrences() throws Exception {
        final Object field = new Object();
        final Map<RandomPOJO, AtomicInteger> map = new ConcurrentHashMap<>();
        final Collection<RandomPOJO> list = CollectionCreator.getCollection(ArrayList::new, field, field);
        GenericOperation.addToMapWithoutDuplicates(list, map);

        final RandomPOJO pojo = new RandomPOJO();
        pojo.setField(field);
        Assert.assertEquals(1, map.get(pojo).get());
    }
}
