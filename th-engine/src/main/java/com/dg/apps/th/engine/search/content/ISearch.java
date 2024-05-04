package com.dg.apps.th.engine.search.content;

import com.dg.apps.th.engine.search.content.impl.FileSearcher;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.impl.FileSearchStatusReporter;
import com.dg.apps.th.model.adapter.ILabelAdapter;
import com.dg.apps.th.model.adapter.ITableAdapter;
import com.dg.apps.th.model.config.SearchConfiguration;

/**
 * Main handle to search.
 */
public interface ISearch extends Runnable {
    /**
     * Request that the process cancel.
     */
    void requestCancel();

    /**
     * Simple factory.
     */
    static ISearch create(final SearchConfiguration config, final ITableAdapter tableAdapter, final ILabelAdapter labelAdapter) {
        final IStatusReporter reporter = new FileSearchStatusReporter(tableAdapter, labelAdapter);
        reporter.reportSuccess(null);
        return new FileSearcher(config, reporter);
    }
}
