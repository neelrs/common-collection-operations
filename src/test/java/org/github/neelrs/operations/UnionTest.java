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

public class UnionTest {

    @Test
    public void shouldBeAbleToPerformUnionOfListAndSet() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(HashSet::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList)
                .collectionContainer(ArrayList::new)
                .get();

        Assert.assertTrue(union instanceof List);
        Assert.assertEquals(1, union.size());
    }

    @Test
    public void shouldPerformUnionOfTwoListsToGiveResultInSet() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList)
                .collectionContainer(HashSet::new)
                .get();

        Assert.assertTrue(union instanceof Set);
        Assert.assertEquals(1, union.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowDuplicatesForSet() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        Operations.union(aList, bList)
                .collectionContainer(HashSet::new)
                .withDuplicates()
                .get();

    }

    @Test
    public void shouldPerformUnionOfTwoListsWithDuplicates() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList).withDuplicates().get();

        Assert.assertEquals(2, union.size());
        final RandomPOJO randomPOJO = new RandomPOJO();
        randomPOJO.setField(field);
        for (final RandomPOJO r : union) {
            Assert.assertEquals(randomPOJO, r);
        }
    }

    @Test
    public void shouldPerformUnionOfTwoListsWithoutDuplicates() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList).get();

        Assert.assertEquals(1, union.size());
    }

    @Test
    public void shouldPerformUnionOfTwoSetsToGiveResultWithDuplicatesInList() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(HashSet::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(HashSet::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList).withDuplicates().get();

        Assert.assertTrue(union instanceof List);
        Assert.assertEquals(2, union.size());
    }


}
