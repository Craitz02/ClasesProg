/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import ni.edu.uni.misprogramas.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.misprogramas.backend.pojo.ModeloTabla;
import ni.edu.uni.misprogramas.backend.pojo.Vehicle;
import ni.edu.uni.misprogramas.views.panels.PnlViewVehicle;

/**
 *
 * @author Rodrigo
 */
public class PnlViewVehicleController {

    private final PnlViewVehicle pnlViewVehicle;
    private JsonVehicleDaoImpl jvdao;
    private DefaultComboBoxModel cmbModelType;
    private DefaultTableModel TableModel;
    private final String TYPES[] = new String[]{"-", "Make", "Model", "Style", "Year", "VIN"};
    private final String colums[] = new String[]{"Stock Number", "Year", "Make", "Model", "Style", "VIN", "Exterior Color", "Internal Color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};

    public PnlViewVehicleController(PnlViewVehicle pnlViewVehicle) throws FileNotFoundException {
        this.pnlViewVehicle = pnlViewVehicle;
        initComponent();
    }

    private void initComponent() throws FileNotFoundException, IOException {
        jvdao = new JsonVehicleDaoImpl();
        if(jvdao.getAll().isEmpty()){
            return;
        }
        
        String matriz[][]= new String[jvdao.getAll().size()][15];
        for (int i = 0; i < jvdao.getAll().size(); i++) {
            
        }
        
        cmbModelType = new DefaultComboBoxModel(TYPES);
        TableModel = new DefaultTableModel(null,colums);
//        for (String c : colums) {
//            TableModel.addColumn(c);
//        }

        pnlViewVehicle.getCmbType().setModel(cmbModelType);
        pnlViewVehicle.getTblViews().setModel(TableModel);

        pnlViewVehicle.getTxtBrowse().addKeyListener(new KeyAdapter()
        {
            public void KeyR(final KeyEvent e)
            {
                TableRowSorter TFilter = new TableRowSorter(pnlViewVehicle.getTblViews().getModel());
                String s = pnlViewVehicle.getTxtBrowse().getText();

                FilterTabe(pnlViewVehicle.getCmbType().getSelectedIndex(), TFilter);
            }
        });
        pnlViewVehicle.getBtnSearch().addActionListener((e) -> {
            btnSearchActionListener(e);
        });
    }
    
    private void FilterTabe(int a, TableRowSorter filter)
    {
        filter.setRowFilter(RowFilter.regexFilter(pnlViewVehicle.getTxtBrowse().getText(), a));
        pnlViewVehicle.getTblViews().setRowSorter(filter);
    } 

    private void btnSearchActionListener(ActionEvent e) {
        if (pnlViewVehicle.getCmbType().getSelectedItem().toString().equals("-")) {
            JOptionPane.showMessageDialog(null, "Please Select a Search Type.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        if (pnlViewVehicle.getCmbType().getSelectedItem().toString().equals("Make")) {
            Collection<Vehicle> allvehicles = new ArrayList<>();
            Collection<Vehicle> vehicles = new ArrayList<>();
            String make = pnlViewVehicle.getTxtBrowse().getText();
            try {
                allvehicles = jvdao.getAll();
            } catch (IOException ex) {
                Logger.getLogger(PnlViewVehicleController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            for (Vehicle vehicle : allvehicles) {
//                if (vehicle.getMake().equalsIgnoreCase(make)) {
//                    vehicles.add(vehicle);
//                }
//            }
            FillTable(allvehicles);
        }
    }

    private void FillTable(Collection<Vehicle> vehicles) {
        DefaultTableModel datos = (DefaultTableModel) pnlViewVehicle.getTblViews().getModel();
        
        for (Vehicle v : vehicles) {
            Object[] row = {v.getStockNumber(), v.getYear(),
                v.getMake(), v.getModel(), v.getStyle(), v.getVin(),
                v.getExteriorColor(), v.getInteriorColor(), v.getMiles(),
                v.getPrice(), v.getTransmission(), v.getEngine(), v.getImage(), v.getStatus()};
            
            datos.addRow(row);
        }
        
        
//        pnlViewVehicle.getTblViews().setModel(datos);
    }
}
