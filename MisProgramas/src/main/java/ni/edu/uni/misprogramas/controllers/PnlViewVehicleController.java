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
    private DefaultTableModel TableModel;
    private final String colums[] = new String[]{"Stock Number", "Year", "Make", "Model", "Style", "VIN", "Exterior Color", "Internal Color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};
    private List<Vehicle> vehicles = new ArrayList<>();

    public PnlViewVehicleController(PnlViewVehicle pnlViewVehicle) throws FileNotFoundException, IOException {
        this.pnlViewVehicle = pnlViewVehicle;
        initComponent();

    }

    private void initComponent() throws FileNotFoundException, IOException {
        TableModel = (DefaultTableModel) pnlViewVehicle.getTblViews().getModel();
        jvdao = new JsonVehicleDaoImpl();
        pnlViewVehicle.getBtnViewAll().addActionListener((e) -> {
            try {
                btnViewAllActionListener(e);
            } catch (IOException ex) {
                Logger.getLogger(PnlViewVehicleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pnlViewVehicle.getTxtBrowse().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                TableRowSorter tablefilter = new TableRowSorter(pnlViewVehicle.getTblViews().getModel());
                String text = pnlViewVehicle.getTxtBrowse().getText();
                pnlViewVehicle.getTxtBrowse().setText(text);
                
                Filtrar(pnlViewVehicle.getCmbType().getSelectedIndex(), tablefilter);
            }
        });
    }

    private void Filtrar(int a, TableRowSorter filter) {
        filter.setRowFilter(RowFilter.regexFilter(pnlViewVehicle.getTxtBrowse().getText(), a));
        pnlViewVehicle.getTblViews().setRowSorter(filter);
    }

    private void btnViewAllActionListener(ActionEvent e) throws IOException {
        vehicles = (List<Vehicle>) jvdao.getAll();
        do{
            TableModel.addRow(new Object[]{});
        }while(vehicles.size() > pnlViewVehicle.getTblViews().getRowCount());
        
        getInfo(vehicles);
        
        
    }
    
    private void getInfo(List<Vehicle> v){
        for (int i = 0; i < v.size(); i++) {
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getStockNumber(), i, 0);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getYear(), i, 1);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getMake(), i, 2);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getModel(), i, 3);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getStyle(), i, 4);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getVin(), i, 5);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getExteriorColor(), i, 6);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getInteriorColor(), i, 7);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getMiles(), i, 8);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getPrice(), i, 9);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getTransmission(), i, 10);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getEngine(), i, 11);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getImage(), i, 12);
            pnlViewVehicle.getTblViews().setValueAt(v.get(i).getStatus(), i, 13);
        }
    }
}
