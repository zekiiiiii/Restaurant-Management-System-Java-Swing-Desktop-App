package rms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ViewBill extends javax.swing.JFrame implements ActionListener {
JLabel title,name,catagory,price,quantity;
JTextField nametf,pricetf;
JComboBox catagorycb;
JButton back,items,selling,viewBill,tableReservation,logout,add,update,delete,refresh;
JPanel mainpanel,panelnorth,panelcenter,panelcenter1,
        panelcenter2,panelcenter3,panelcenter4,
        panelt,panelb;
JSpinner quantitysp;
    public ViewBill() {
        initComponents();
mainpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelnorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 0));
title = new JLabel("VIEW BILL ");
title.setFont(new Font("Times New Roman", Font.BOLD, 35));
back=new JButton("Back");
back.setFont(new Font("Times New Roman", Font.BOLD, 14));
back.setBounds(90, 180, 100, 30);
back.setLayout(new FlowLayout(FlowLayout.LEFT));
panelnorth.add(back, BorderLayout.WEST);
panelnorth.setBackground(Color.orange);
panelnorth.add(title);
panelnorth.setPreferredSize(new Dimension(1000, 60));
panelcenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 0));
back.addActionListener(this);
mainpanel.add(panelnorth, BorderLayout.NORTH);
mainpanel.add(panelcenter, BorderLayout.CENTER);
add(mainpanel,BorderLayout.CENTER);
setContentPane(mainpanel);
setSize(1000,700);
setTitle("Restaurant Management System"); 
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
validate();
repaint();
    }
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
      /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new login().setVisible(true);
                new ViewBill().setVisible(true);  
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==back){
           new Restaurant().setVisible(true);
        }  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
