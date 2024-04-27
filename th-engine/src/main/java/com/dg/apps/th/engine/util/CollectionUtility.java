package com.dg.apps.th.engine.util;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;

public class CollectionUtility {
    public static <T> List<Collection<T>> splitCollection(Collection<T> col, int numLists) throws InstantiationException, IllegalAccessException {
        List<Collection<T>> lstReturn = new ArrayList<Collection<T>>();
        Iterator<T> iter = col.iterator();

        for (int c = 0; c < numLists; c++) {
            Collection<T> temp = col.getClass().newInstance();
            lstReturn.add(temp);
        }

        while (iter.hasNext()) {
            for (int collectionIndex = 0; collectionIndex < numLists; collectionIndex++) {
                if (iter.hasNext()) {
                    Collection<T> current = lstReturn.get(collectionIndex);
                    T value = iter.next();
                    current.add(value);
                }
            }
        }

        return lstReturn;
    }

    public static boolean isCollection(Class<?> clazz) {
        if (Collection.class.isAssignableFrom(clazz))
            return true;
        return false;
    }

    public static boolean isCollection(Object object) {
        return isCollection(object.getClass());
    }
}
