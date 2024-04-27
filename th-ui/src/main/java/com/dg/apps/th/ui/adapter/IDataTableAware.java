package com.dg.apps.th.ui.adapter;

import com.dg.apps.th.ui.panel.ReadOnlyDataTable;

/**
 * Data table aware components.
 */
public interface IDataTableAware extends IComponentAware {
    /**
     * Access the data table.
     */
    ReadOnlyDataTable getTableReference();
}
