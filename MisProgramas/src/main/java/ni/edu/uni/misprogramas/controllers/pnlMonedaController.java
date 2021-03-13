/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import ni.edu.uni.misprogramas.views.panels.pnlMoneda;

/**
 *
 * @author Rodrigo
 */
public class pnlMonedaController implements ActionListener {

    private final pnlMoneda pnlMone;
    private final DecimalFormat df = new DecimalFormat("#.0");

    public pnlMonedaController(pnlMoneda pnlMone) {
        this.pnlMone = pnlMone;
        initComponent();
    }

    private void initComponent() {
        pnlMone.getBtnConvToCordo().addActionListener(this);
        pnlMone.getBtnConvToDollar().addActionListener(this);
        pnlMone.getBtnNew().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Convertir a U$")) {
            double n;
            if (pnlMone.getTxtCordoba()==null) {
                n=0;
            }else{
                n = Double.parseDouble(pnlMone.getTxtCordoba().getText());
                pnlMone.getTxtDolar().setText(String.valueOf(df.format(n/35)));
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Convertir a C$"))  {
            double n;
            if (pnlMone.getTxtDolar()==null) {
                n=0;
            }else{
                n = Double.parseDouble(pnlMone.getTxtDolar().getText());
                pnlMone.getTxtCordoba().setText(String.valueOf(df.format(n*35)));
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Nuevo")) {
            borrar();
        }
    }

    private void borrar() {
        pnlMone.getTxtCordoba().setText("");
        pnlMone.getTxtDolar().setText("");
    }
}
