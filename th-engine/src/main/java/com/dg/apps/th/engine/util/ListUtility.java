package com.dg.apps.th.engine.util;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Various list utilities.
 */
public class ListUtility {
    /**
     * Split a collection into n collections.
     */
    public static <T> List<List<T>> splitList(final List<T> list, final int numLists) {
        final List<List<T>> lstReturn = new LinkedList<>();
        int listIndex = 0;

        IntStream.range(0, numLists).forEach(idx -> lstReturn.add(new LinkedList<>()));

        for (final T element : list) {
            lstReturn.get(listIndex).add(element);
            listIndex = listIndex >= (numLists-1) ? 0 : listIndex+1;
        }

        return lstReturn;
    }
}
