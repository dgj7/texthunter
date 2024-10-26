package com.dg.apps.th.ui.view.adapter;

import com.dg.apps.th.ui.view.panel.ReadOnlyDataTable;

/**
 * Data table aware components.
 */
public interface IDataTableAware extends IComponentAware {
    /**
     * Access the data table.
     */
    ReadOnlyDataTable getTableReference();
}
