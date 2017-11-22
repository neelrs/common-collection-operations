package org.github.neelrs.operations;

import org.github.neelrs.operations.fixture.CollectionCreator;
import org.github.neelrs.operations.fixture.RandomPOJO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class IntersectionTest {

    @Test
    public void intersectionShouldFindCommonOfTwoListsIntoList() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        final List<RandomPOJO> intersection = Operations.intersection(aList, bList).get(Collector.inList());

        Assert.assertEquals(1, intersection.size());
        Assert.assertEquals(aList, intersection);
        Assert.assertEquals(bList, intersection);
    }

    @Test
    public void intersectionShouldExcludeFieldsNotPresentInBoth() throws Exception {
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, new Object());
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, new Object());

        final List<RandomPOJO> intersection = Operations.intersection(aList, bList).get(Collector.inList());

        Assert.assertTrue(intersection.isEmpty());
    }

    @Test
    public void intersectionShouldRemoveDuplicates() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field, new Object());

        Collection<RandomPOJO> intersection = Operations.intersection(aList, bList).get(Collector.inList());

        Assert.assertEquals(1, intersection.size());
        final RandomPOJO randomPOJO = new RandomPOJO();
        randomPOJO.setField(field);
        Assert.assertTrue(intersection.contains(randomPOJO));
    }

    @Test
    public void shouldReturnInContainerSpecified() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field, new Object());

        final Set<RandomPOJO> intersection = Operations.intersection(aList, bList)
                .get(Collector.inSet());

        Assert.assertTrue(intersection != null);
    }

    @Test
    public void shouldReturnListOfIntersectionByDefault() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field, new Object());

        final List<RandomPOJO> intersection = Operations.intersection(aList, bList).get();

        Assert.assertTrue(intersection != null);
    }
}
