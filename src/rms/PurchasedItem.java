package rms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import java.text.ParseException;

// ...
public class PurchasedItem extends javax.swing.JFrame implements ActionListener {
JPanel mainpanel,panelnorth,panelcenter,panelbottom,panelbottomtable;
JLabel title,name,quantity,quality,price,expiredate;
JTextField nametf,quantitytf,pricetf,expiredatetf;
JComboBox qualitycb;
DateFormat dateformat;
DateFormatter dateformatter;

JButton add,display,update,remove,refresh,back;
DefaultTableModel model;
JTable tableItem;
   java.sql.Connection conn=null;
   String url="jdbc:mysql://localhost:3306/restuarant";
   String dbun="root";
   String pwd="";
   PreparedStatement pstm=null;
   ResultSet rs=null;
    public PurchasedItem() {
        initComponents();
mainpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));//////////////
//mainpanel.setBackground(Color.red);
panelnorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 0));//////////////
title = new JLabel("PURCHASED ITEMS");
title.setFont(new Font("Times New Roman", Font.BOLD, 35));
back=new JButton("Back");
back.setFont(new Font("Times New Roman", Font.BOLD, 14));
back.setBounds(90, 180, 100, 30);
back.setLayout(new FlowLayout(FlowLayout.LEFT));
panelnorth.setBackground(Color.orange);
panelnorth.setPreferredSize(new Dimension(1000, 60));
panelnorth.add(back, BorderLayout.WEST);
panelnorth.add(title);
mainpanel.add(panelnorth, BorderLayout.NORTH);

panelcenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelcenter.setPreferredSize(new Dimension(1000, 85));
mainpanel.add(panelcenter, BorderLayout.CENTER);

name=new JLabel("Item Name");
name.setHorizontalAlignment(SwingConstants.CENTER);
name.setFont(new Font("Times New Roman", Font.BOLD, 14));
name.setBounds(0, 0, 100, 30);
nametf = new JTextField(6);
nametf.setFont(new Font("Times New Roman", Font.BOLD, 14));
nametf.setBounds(30, 180, 100, 30);
 
quantity=new JLabel("Quantity");
quantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
quantitytf= new JTextField(3);
quantitytf.setFont(new Font("Times New Roman", Font.BOLD, 14));

quality =new JLabel("Quality");
quality.setFont(new Font("Times New Roman", Font.BOLD, 14));
quality.setBounds(40, 180, 100, 30);

qualitycb = new JComboBox();
// Assuming 'list' is an array of strings
String[] list = {"High ", "Better ", "Medium"};
qualitycb = new JComboBox<>(list);
qualitycb.setBounds(50, 180, 100, 30);

price=new JLabel("Price");
price.setFont(new Font("Times New Roman", Font.BOLD, 14));
price.setBounds(60, 180, 100, 30);

pricetf=new JTextField(4);
pricetf.setFont(new Font("Times New Roman", Font.BOLD, 14));
pricetf.setPreferredSize(new Dimension(55, 30));


expiredate=new JLabel("Expire date");
expiredate.setFont(new Font("Times New Roman", Font.BOLD, 14));
expiredate.setBounds(60, 180, 100, 30);

dateformat = new SimpleDateFormat("dd/mm/yyyy");
dateformatter = new DateFormatter(dateformat);
expiredatetf=new JFormattedTextField(dateformatter);
expiredatetf=new JFormattedTextField();

expiredatetf.setFont(new Font("Times New Roman", Font.BOLD, 14));
expiredatetf.setBounds(60, 180, 100, 30);
expiredatetf.setPreferredSize(new Dimension(75, 30));

add=new JButton("Add");
add.setFont(new Font("Times New Roman", Font.BOLD, 14));
add.setBounds(80, 180, 100, 30);
add.setPreferredSize(new Dimension(55, 40));

display=new JButton("Display");
display.setFont(new Font("Times New Roman", Font.BOLD, 14));
display.setBounds(80, 180, 100, 30);
display.setPreferredSize(new Dimension(75, 40));

update=new JButton("Update");
update.setFont(new Font("Times New Roman", Font.BOLD, 14));
update.setBounds(90, 180, 100, 30);
update.setPreferredSize(new Dimension(75, 40));


