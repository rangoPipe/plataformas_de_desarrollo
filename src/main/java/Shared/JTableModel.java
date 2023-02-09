/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shared;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author felipearango
 */
public class JTableModel extends AbstractTableModel {

    private Object[][] _rows;
    private String[] _columns;

    public JTableModel(String[] columns, Object[][] rows) {
        this._columns = columns;
        this._rows = rows;
    }

    @Override
    public String getColumnName(int column) {
        return _columns[column];
    }

    @Override
    public int getRowCount() {
        return _rows.length;
    }

    @Override
    public int getColumnCount() {
        return _columns.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object value =  _rows[row][column];
        return (value == null) ? "" : value ;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}
