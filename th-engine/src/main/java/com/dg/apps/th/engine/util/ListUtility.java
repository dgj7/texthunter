package com.dg.apps.th.engine.util;

import java.util.*;

/**
 * Various list utilities.
 */
public class ListUtility {
    /**
     * Split a collection into n collections.
     */
    public static <T> List<List<T>> splitList(final List<T> list, final int numLists) {
        final List<List<T>> lstReturn = new LinkedList<>();
        final Iterator<T> iter = list.iterator();

        for (int c = 0; c < numLists; c++) {
            final List<T> temp = new LinkedList<>();
            lstReturn.add(temp);
        }

        while (iter.hasNext()) {
            for (int collectionIndex = 0; collectionIndex < numLists; collectionIndex++) {
                if (iter.hasNext()) {
                    final List<T> current = lstReturn.get(collectionIndex);
                    final T value = iter.next();
                    current.add(value);
                }
            }
        }

        return lstReturn;
    }
}
