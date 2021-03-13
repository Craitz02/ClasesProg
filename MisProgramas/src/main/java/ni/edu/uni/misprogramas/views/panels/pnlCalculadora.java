/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.misprogramas.views.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Rodrigo
 */
public class pnlCalculadora extends javax.swing.JPanel {

    /**
     * Creates new form pnlCalculadora
     */
    public pnlCalculadora() {
        initComponents();
    }

    public JButton getBtnCalc() {
        return btnCalc;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JTextField getTxtNumber1() {
        return txtNumber1;
    }

    public JTextField getTxtNumber2() {
        return txtNumber2;
    }

    public JTextField getTxtResult() {
        return txtResult;
    }

    public void setBtnCalc(JButton btnCalc) {
        this.btnCalc = btnCalc;
    }

    public void setBtnNew(JButton btnNew) {
        this.btnNew = btnNew;
    }

    public void setTxtNumber1(JTextField txtNumber1) {
        this.txtNumber1 = txtNumber1;
    }

    public void setTxtNumber2(JTextField txtNumber2) {
        this.txtNumber2 = txtNumber2;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btnCalc = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumber1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNumber2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtResult = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(300, 220));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnCalc.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        btnCalc.setText("Calcular");
        btnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalc);

        btnNew.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        btnNew.setText("Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Eras Light ITC", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Numero 1:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtNumber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumber1ActionPerformed(evt);
            }
        });
        txtNumber1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumber1KeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(txtNumber1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Eras Light ITC", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Numero 2:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtNumber2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumber2KeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(txtNumber2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Eras Light ITC", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Resultado: ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtResult.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(txtResult, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumber1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumber1ActionPerformed

    private void txtNumber1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumber1KeyTyped
        if (!Character.isDigit(evt.getKeyChar())&&evt.getKeyChar()!= '.') {
            evt.consume();
        }
        if (evt.getKeyChar()=='.'&&txtNumber1.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumber1KeyTyped

    private void txtNumber2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumber2KeyTyped
        if (!Character.isDigit(evt.getKeyChar())&&evt.getKeyChar()!= '.') {
            evt.consume();
        }
        if (evt.getKeyChar()=='.'&&txtNumber2.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumber2KeyTyped

    private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalcActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewActionPerformed
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalc;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtNumber1;
    private javax.swing.JTextField txtNumber2;
    private javax.swing.JTextField txtResult;
    // End of variables declaration//GEN-END:variables
}
