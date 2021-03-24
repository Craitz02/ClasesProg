/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ni.edu.uni.misprogramas.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.misprogramas.backend.pojo.Vehicle;
import ni.edu.uni.misprogramas.views.panels.PnlVehicleRegister;

/**
 *
 * @author Rodrigo
 */
public class PnlVehicleRegisterController extends JsonVehicleDaoImpl implements ActionListener {

    private final PnlVehicleRegister pnlVeRegis;

    public PnlVehicleRegisterController(PnlVehicleRegister pnlVeRegis) {
        this.pnlVeRegis = pnlVeRegis;
        initComponent();
    }

    private void initComponent() {
        pnlVeRegis.getBtnOK().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("OK")) {
            if (pnlVeRegis.getTxtStockNumber().getText().isEmpty() || pnlVeRegis.getTxtYear().getText().isEmpty() || pnlVeRegis.getFtxtVin().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete with all information");
            } else {
                create(getVehicle());
            }

        }
    }

    private Vehicle getVehicle() {
        int stockNumber = Integer.parseInt(pnlVeRegis.getTxtStockNumber().getText());
        int year = Integer.parseInt(pnlVeRegis.getTxtYear().getText());
        String make;
        String model;//30
        String style;//30
        String vin;//20
        String exteriorColor;//30
        String interiorColor;//30
        String miles;//7
        float price;//7
        Vehicle.Transmission transmission;//20
        String engine;//50
        String image;//100
        String status;//20
        
       
        
        
        Vehicle v = new Vehicle(stockNumber, year, make, model, style, vin, exteriorColor, interiorColor, miles, price, transmission, engine, image, status);
    }

}
