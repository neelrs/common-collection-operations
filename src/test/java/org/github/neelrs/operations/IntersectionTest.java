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

public class IntersectionTest {

    @Test
    public void intersectionShouldFindCommonOfTwoListsIntoList() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field);

        Collection<RandomPOJO> intersection = Operations.intersection(aList, bList).get();

        Assert.assertEquals(1, intersection.size());
        Assert.assertTrue(intersection instanceof List);
        Assert.assertEquals(aList, intersection);
        Assert.assertEquals(bList, intersection);
    }

    @Test
    public void intersectionShouldExcludeFieldsNotPresentInBoth() throws Exception {
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, new Object());
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, new Object());

        Collection<RandomPOJO> intersection = Operations.intersection(aList, bList).get();

        Assert.assertTrue(intersection.isEmpty());
    }

    @Test
    public void intersectionShouldRemoveDuplicates() throws Exception {
        final Object field = new Object();
        final Collection<RandomPOJO> aList = CollectionCreator.getCollection(ArrayList::new, field, field);
        final Collection<RandomPOJO> bList = CollectionCreator.getCollection(ArrayList::new, field, new Object());

        Collection<RandomPOJO> intersection = Operations.intersection(aList, bList).get();

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

        Collection<RandomPOJO> intersection = Operations.intersection(aList, bList)
                .collectionContainer(HashSet::new)
                .get();

        Assert.assertTrue(intersection instanceof Set);
    }
}
