package com.dg.apps.th.model.adapter;

import com.dg.apps.th.engine.search.content.ISearch;

/**
 * A type that's aware of the main search handle.
 */
public interface ISearchAware {
    /**
     * Get the main search handle.
     */
    ISearch getSearcherReference();
}
