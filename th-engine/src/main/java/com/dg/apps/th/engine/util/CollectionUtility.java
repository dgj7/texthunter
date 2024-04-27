package com.dg.apps.th.engine.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Various collection utilities.
 */
public class CollectionUtility {
    /**
     * Split a collection into n collections.
     */
    public static <T> List<Collection<T>> splitCollection(final Collection<T> col, final int numLists) throws InstantiationException, IllegalAccessException {
        final List<Collection<T>> lstReturn = new ArrayList<Collection<T>>();
        final Iterator<T> iter = col.iterator();

        for (int c = 0; c < numLists; c++) {
            final Collection<T> temp = col.getClass().newInstance();
            lstReturn.add(temp);
        }

        while (iter.hasNext()) {
            for (int collectionIndex = 0; collectionIndex < numLists; collectionIndex++) {
                if (iter.hasNext()) {
                    final Collection<T> current = lstReturn.get(collectionIndex);
                    final T value = iter.next();
                    current.add(value);
                }
            }
        }

        return lstReturn;
    }

    /**
     * Determine if the given Class is a {@link Collection}.
     */
    public static boolean isCollection(Class<?> clazz) {
        if (Collection.class.isAssignableFrom(clazz))
            return true;
        return false;
    }

    /**
     * Determine if the given Object is a {@link Collection}.
     */
    public static boolean isCollection(Object object) {
        return isCollection(object.getClass());
    }
}
