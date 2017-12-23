package com.github.neelrs.operations;

import com.github.neelrs.operations.fixture.CollectionCreator;
import com.github.neelrs.operations.fixture.RandomPOJO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class SubsetTest {

    @Test
    public void shouldNotifyIfSubset() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field, new Object());
        Assert.assertTrue(Operations.isSubset(aList).of(bList));
    }

    @Test
    public void shouldNotifyIfNotSubset() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, new Object(), new Object());
        Assert.assertFalse(Operations.isSubset(aList).of(bList));
    }
}
