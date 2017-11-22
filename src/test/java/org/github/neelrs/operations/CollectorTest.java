package org.github.neelrs.operations;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class CollectorTest {

    @Test
    public void inListShouldReturnImplementationOfList() throws Exception {
        final Supplier<List<Object>> container = Collector.inList();
        Assert.assertTrue(container.get() != null);
    }

    @Test
    public void inSetShouldReturnImplementationOfSet() throws Exception {
        final Supplier<Set<Object>> container = Collector.inSet();
        Assert.assertTrue(container.get() != null);
    }
}
