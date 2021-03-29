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
import ni.edu.uni.misprogramas.views.panels.PnlNewVehicle;

/**
 *
 * @author Rodrigo
 */
public class PnlNewVehicleController {

    private PnlNewVehicle pnlNewVehicle;
    private JsonVehicleDaoImpl jvdao;
    private List<vehicleSubModel> vSubModel;
    private Gson gson;
    private DefaultComboBoxModel cmbModelMake;
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private String status[] = new String[]{"Active", "Maintainance", "Not avaliable"};
    private JFileChooser fileChooser;

    public PnlNewVehicleController(PnlNewVehicle pnlNewVehicle) throws FileNotFoundException {
        this.pnlNewVehicle = pnlNewVehicle;
        initComponent();
    }

    private void initComponent() throws FileNotFoundException {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();

        JsonReader jreader = new JsonReader(
                new BufferedReader(new InputStreamReader(
                        getClass().getResourceAsStream("/json/vehicleData_1.json")))
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

        pnlNewVehicle.getCmbMake().setModel(cmbModelMake);
        pnlNewVehicle.getCmbModel().setModel(cmbModelModel);
        pnlNewVehicle.getCmbExternalColor().setModel(cmbModelEColor);
        pnlNewVehicle.getCmbInternalColor().setModel(cmbModelIColor);
        pnlNewVehicle.getCmbStatus().setModel(cmbModelStatus);

        pnlNewVehicle.getBtnSave().addActionListener((actionEvent) -> {
            btnSaveActionListener(actionEvent);
        });

        pnlNewVehicle.getBtnBrowse().addActionListener(((e) -> {
            btnBrowseActionListener(e);
        }));
    }

    private void btnSaveActionListener(ActionEvent e) {
        int stock, year;
        String make, model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission;

        stock = Integer.parseInt(pnlNewVehicle.getTxtStockNumber().getText());
        year = Integer.parseInt(pnlNewVehicle.getSpnYear().getModel().getValue().toString());
        make = pnlNewVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlNewVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlNewVehicle.getTxtStyle().getText();
        vin = pnlNewVehicle.getFtxtVin().getText();
        eColor = pnlNewVehicle.getCmbExternalColor().getSelectedItem().toString();
        iColor = pnlNewVehicle.getCmbInternalColor().getSelectedItem().toString();
        miles = pnlNewVehicle.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlNewVehicle.getSpnPrice().getModel().getValue().toString());
        transmission = pnlNewVehicle.getRbtnAuto().isSelected()
                ? Vehicle.Transmission.AUTOMATIC : Vehicle.Transmission.MANUAL;
        engine = pnlNewVehicle.getTxtEngine().getText();
        image = pnlNewVehicle.getTxtImage().getText();
        status = pnlNewVehicle.getCmbStatus().getSelectedItem().toString();

        Vehicle v = new Vehicle(stock, year, make, model, style, vin, eColor, iColor, miles, price, transmission, engine, image, status);
        try {
            vehicleValidation(v);
            jvdao.create(v);
            JOptionPane.showMessageDialog(null, "Vehicle save sucessfully.", "Saved message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(PnlNewVehicleController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error Message", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PnlNewVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    

    private void btnBrowseActionListener(ActionEvent e) {
        fileChooser = new JFileChooser();

        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File imageFile = fileChooser.getSelectedFile();
        pnlNewVehicle.getTxtImage().setText(imageFile.getPath());
    }

    private void vehicleValidation(Vehicle v) throws Exception {
        if (v.getStockNumber() <= 0) {
            throw new Exception("StockNumber can not be less or equal to zero.");
        }
        if (v.getVin().isEmpty() || v.getVin().isBlank()) {
            throw new Exception("Vin number can not be empty or blank.");
        }
        if (v.getEngine().isEmpty()) {
            throw new Exception("Engine can not be empty or blank.");
        }
    }
}
