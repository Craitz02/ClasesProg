/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.backend.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class ModeloTabla extends AbstractTableModel {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final String columnas[] = {"Stock Number", "Year", "Make", "Model", "Style", "VIN", "Exterior Color", "Internal Color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};

    public ModeloTabla() {
    }
    
    @Override
    public int getRowCount() {
        return vehicles.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }
    
    public void addRow(Vehicle[] veh){
        vehicles.addAll(Arrays.asList(veh));
        this.fireTableRowsInserted(vehicles.size(), vehicles.size());
    }
    
    public void removeRow(int index){
         vehicles.remove(index);
         this.fireTableRowsDeleted(index, index);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return vehicles.get(rowIndex).getStockNumber();
            case 1: return vehicles.get(rowIndex).getYear();
            case 2: return vehicles.get(rowIndex).getMake();
            case 3: return vehicles.get(rowIndex).getModel();
            case 4: return vehicles.get(rowIndex).getStyle();
            case 5: return vehicles.get(rowIndex).getVin();
            case 6: return vehicles.get(rowIndex).getExteriorColor();
            case 7: return vehicles.get(rowIndex).getInteriorColor();
            case 8: return vehicles.get(rowIndex).getMiles();
            case 9: return vehicles.get(rowIndex).getPrice();
            case 10: return vehicles.get(rowIndex).getTransmission();
            case 11: return vehicles.get(rowIndex).getEngine();
            case 12: return vehicles.get(rowIndex).getImage();
            case 13: return vehicles.get(rowIndex).getStatus();
            default: return null;
        }
    }
    
}
