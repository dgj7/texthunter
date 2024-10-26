package com.dg.apps.th.model.pattern.build;

/**
 * Builder pattern.
 *
 * @param <T> builder target.
 */
public interface Builder<T> {
    /**
     * Build T.
     */
    T build();
}