remove=new JButton("Remove");
remove.setFont(new Font("Times New Roman", Font.BOLD, 14));
remove.setBounds(100, 180, 100, 30);
remove.setHorizontalAlignment(SwingConstants.LEFT);
remove.setPreferredSize(new Dimension(78, 40));


refresh=new JButton("Refresh");
refresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
refresh.setBounds(100, 180, 100, 30);
refresh.setHorizontalAlignment(SwingConstants.LEFT);



panelcenter.add(name);
panelcenter.add(nametf);
panelcenter.add(quantity);
panelcenter.add(quantitytf);
panelcenter.add(quality);
panelcenter.add(qualitycb);
panelcenter.add(price);
panelcenter.add(pricetf);
panelcenter.add(expiredate);
panelcenter.add(expiredatetf);

panelcenter.add(add);
panelcenter.add(display);
panelcenter.add(update);
panelcenter.add(remove);
panelcenter.add(refresh);

panelbottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelbottom.setPreferredSize(new Dimension(950, 530));
panelbottom.setBackground(Color.gray);
mainpanel.add(panelbottom, BorderLayout.SOUTH);

panelbottomtable=new JPanel(new FlowLayout(FlowLayout.CENTER));
panelbottomtable.setPreferredSize(new Dimension(800, 510));
panelbottomtable.setBackground(Color.yellow);
panelbottom.add(panelbottomtable, BorderLayout.SOUTH);

String[] columnNames = {"No", "Item_Name", "Quantity",
    "Quality", "Price","expireDate","Total_Price"};
model = new DefaultTableModel(columnNames, 0);
tableItem = new JTable(model);
JScrollPane scrollPane = new JScrollPane(tableItem);
scrollPane.setPreferredSize(new Dimension(750, 440));
panelbottomtable.add(scrollPane, BorderLayout.CENTER);
JLabel dir = new JLabel("Click row to remove or update the data!");
dir.setFont(new Font("Times New Roman", Font.BOLD, 20));

panelbottomtable.add(dir);


