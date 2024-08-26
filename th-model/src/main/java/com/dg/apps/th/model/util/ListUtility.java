package com.dg.apps.th.model.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Various list utilities.
 */
@Slf4j
public class ListUtility {
    /**
     * Split a collection into n collections.
     */
    public static <T> List<List<T>> splitList(final List<T> list, final int numLists) {
        final Instant start = Instant.now();

        if (list == null || numLists <= 0) {
            final String listSize = Optional.ofNullable(list)
                    .map(List::size)
                    .map(String::valueOf)
                    .orElse("null");
            log.warn("splitList() got weird input: list.size=[{}], numLists=[{}] ({}ms)", listSize, numLists, Duration.between(start, Instant.now()).toMillis());
            return new ArrayList<>(0);
        }

        final List<List<T>> lstReturn = new LinkedList<>();
        int listIndex = 0;

        IntStream.range(0, numLists).forEach(idx -> lstReturn.add(new LinkedList<>()));

        for (final T element : list) {
            lstReturn.get(listIndex).add(element);
            listIndex = listIndex >= (numLists - 1) ? 0 : listIndex + 1;
        }

        log.info("finished splitting into [{}] lists ({}ms)", lstReturn.size(), Duration.between(start, Instant.now()).toMillis());
        return lstReturn;
    }
}
