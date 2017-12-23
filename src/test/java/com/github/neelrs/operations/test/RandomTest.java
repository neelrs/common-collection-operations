package com.github.neelrs.operations.test;

import com.github.neelrs.operations.Collector;
import com.github.neelrs.operations.Operations;
import com.github.neelrs.operations.fixture.CollectionCreator;
import com.github.neelrs.operations.fixture.RandomPOJO;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Ignore
public class RandomTest {

    @Test
    public void testMultipleOperations() throws Exception {
        final Collection<RandomPOJO> a = CollectionCreator.getCollection(ArrayList::new, "A");
        final Collection<RandomPOJO> b = CollectionCreator.getCollection(HashSet::new, "B", "C");
        final Collection<RandomPOJO> c = CollectionCreator.getCollection(ArrayList::new, "C", "A");

        final List<RandomPOJO> union = Operations.intersection(a, Operations.union(b, c).get(Collector.inSet())).get(Collector.inList());

        union.forEach(u -> System.out.println(u.getField()));
    }

    @Test
    public void testComplexOperations() throws Exception {
        final Collection<RandomPOJO> a = CollectionCreator.getCollection(ArrayList::new, "A", "C", "C");
        final Collection<RandomPOJO> b = CollectionCreator.getCollection(HashSet::new, "B", "A");

        final List<RandomPOJO> union = Operations.union(Operations.subtract(a, b).get(), Operations.subtract(b, a).get())
                .withDuplicates()
                .get();

        union.forEach(u -> System.out.println(u.getField()));
    }

    @Test
    public void testComplexSubset() throws Exception {
        final Collection<RandomPOJO> a = CollectionCreator.getCollection(ArrayList::new, "A", "C", "C");
        final Collection<RandomPOJO> b = CollectionCreator.getCollection(HashSet::new, "B", "A");

        System.out.println(Operations.isSubset(Operations.intersection(a, b).get()).of(a));
    }
}
