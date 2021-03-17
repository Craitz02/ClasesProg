/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import ni.edu.uni.misprogramas.views.panels.pnlConversionTemplate;

/**
 *
 * @author Sistemas-09
 */
public class PnlTemperaturaC implements ActionListener {

    private final pnlConversionTemplate pnlConvTemplate;
    private final String TEMPERATURES[] = new String[]{"Celcius", "Fahrenheit"};
    private DefaultComboBoxModel CmbModelFrom;
    private DefaultComboBoxModel CmbModelTo;
    private DecimalFormat dc = new DecimalFormat("#.0");
    
    public PnlTemperaturaC(pnlConversionTemplate pnlConvTemplate) {
        this.pnlConvTemplate = pnlConvTemplate;
        initComponents();
    }
    
    private void initComponents() {
        CmbModelFrom = new DefaultComboBoxModel(TEMPERATURES);
        CmbModelTo = new DefaultComboBoxModel(TEMPERATURES);
        pnlConvTemplate.getCmbFrom().setModel(CmbModelFrom);
        pnlConvTemplate.getCmbTo().setModel(CmbModelTo);
        
        pnlConvTemplate.getBtnCalcular().addActionListener(this);
        pnlConvTemplate.getBtnNew().addActionListener(this);
        
        pnlConvTemplate.getCmbFrom().addItemListener((ItemEvent e) -> {
            conversionEvent();
        });
        
        pnlConvTemplate.getCmbTo().addItemListener((ItemEvent e) -> {
            conversionEvent();
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Convertir")) {
            conversionEvent();
        }
    }
    
    private void conversionEvent() throws NumberFormatException, HeadlessException {
        int cmbFromIndex = pnlConvTemplate.getCmbFrom().getSelectedIndex();
        int cmbToIndex = pnlConvTemplate.getCmbTo().getSelectedIndex();
        double value = 0;
        if (pnlConvTemplate.getTxtValue().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor a convertir");
        } else {
            value = Double.parseDouble(pnlConvTemplate.getTxtValue().getText());
        }
        pnlConvTemplate.getLblResult().setText("Resultado: " + dc.format(convertidorTemperature(value, cmbFromIndex, cmbToIndex)));
    }
    
    private double convertidorTemperature(double value, int from, int to) {
        switch (from) {
            case 0:
                switch (to) {
                    case 0:
                        return value;
                    case 1:
                        return celciusToFahrenheit(value);
                    
                }
            case 1:
                switch (to) {
                    case 0:
                        return fahrenheitToCelcius(value);
                    case 1:
                        return value;
                }
        }
        return value;
    }
    
    private double celciusToFahrenheit(double value) {
        return (((double) value * 9 / 5) + 32);
    }
    
    private double fahrenheitToCelcius(double value) {
        return (((double) value - 32) * 5 / 9);
    }
}
