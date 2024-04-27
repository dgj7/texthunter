package com.dg.apps.th.ui.tools;

import com.dg.apps.th.model.adapter.ITableAdapter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Vector;

public class ReadOnlyDataTable extends JPanel implements ITableAdapter {
    private volatile DefaultTableModel _model = null;
    private volatile JTable _table = null;
    private volatile JScrollPane _scroller = null;

    public ReadOnlyDataTable() {
        super(new BorderLayout());

        initialize();
        configure();
        addWidgets();
    }

    public synchronized void addColumn(String columnName) {
        _model.addColumn(columnName);
    }

    public synchronized void addColumns(List<String> lstColumnNames) {
        for (String columnName : lstColumnNames) {
            _model.addColumn(columnName);
        }
    }

    public synchronized void addColumns(String[] arrColumnNames) {
        for (String columnName : arrColumnNames) {
            _model.addColumn(columnName);
        }
    }

    @Override
    public synchronized void addRow(Vector<String> rowData) {
        _model.addRow(rowData);
    }

    public synchronized void addRow(String[] rowData) {
        _model.addRow(rowData);
    }

    public synchronized void removeRow(int row) {
        _model.removeRow(row);
    }

    public synchronized void setValueAt(Object aValue, int row, int column) {
        _model.setValueAt(aValue, row, column);
    }

    private void initialize() {
        _model = generateTableModel();
        _table = new JTable(_model);
        _scroller = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    private void configure() {
        _table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //_table.setCellSelectionEnabled(true);
        _table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void addWidgets() {
        this.add(_scroller, BorderLayout.CENTER);
    }

    private DefaultTableModel generateTableModel() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public synchronized void adjustColumnWidth(int column, int width) {
        try {
            _table.getColumnModel().getColumn(column).setPreferredWidth(width);
        } catch (Exception ex) {
            //
        }
    }

    public synchronized void clear() {
        _model.setRowCount(0);
    }

    public synchronized void addListSelectionListener(ListSelectionListener listener) {
        _table.getSelectionModel().addListSelectionListener(listener);
    }

    public synchronized void addMouseAdapter(MouseAdapter adapter) {
        _table.addMouseListener(adapter);
    }

    public synchronized int getSelectedRow() {
        return _table.getSelectedRow();
    }

    public synchronized Object getValueAt(int row, int column) {
        return _table.getValueAt(row, column);
    }

    public synchronized int getRowCount() {
        return _table.getRowCount();
    }

    public synchronized int getColumnCount() {
        return _table.getColumnCount();
    }
}
