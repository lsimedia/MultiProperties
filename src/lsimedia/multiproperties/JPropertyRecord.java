/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsimedia.multiproperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author sbodmer
 */
public class JPropertyRecord extends javax.swing.JPanel implements RecordGUI, ActionListener {

    PropertyRecord pr = null;
    javax.swing.Timer timer = null;

    /**
     * The selected one when dialig was opened so the user can directly see
     * the correct column
     */
    JColumnValue selected = null;
    
    public JPropertyRecord(PropertyRecord pr, MultiPropertiesTableModel model, int selectedColumn) {
        this.pr = pr;

        initComponents();

        TF_Name.setText(pr.name);
        TA_Default.setText(pr.defaultValue);
        CB_Disabled.setSelected(pr.disabled);
        TA_Description.setText(pr.description);

        for (int i = 0;i < pr.values.size();i++) {
            PropertyRecord.Value v = pr.values.get(i);

            //--- Add 1 to the column index, because the column 0 is the Key
            JColumnValue jc = new JColumnValue(model.getColumnName(i+1), v.disabled, v.value, pr.defaultValue);
            PN_Columns.add(jc);

            if (selectedColumn == (i+1)) selected = jc;
            
        }
        
        timer = new javax.swing.Timer(1000, this);
        timer.start();
    }

    //**************************************************************************
    //*** PropertyGUI
    //**************************************************************************
    public void apply() {
        timer.stop();

        pr.name = TF_Name.getText();
        pr.disabled = CB_Disabled.isSelected();
        pr.description = TA_Description.getText().trim();

        pr.defaultValue = TA_Default.getText();
        if (pr.defaultValue.contains("\n")) pr.multiLine = true;

        for (int i = 0;i < PN_Columns.getComponentCount();i++) {
            JColumnValue jc = (JColumnValue) PN_Columns.getComponent(i);

            PropertyRecord.Value v = pr.values.get(i);
            v.disabled = jc.isDisabled();
            v.value = jc.getValue();
            if (v.value.contains("\n")) pr.multiLine = true;

        }

    }

    @Override
    public void cancel() {
        timer.stop();
    }

    @Override
    public JComponent getVisual() {
        return this;
    }

    //**************************************************************************
    //*** ActionListener
    //**************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            //--- Update the default values
            for (int i = 0;i < PN_Columns.getComponentCount();i++) {
                JColumnValue jc = (JColumnValue) PN_Columns.getComponent(i);
                if (jc.isDisabled()) jc.setValue(TA_Default.getText());
                
            }
            
            //--- Scroll to selected field
            if (selected != null) {
                PN_Columns.scrollRectToVisible(selected.getBounds());
                selected.focus();
                selected = null;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        PN_General = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TF_Name = new javax.swing.JTextField();
        CB_Disabled = new javax.swing.JCheckBox();
        SP_Columns = new javax.swing.JScrollPane();
        PN_Columns = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TA_Default = new javax.swing.JTextArea();
        PN_Description = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TA_Description = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Key");

        TF_Name.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        CB_Disabled.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        CB_Disabled.setText("Disabled");

        PN_Columns.setLayout(new javax.swing.BoxLayout(PN_Columns, javax.swing.BoxLayout.Y_AXIS));
        SP_Columns.setViewportView(PN_Columns);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Default value");

        TA_Default.setColumns(20);
        TA_Default.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        TA_Default.setRows(5);
        jScrollPane3.setViewportView(TA_Default);

        javax.swing.GroupLayout PN_GeneralLayout = new javax.swing.GroupLayout(PN_General);
        PN_General.setLayout(PN_GeneralLayout);
        PN_GeneralLayout.setHorizontalGroup(
            PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_GeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Columns)
                    .addGroup(PN_GeneralLayout.createSequentialGroup()
                        .addGroup(PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PN_GeneralLayout.createSequentialGroup()
                                .addComponent(TF_Name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CB_Disabled))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PN_GeneralLayout.setVerticalGroup(
            PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_GeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_Disabled))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PN_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SP_Columns, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("General", PN_General);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Property description");

        TA_Description.setColumns(20);
        TA_Description.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        TA_Description.setRows(5);
        jScrollPane2.setViewportView(TA_Description);

        javax.swing.GroupLayout PN_DescriptionLayout = new javax.swing.GroupLayout(PN_Description);
        PN_Description.setLayout(PN_DescriptionLayout);
        PN_DescriptionLayout.setHorizontalGroup(
            PN_DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_DescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN_DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
                .addContainerGap())
        );
        PN_DescriptionLayout.setVerticalGroup(
            PN_DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_DescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Description", PN_Description);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_Disabled;
    private javax.swing.JPanel PN_Columns;
    private javax.swing.JPanel PN_Description;
    private javax.swing.JPanel PN_General;
    private javax.swing.JScrollPane SP_Columns;
    private javax.swing.JTextArea TA_Default;
    private javax.swing.JTextArea TA_Description;
    private javax.swing.JTextField TF_Name;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
