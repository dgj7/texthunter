package com.dg.apps.th.ui.view.panel;

import com.dg.apps.th.model.adapter.ITableAdapter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Panel storing a data table.
 */
@Slf4j
public class ReadOnlyDataTable extends JPanel implements ITableAdapter {
    private volatile DefaultTableModel model = null;
    private volatile JTable table = null;
    private volatile JScrollPane scroller = null;

    /**
     * Create a new instance.
     */
    public ReadOnlyDataTable() {
        super(new BorderLayout());

        initialize();
        configure();
        addWidgets();
    }

    /**
     * Add columns.
     */
    public synchronized void addColumns(final String[] arrColumnNames) {
        for (String columnName : arrColumnNames) {
            model.addColumn(columnName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addRow(final Vector<String> rowData) {
        model.addRow(rowData);
    }

    /**
     * Initialize.
     */
    private void initialize() {
        model = generateTableModel();
        table = new JTable(model);
        scroller = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Configure.
     */
    private void configure() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * Add widgets.
     */
    private void addWidgets() {
        this.add(scroller, BorderLayout.CENTER);
    }

    /**
     * Generate the table model.
     */
    private DefaultTableModel generateTableModel() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
    }

    /**
     * Adjust the column width.
     */
    public synchronized void adjustColumnWidth(final int column, final int width) {
        table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }

    /**
     * Get a value in the table.
     */
    public synchronized Object getValueAt(final int row, final int column) {
        return table.getValueAt(row, column);
    }

    /**
     * Get the row count.
     */
    public synchronized int getRowCount() {
        return table.getRowCount();
    }

    /**
     * Get the column count.
     */
    public synchronized int getColumnCount() {
        return table.getColumnCount();
    }
}
