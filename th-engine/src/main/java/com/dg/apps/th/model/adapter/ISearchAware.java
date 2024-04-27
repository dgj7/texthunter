package com.dg.apps.th.model.adapter;

import com.dg.apps.th.engine.search.content.FileSearchLauncher;

/**
 * A type that's aware of the main search handle.
 */
public interface ISearchAware {
    /**
     * Get the main search handle.
     */
    FileSearchLauncher getFileSearchLauncherReference();
}
