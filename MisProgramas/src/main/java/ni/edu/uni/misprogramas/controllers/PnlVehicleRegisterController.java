/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
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
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private String status[] = new String[]{"Active","Maintainance","Not avaliable"};
    private JFileChooser fileChooser;
    
    
    public PnlVehicleRegisterController(PnlVehicleRegister pnlVeRegis) throws FileNotFoundException {
        this.pnlVeRegis = pnlVeRegis;
        initComponent();
    }
    
    private void initComponent() throws FileNotFoundException {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();
        
        JsonReader jreader = new JsonReader(
               new BufferedReader(new InputStreamReader(
                       getClass().getResourceAsStream("/jsons/vehicleData.json")))
        );
        
        Type listType = new TypeToken<ArrayList<vehicleSubModel>>(){}.getType();
        vSubModel = gson.fromJson(jreader, listType);
        
        List<String> makes = vSubModel.stream().map(x -> x.getMake()).collect(Collectors.toList());
        List<String> models = vSubModel.stream().map(x -> x.getModel()).collect(Collectors.toList());
        List<String> colors = vSubModel.stream().map(x -> x.getColor()).collect(Collectors.toList());
        
        cmbModelMake = new DefaultComboBoxModel(makes.toArray());
        cmbModelModel = new DefaultComboBoxModel(models.toArray());
        cmbModelEColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelIColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelStatus = new DefaultComboBoxModel(status);
        
        pnlVeRegis.getCmbMake().setModel(cmbModelMake);
        pnlVeRegis.getCmbModel().setModel(cmbModelModel);
        pnlVeRegis.getCmbExternalColor().setModel(cmbModelEColor);
        pnlVeRegis.getCmbInternalColor().setModel(cmbModelIColor);
        pnlVeRegis.getCmbStatus().setModel(cmbModelStatus);
        
        pnlVeRegis.getBtnSave().addActionListener((actionEvent)-> {
            btnSaveActionListener(actionEvent);
        });
        
        pnlVeRegis.getBtnBrowse().addActionListener(((e)->{
            
        }));
    }

    private void btnSaveActionListener(ActionEvent e){
        int stock, year;
        String make,model,style,vin,eColor,iColor,miles,engine,image,status;
        float price;
        Vehicle.Transmission transmission;
        
        
        stock = Integer.parseInt(pnlVeRegis.getTxtStockNumber().getText());
        year = Integer.parseInt(pnlVeRegis.getSpnYear().getModel().getValue().toString());
        make = pnlVeRegis.getCmbMake().getSelectedItem().toString();
        model = pnlVeRegis.getCmbModel().getSelectedItem().toString();
        style = pnlVeRegis.getTxtStyle().getText();
        vin = pnlVeRegis.getFtxtVin().getText();
        eColor = pnlVeRegis.getCmbExternalColor().getSelectedItem().toString();
        iColor = pnlVeRegis.getCmbInternalColor().getSelectedItem().toString();
        miles = pnlVeRegis.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlVeRegis.getSpnPrice().getModel().getValue().toString());
        transmission = pnlVeRegis.getRbtnAuto().isSelected() ?
                Vehicle.Transmission.AUTOMATIC : Vehicle.Transmission.MANUAL;
        engine = pnlVeRegis.getTxtEngine().getText();
        image = pnlVeRegis.getTxtImage().getText();
        status = pnlVeRegis.getCmbStatus().getSelectedItem().toString();
        
        
        Vehicle v = new Vehicle(stock, year, make, model, style, vin, iColor, iColor, miles, price, transmission, engine, image, status);
        try {
            vehicleValidation(v);
            jvdao.create(v);
            JOptionPane.showMessageDialog(null, "Vehicle save sucessfully.","Saved message",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(PnlVehicleRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        
    }   catch (Exception ex) {
            Logger.getLogger(PnlVehicleRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    private void btnBrowseActionListener(ActionEvent e){
        fileChooser = new JFileChooser();
        
        int option = fileChooser.showOpenDialog(null);
        
        if(option == JFileChooser.CANCEL_OPTION){
            return;
        }
        
        File imageFile = fileChooser.getSelectedFile();
        pnlVeRegis.getTxtImage().setText(imageFile.getPath());
    }
    
    private void vehicleValidation(Vehicle v) throws Exception{
        if(v.getStockNumber()<=0){
            throw new Exception("StockNumber can not be less or equal to zero.");
        }
        if(v.getVin().isEmpty()||v.getVin().isBlank()){
            throw new Exception("Vin number can not be empty or blank.");
        }
        if(v.getEngine().isEmpty()){
            throw new Exception("Engine can not be empty.");
        }
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
