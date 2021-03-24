/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import ni.edu.uni.misprogramas.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.misprogramas.backend.pojo.Vehicle;
import ni.edu.uni.misprogramas.backend.pojo.vehicleSubModel;
import ni.edu.uni.misprogramas.views.panels.PnlVehicleRegister;

/**
 *
 * @author Rodrigo
 */
public class PnlVehicleRegisterController {
    
    private PnlVehicleRegister pnlVeRegis;
    private JsonVehicleDaoImpl jvdao;
    private List<vehicleSubModel> vSubModel;
    private Gson gson;
    private DefaultComboBoxModel cmbModelMake;
    
    public PnlVehicleRegisterController(PnlVehicleRegister pnlVeRegis) throws FileNotFoundException {
        this.pnlVeRegis = pnlVeRegis;
        initComponent();
    }
    
    private void initComponent() throws FileNotFoundException {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();
        
        JsonReader jreader = new JsonReader(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))));
        
        vSubModel = gson.fromJson(jreader, List.class);
        
        cmbModelMake = new DefaultComboBoxModel(vSubModel.toArray());
        
        pnlVeRegis.getCmbMake().setModel(cmbModelMake);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equalsIgnoreCase("OK")) {
//            if (pnlVeRegis.getTxtStockNumber().getText().isEmpty() || pnlVeRegis.getTxtYear().getText().isEmpty() || pnlVeRegis.getFtxtVin().getText().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Please complete with all information");
//            } else {
//                create(getVehicle());
//            }
//
//        }
//    }
//    private Vehicle getVehicle() {
//        int stockNumber = Integer.parseInt(pnlVeRegis.getTxtStockNumber().getText());
//        int year = Integer.parseInt(pnlVeRegis.getTxtYear().getText());
//        String make;
//        String model;//30
//        String style;//30
//        String vin;//20
//        String exteriorColor;//30
//        String interiorColor;//30
//        String miles;//7
//        float price;//7
//        Vehicle.Transmission transmission;//20
//        String engine;//50
//        String image;//100
//        String status;//20
//        
//       
//        
//        
//        Vehicle v = new Vehicle(stockNumber, year, make, model, style, vin, exteriorColor, interiorColor, miles, price, transmission, engine, image, status);
//    }
    }
