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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import ni.edu.uni.misprogramas.backend.dao.implementation.JsonVehicleDaoImpl;
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
    //private final String colums[] = new String[]{"Stock Number", "Year", "Make", "Model", "Style", "VIN", "Exterior Color", "Internal Color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public PnlViewVehicleController(PnlViewVehicle pnlViewVehicle) throws IOException {
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
                String text = (String) pnlViewVehicle.getTxtBrowse().getText();

                pnlViewVehicle.getTxtBrowse().setText(text);

                Filtrar(tablefilter);
            }
        });
    }

    private void Filtrar(TableRowSorter filter) {
        filter.setRowFilter(RowFilter.regexFilter(pnlViewVehicle.getTxtBrowse().getText(),0,1,2,3,4,5,6,7,8));
        pnlViewVehicle.getTblViews().setRowSorter(filter);
    }

    private void btnViewAllActionListener(ActionEvent e) throws IOException {
        vehicles = (List<Vehicle>)jvdao.getAll();
        TableModel.setNumRows(vehicles.size());
        for (int i = 0; i < vehicles.size(); i++) {
            TableModel.setValueAt(vehicles.get(i).getStockNumber(), i, 0);
            TableModel.setValueAt(vehicles.get(i).getYear(), i, 1);
            TableModel.setValueAt(vehicles.get(i).getMake(), i, 2);
            TableModel.setValueAt(vehicles.get(i).getModel(), i, 3);
            TableModel.setValueAt(vehicles.get(i).getStyle(), i, 4);
            TableModel.setValueAt(vehicles.get(i).getVin(), i, 5);
            TableModel.setValueAt(vehicles.get(i).getExteriorColor(), i, 6);
            TableModel.setValueAt(vehicles.get(i).getInteriorColor(), i, 7);
            TableModel.setValueAt(vehicles.get(i).getMiles(), i, 8);
            TableModel.setValueAt(vehicles.get(i).getPrice(), i, 9);
            TableModel.setValueAt(vehicles.get(i).getTransmission(), i, 10);
            TableModel.setValueAt(vehicles.get(i).getEngine(), i, 11);
            TableModel.setValueAt(vehicles.get(i).getImage(), i, 12);
            TableModel.setValueAt(vehicles.get(i).getStatus(), i, 13);

        }
        pnlViewVehicle.getBtnViewAll().setText("View All");

    }
}