//////////////////////////////////
add.addActionListener( this);
display.addActionListener( this);
update.addActionListener( this);
remove.addActionListener( this);
refresh.addActionListener( this);
back.addActionListener( this);
///////////////////////////////////

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
            java.util.logging.Logger.getLogger(PurchasedItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchasedItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchasedItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchasedItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
           /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchasedItem().setVisible(false);
                new login().setVisible(true);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
           new Restaurant().setVisible(true);
        } 
        
 
        
    if (e.getSource() == add) {
    String itemName = nametf.getText();
    String itemQuantity = quantitytf.getText();
    String itemQuality = qualitycb.getSelectedItem().toString();
    String Price = pricetf.getText();
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date expireDate=null;
            try {
                expireDate = dateFormat.parse(expiredatetf.getText());
            } catch (ParseException ex) {
                Logger.getLogger(PurchasedItem.class.getName()).log(Level.SEVERE, null, ex);
            }
    if (validateitemDetails(itemName, itemQuantity, itemQuality,Price, expireDate)) {
        String insertQuery = "INSERT INTO item (itemName, itemQuantity, itemQuality,itemPrice, expireDate) VALUES (?, ?, ?, ?, ?)";

        try {
           conn = DriverManager.getConnection(url, dbun, pwd);
           pstm = conn.prepareStatement(insertQuery, pstm.RETURN_GENERATED_KEYS);
           pstm.setString(1, itemName);
           pstm.setString(2, itemQuantity);
           pstm.setString(3, itemQuality);
           pstm.setString(4, Price); 
           pstm.setDate(5, new java.sql.Date(expireDate.getTime()));
           pstm.executeUpdate();

           JOptionPane.showMessageDialog(this, "Item Added Successfully");
           conn.close();
           pstm.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
           ex.printStackTrace();
        }
    }
}
     if (e.getSource() == display) {
    // Clear the existing table data
    model = (DefaultTableModel) tableItem.getModel();
    model.setRowCount(0);

    String selectQuery = "SELECT itemNum, itemName, itemQuantity, itemQuality, itemPrice, expireDate, totalPrice FROM item";
    try { 
         conn = DriverManager.getConnection(url, dbun, pwd);
         pstm = conn.prepareStatement(selectQuery);
         rs = pstm.executeQuery();
        // Iterate over the result set and populate the table model
            while (rs.next()) {
            Object[] rowData = new Object[7];
            rowData[0] = rs.getInt("itemNum");
            rowData[1] = rs.getString("itemName");
            rowData[2] = rs.getString("itemQuantity");
            rowData[3] = rs.getString("itemQuality");
            rowData[4] = rs.getString("itemPrice");
            rowData[5] = rs.getString("expireDate");
            rowData[6] = rs.getString("totalPrice");
            model.addRow(rowData);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    
     
   if (e.getSource() == refresh) {
    // Clear the existing table data
    DefaultTableModel tableModel = (DefaultTableModel) tableItem.getModel();
    tableModel.setRowCount(0);

    String selectQuery = "SELECT itemNum, itemName, itemQuantity, itemQuality, itemPrice, expireDate FROM item";

    try { conn = DriverManager.getConnection(url, dbun, pwd);
          pstm = conn.prepareStatement(selectQuery);
          rs = pstm.executeQuery();
        // Iterate over the result set and populate the table model
        while (rs.next()) {
            Object[] rowData = new Object[6];
            rowData[0] = rs.getInt("itemNum");
            rowData[1] = rs.getString("itemName");
            rowData[2] = rs.getString("itemQuantity");
            rowData[3] = rs.getString("itemQuality");
            rowData[4] = rs.getString("itemPrice");
            rowData[5] = rs.getString("expireDate");
            tableModel.addRow(rowData);
        }
        conn.close();
         pstm.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}
     
    if(e.getSource()==remove){
                  int selectedRow = tableItem.getSelectedRow();

    int selectedRowIndex = tableItem.getSelectedRow();
         if (selectedRowIndex != -1) {
            // Remove the selected row from the table model
            model.removeRow(selectedRowIndex);
        }
         
         else if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "No selected item.");
        return;
         }
       }
    
    if (e.getSource() == update) {
    int selectedRow = tableItem.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "No item selected.");
        return;
    }
    
    String itemName = nametf.getText();
    String itemQuantity = quantitytf.getText();
    String itemQuality = qualitycb.getSelectedItem().toString();
    String itemPrice = pricetf.getText();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date expireDate = null;
    
    try {
        expireDate = dateFormat.parse(expiredatetf.getText());
    } catch (ParseException ex) {
        Logger.getLogger(PurchasedItem.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }

    if (ValidateItemDetails(itemName, itemQuantity, itemQuality, itemPrice, expireDate)) {
        String updateQuery = "UPDATE item SET itemName=?, itemQuantity=?, itemQuality=?, itemPrice=?, expireDate=? WHERE itemNum=?";

        try {
            conn = DriverManager.getConnection(url, dbun, pwd);
            pstm = conn.prepareStatement(updateQuery);

            // Set the updated values in the prepared statement
            pstm.setString(1, itemName);
            pstm.setString(2, itemQuantity);
            pstm.setString(3, itemQuality);
            pstm.setString(4, itemPrice);
            pstm.setDate(5, new java.sql.Date(expireDate.getTime()));

            // Get the itemNum value from the selected row in the table
            int itemNum = (int) tableItem.getValueAt(selectedRow, 0);
            pstm.setInt(6, itemNum);

            // Execute the update statement
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Item updated successfully.");
           conn.close();
           pstm.close();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update item.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
       }
    }
  }
 }
    private boolean validateitemDetails(String itemName, String itemQuantity, String itemQuality, String itemPrice, Date expireDate) {
    // Perform validation checks on the employee details
    if (itemName.isEmpty() || itemQuantity.isEmpty() || itemQuality.isEmpty() || itemPrice.isEmpty() || expireDate.equals(0)) 
    {
        JOptionPane.showMessageDialog(this, "To ADD please fill the field.");
        return false;
    }
    // Add more specific validation checks if neede
    return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
private boolean ValidateItemDetails(String itemName, String itemQuantity,
        String itemQuality, String itemPrice, Date expireDate) {
    // Perform validation checks on the employee details
      if (itemName.isEmpty() || itemQuantity.isEmpty() || itemQuality.isEmpty() || itemPrice.isEmpty() || expireDate == null)
           {
        JOptionPane.showMessageDialog(this, "Fill all field with value.");
        return false;
    }
    // Add more specific validation checks if neede
    return true;
    }

}

