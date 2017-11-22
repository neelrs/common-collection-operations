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

        final List<RandomPOJO> union = Operations.union(aList, bList)
                .get(Collector.inList());

        Assert.assertEquals(1, union.size());
    }

    @Test
    public void shouldPerformUnionOfTwoListsToGiveResultInSet() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final Set<RandomPOJO> union = Operations.union(aList, bList)
                .get(Collector.inSet());

        Assert.assertEquals(1, union.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowDuplicatesForSet() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        Operations.union(aList, bList)
                .withDuplicates()
                .get(HashSet::new);

    }

    @Test
    public void shouldPerformUnionOfTwoListsWithDuplicatesInList() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final Collection<RandomPOJO> union = Operations.union(aList, bList).withDuplicates().get(Collector.inList());

        Assert.assertEquals(2, union.size());
        final RandomPOJO randomPOJO = new RandomPOJO();
        randomPOJO.setField(field);
        for (final RandomPOJO r : union) {
            Assert.assertEquals(randomPOJO, r);
        }
    }

    @Test
    public void shouldPerformUnionOfTwoListsWithoutDuplicatesAndReturnListByDefault() throws Exception {
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

        final List<RandomPOJO> union = Operations.union(aList, bList).withDuplicates().get();

        Assert.assertTrue(union != null);
        Assert.assertEquals(2, union.size());
    }


}
