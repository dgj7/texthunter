package com.dg.apps.th.model.adapter;

import java.util.Vector;

/**
 * <p>
 * Adapter for an output table, potentially in a graphical user interface.
 * </p>
 */
public interface ITableAdapter {
    /**
     * Add a row.
     */
    void addRow(final Vector<String> row);
}
