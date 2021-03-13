/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import ni.edu.uni.misprogramas.views.panels.pnlTemperatura;

/**
 *
 * @author Rodrigo
 */
public class pnlTemperaturaController implements ActionListener {

    private final pnlTemperatura pnlTemp;
    private final DecimalFormat df = new DecimalFormat("#.0");

    public pnlTemperaturaController(pnlTemperatura pnlTemp) {
        this.pnlTemp = pnlTemp;
        initComponent();
    }

    private void initComponent() {
        pnlTemp.getBtnConvToC().addActionListener(this);
        pnlTemp.getBtnConvToF().addActionListener(this);
        pnlTemp.getBtnNew().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Convertir a F°")) {
            double c;

            if (pnlTemp.getTxtCelcious().getText() == null) {
                c = 0;
            } else {
                c = Double.parseDouble(pnlTemp.getTxtCelcious().getText());
                pnlTemp.getTxtFahren().setText(String.valueOf(df.format(convAF(c))));
            }
            
        }
        if(e.getActionCommand().equalsIgnoreCase("Convertir a C°")){
            double f;
            if (pnlTemp.getTxtFahren().getText() == null) {
                f = 0;
            } else {
                f = Double.parseDouble(pnlTemp.getTxtFahren().getText());
                pnlTemp.getTxtCelcious().setText(String.valueOf(df.format(convAC(f))));
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Nuevo")) {
            borrar();
        }
    }

    private double convAC(double a) {
        return (a - 32) * 5 / 9;
    }

    private double convAF(double a) {
        return (a * 9 / 5) + 32;
    }

    public void borrar() {
        pnlTemp.getTxtCelcious().setText("");
        pnlTemp.getTxtFahren().setText("");

    }
}
