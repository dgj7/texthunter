package com.dg.apps.th.engine.search.content;

import com.dg.apps.th.engine.search.content.impl.FileSearcher;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.impl.FileSearchStatusReporter;
import com.dg.apps.th.model.adapter.ILabelAdapter;
import com.dg.apps.th.model.adapter.ITableAdapter;
import com.dg.apps.th.model.config.SearchConfiguration;

/**
 * <p>
 * Main handle to search.
 * </p>
 * <p>
 * Extends {@link Runnable}, as it's main method is {@code run()},
 * and we want to launch the search process in it's own thread.  This
 * keeps the search ui thread responsive.
 * </p>
 * <p>
 * Adds a method that can be called from a button, to halt the process
 * and return.
 * </p>
 * <p>
 * It's expected that this process won't return any value.  Instead, it'll
 * utilise various injected interfaces to contact the ui for updates
 * in real time.
 * </p>
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
