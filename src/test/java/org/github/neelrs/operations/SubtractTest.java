package org.github.neelrs.operations;

import org.github.neelrs.operations.fixture.CollectionCreator;
import org.github.neelrs.operations.fixture.RandomPOJO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubtractTest {

    @Test
    public void shouldTakeDifferenceOfTwoCollections() throws Exception {
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, "A", "B");
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, "A");
        final List<RandomPOJO> expected = (List<RandomPOJO>)CollectionCreator.getCollection(ArrayList::new, "B");

        final List<RandomPOJO> diff = Operations.subtract(aList, bList).get();

        Assert.assertEquals(expected, diff);
    }

    @Test
    public void shouldTakeDifferenceOfTwoCollectionsAndReturnResultInSpecifiedTypeOfCollectionImplementation() throws Exception {
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, "A", "B");
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, "A", "C");
        final Set<RandomPOJO> expected = (Set<RandomPOJO>)CollectionCreator.getCollection(HashSet::new, "B");

        final Set<RandomPOJO> diff = Operations.subtract(aList, bList).get(Collector.inSet());

        Assert.assertEquals(expected, diff);
    }

    @Test
    public void subtractShouldReturnEmptyCollectionForSameCollections() throws Exception {
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, "A");
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, "A");

        final Set<RandomPOJO> diff = Operations.subtract(aList, bList).get(Collector.inSet());

        Assert.assertTrue(diff.isEmpty());
    }
}
