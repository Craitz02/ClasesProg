/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import ni.edu.uni.misprogramas.views.panels.pnlConversionTemplate;

/**
 *
 * @author Sistemas-09
 */
public class PnlTemperaturaC implements ActionListener{
    private final pnlConversionTemplate pnlConvTemplate;
    private final String TEMPERATURES[] = new String[]{"Celcius","Fahrenheit"};
    private DefaultComboBoxModel CmbModelFrom;
    private DefaultComboBoxModel CmbModelTo;
    
    public PnlTemperaturaC(pnlConversionTemplate pnlConvTemplate) {
        this.pnlConvTemplate = pnlConvTemplate;
        initComponents();
    }
    
    private void initComponents(){
        CmbModelFrom = new DefaultComboBoxModel(TEMPERATURES);
        CmbModelTo = new DefaultComboBoxModel(TEMPERATURES);
        pnlConvTemplate.getCmbFrom().setModel(temperaturaCmbModel);
        pnlConvTemplate.getCmbTo().setModel(temperaturaCmbModel);
        
        pnlConvTemplate.getBtnCalcular().addActionListener(this);
        pnlConvTemplate.getBtnNew().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Convertir")){
            int cmbFromIndex = pnlConvTemplate.getCmbFrom().getSelectedIndex();
            int cmbToIndex = pnlConvTemplate.getCmbTo().getSelectedIndex();
            double value = Double.parseDouble(pnlConvTemplate.getTxtValue().getText());
            pnlConvTemplate.getLblResult().setText("Resultado: " + convertidorTemperature(value, cmbFromIndex, cmbToIndex));
        }
    }
    
    private double convertidorTemperature(double value, int from, int to){
        switch(from){
            case 0:
                switch(to){
                    case 0:
                        return value;
                    case 1:
                        return celciusToFahrenheit(value);
                        
                }
            case 1:
                switch(to){
                    case 0:
                        return fahrenheitToCelcius(value);
                    case 1:
                        return value;
                }
        }
        return value;
    }
    
    private double celciusToFahrenheit(double value){
        return (((double)value*9/5)+32);
    }
    
    private double fahrenheitToCelcius(double value){
        return (((double)value-32)*5/9);
    }
}
