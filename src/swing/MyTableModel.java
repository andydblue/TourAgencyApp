package swing;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MyTableModel extends DefaultTableModel {
    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    @Override
    public void addColumn(Object columnName, Object[] columnData) {
        super.addColumn(columnName, columnData);
    }

    @Override
    public void addColumn(Object columnName, Vector columnData) {
        super.addColumn(columnName, columnData);
    }

    @Override
    public void addColumn(Object columnName) {
        super.addColumn(columnName);
    }

    @Override
    public void setColumnCount(int columnCount) {
        super.setColumnCount(columnCount);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    @Override
    public void setColumnIdentifiers(Object[] newIdentifiers) {
        super.setColumnIdentifiers(newIdentifiers);
    }

    @Override
    public void setColumnIdentifiers(Vector columnIdentifiers) {
        super.setColumnIdentifiers(columnIdentifiers);
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public void moveRow(int start, int end, int to) {
        super.moveRow(start, end, to);
    }

    @Override
    public void insertRow(int row, Object[] rowData) {
        super.insertRow(row, rowData);
    }

    @Override
    public void insertRow(int row, Vector rowData) {
        super.insertRow(row, rowData);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }

    @Override
    public void addRow(Vector rowData) {
        super.addRow(rowData);
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount);
    }

    @Override
    public void setNumRows(int rowCount) {
        super.setNumRows(rowCount);
    }

    @Override
    public void rowsRemoved(TableModelEvent event) {
        super.rowsRemoved(event);
    }

    @Override
    public void newRowsAdded(TableModelEvent e) {
        super.newRowsAdded(e);
    }

    @Override
    public void newDataAvailable(TableModelEvent event) {
        super.newDataAvailable(event);
    }

    @Override
    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
    }

    @Override
    public void setDataVector(Vector dataVector, Vector columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
    }

    @Override
    public Vector getDataVector() {
        return super.getDataVector();
    }

    public MyTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public MyTableModel(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public MyTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public MyTableModel(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public MyTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public MyTableModel() {
        super();
    }
}
