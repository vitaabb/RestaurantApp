/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tanysha
 */
public class HistoryPanel extends javax.swing.JPanel {

    /**
     * Creates new form HistoryPanel
     */
    private static HistoryPanel reference;
    private static final Object[] columnNames = { "Id","Dishes", "Total amount", "Waiter"};
    private DefaultTableModel model; 
    Object[][] hist ;
    private HistoryPanel() {
        model = new DefaultTableModel(null, columnNames);
        initComponents();
        setBackground(Color.getHSBColor(276,9,95));
        writingData ();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("History");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(332, 332, 332))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
 public static HistoryPanel getReference(){
         if (reference==null){
             reference = new HistoryPanel();
         }
         return reference;
     }
  public void writingData (){
       model.setDataVector(null, columnNames);
       try {
          int i=0; 
          //Manager.getThread().addEmployee(999678687, "terte","sfsdf", 55);
          System.out.print(Manager.getThread().getEmployees().toArray().length);
          hist=new Object[Manager.getThread().getEmployees().toArray().length][4];
          while(i<Manager.getThread().getEmployees().toArray().length){
               
               //dish.removeAllElements();
              
               hist[i][0]= Manager.getThread().getEmployees().get(i).getId();
               hist[i][1]=Manager.getThread().getEmployees().get(i).getName();
               hist[i][2]= Manager.getThread().getEmployees().get(i).getSurname();
               hist[i][3]= Manager.getThread().getEmployees().get(i).getSalary(); 
               
              
               i++;
               
          }
           
         
         }
          catch (IOException ex) {
            Logger.getLogger(HistoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.setDataVector(hist, columnNames);
          
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
