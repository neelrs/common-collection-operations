package org.github.neelrs.operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class Collector {
    public static <T> Supplier<List<T>> inList() {
        return ArrayList::new;
    }

    public static <T> Supplier<Set<T>> inSet() {
        return HashSet::new;
    }
}
