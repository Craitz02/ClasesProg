/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ni.edu.uni.misprogramas.views.panels.pnlCalculadora;

/**
 *
 * @author Rodrigo
 */
public class pnlCalculadoraController implements ActionListener {
    private final pnlCalculadora pnlCalc;

    public pnlCalculadoraController(pnlCalculadora pnlCalc) {
        this.pnlCalc = pnlCalc;
        initComponent();
    }
   
    private void initComponent(){
        pnlCalc.getBtnCalc().addActionListener(this);
        pnlCalc.getBtnNew().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Calcular")){
            double n1,n2;
            n1 = Double.parseDouble(pnlCalc.getTxtNumber1().getText());
            n2 = Double.parseDouble(pnlCalc.getTxtNumber2().getText());
            
            pnlCalc.getTxtResult().setText(String.valueOf(suma(n1,n2)));
        }
        
        if(e.getActionCommand().equalsIgnoreCase("Nuevo")){
            borrar();
        }
        
    }
    
    private double suma(double a, double b){
        return a+b;
    }
    
    public void borrar(){
        pnlCalc.getTxtNumber1().setText("");
        pnlCalc.getTxtNumber2().setText("");
        pnlCalc.getTxtResult().setText("");
        
    }
}
