package rms;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;


public class SellingItem extends javax.swing.JFrame implements ActionListener{
JLabel title,seller,name,price,quantity,type,fast,expiredate,bill,totalprice,mobile;
JTextField sellertf, menunametf,quantitytf,pricetf,expiredatetf,totalpricetf, mobiletf;
JComboBox typecb;
ButtonGroup fastbg;
JRadioButton yes,no;
JButton back,add,display,update,refresh,addtobill,print;
JPanel mainpanel,panelnorth,paneltop,panelyourbill,panelbottom;
DefaultTableModel model;
JTable selltable,billtable;
DateFormat dateformat;
DateFormatter dateformatter;
java.sql.Connection conn=null;
   String url="jdbc:mysql://localhost:3306/restuarant";
   String dbun="root";
   String pwd="";
   PreparedStatement pstm=null;
   ResultSet rs=null;
   
   public SellingItem() {
        initComponents();  
mainpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//mainpanel.setBackground(Color.red);
panelnorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 1));
title = new JLabel("SELLING MENU");
title.setFont(new Font("Times New Roman", Font.BOLD, 35));
panelnorth.setBackground(Color.orange);
back=new JButton("Back");
back.setFont(new Font("Times New Roman", Font.BOLD, 14));
back.setBounds(90, 180, 100, 30);
back.setLayout(new FlowLayout(FlowLayout.LEFT));
panelnorth.add(back, BorderLayout.WEST);
panelnorth.add(title);
panelnorth.setPreferredSize(new Dimension(1000, 60));
mainpanel.add(panelnorth, BorderLayout.NORTH);

paneltop = new JPanel(new FlowLayout(FlowLayout.CENTER));
paneltop.setPreferredSize(new Dimension(1000,305));
mainpanel.add(paneltop, BorderLayout.CENTER);

seller=new JLabel("Employee");
seller.setFont(new Font("Times New Roman", Font.BOLD, 14));
seller.setBounds(60, 180, 100, 30);

sellertf=new JTextField(10);
sellertf.setFont(new Font("Times New Roman", Font.BOLD, 14));
sellertf.setBounds(60, 180, 100, 30);
mobile=new JLabel("Mobile");
mobile.setFont(new Font("Times New Roman", Font.BOLD, 14));
mobile.setBounds(60, 180, 100, 30);

mobiletf=new JTextField(7);
mobiletf.setFont(new Font("Times New Roman", Font.BOLD, 14));
mobiletf.setBounds(60, 180, 100, 30);

name=new JLabel("MenuName");
name.setFont(new Font("Times New Roman", Font.BOLD, 14));
name.setBounds(60, 180, 100, 30);


menunametf=new JTextField(8);
menunametf.setFont(new Font("Times New Roman", Font.BOLD, 14));
menunametf.setBounds(60, 180, 100, 30);


type=new JLabel("Type");
type.setFont(new Font("Times New Roman", Font.BOLD, 14));
type.setBounds(60, 180, 100, 30);
type.setLayout(new FlowLayout(FlowLayout.LEFT));

typecb=new JComboBox();
typecb.setFont(new Font("Times New Roman", Font.BOLD, 14));
typecb.setBounds(60, 180, 100, 30);
typecb.setBounds(50, 180, 100, 30);
// Assuming 'list' is an array of strings
String[] list = {"Food ", "Alcohol ", "Alcohol_free "};
typecb = new JComboBox<>(list);

 fast=new JLabel("FastingMenu");
 fast.setFont(new Font("Times New Roman", Font.BOLD, 14));
 fast.setBounds(60, 180, 100, 30);
 fastbg=new ButtonGroup();
 yes=new JRadioButton("Yes");
 yes.setFont(new Font("Times New Roman", Font.BOLD, 14));
 no=new JRadioButton("No");
 no.setFont(new Font("Times New Roman", Font.BOLD, 14));
 fastbg.add(yes);
 fastbg.add(no);
 
quantity=new JLabel("Quantity");
quantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
quantity.setBounds(60, 180, 100, 30);

quantitytf=new JTextField(5);
quantitytf.setFont(new Font("Times New Roman", Font.BOLD, 14));
quantitytf.setBounds(60, 180, 100, 30);

price=new JLabel("price");
price.setFont(new Font("Times New Roman", Font.BOLD, 14));
price.setBounds(60, 180, 100, 30);


pricetf=new JTextField(5);
pricetf.setFont(new Font("Times New Roman", Font.BOLD, 14));
pricetf.setBounds(60, 180, 100, 30);


expiredate=new JLabel("Expiredate");
expiredate.setFont(new Font("Times New Roman", Font.BOLD, 14));
expiredate.setBounds(60, 180, 100, 30);

dateformat = new SimpleDateFormat("dd/mm/yyyy");
dateformatter = new DateFormatter(dateformat);
expiredatetf=new JFormattedTextField(dateformatter);
expiredatetf.setFont(new Font("Times New Roman", Font.BOLD, 14));
expiredatetf.setBounds(60, 180, 100, 30);
expiredatetf.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
expiredatetf.setPreferredSize(new Dimension(95, 30));

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

refresh=new JButton("Refresh");
refresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
refresh.setBounds(90, 180, 100, 30);
refresh.setPreferredSize(new Dimension(80, 40));

addtobill=new JButton("AddToBill");
addtobill.setFont(new Font("Times New Roman", Font.BOLD, 14));
addtobill.setBounds(90, 180, 100, 30);
addtobill.setPreferredSize(new Dimension(90, 40));
addtobill.setLayout(new FlowLayout(FlowLayout.RIGHT));

String[] columnNames = {"No","Employee", "Mobile","menuName","Type","FastingMenu",
    "Quantity", "Price","expireDate","TotaPrice"};
model = new DefaultTableModel(columnNames, 0);
selltable = new JTable(model);
JScrollPane scrollPane = new JScrollPane(selltable);
scrollPane.setPreferredSize(new Dimension(900, 190));
scrollPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
JLabel dir = new JLabel("Click row to sell menu then AddToBill!");
dir.setFont(new Font("Times New Roman", Font.BOLD, 18));


paneltop.add(seller);
paneltop.add(sellertf);
paneltop.add(mobile);
paneltop.add(mobiletf);
paneltop.add(name);
paneltop.add(menunametf);
paneltop.add(type);
paneltop.add(typecb);
paneltop.add(fast);
paneltop.add(yes);
paneltop.add(no);
paneltop.add(quantity);
paneltop.add(quantitytf);
paneltop.add(price);
paneltop.add(pricetf);
paneltop.add(expiredate);
paneltop.add(expiredatetf);
paneltop.add(add);
paneltop.add(display);
paneltop.add(update);
paneltop.add(refresh);
paneltop.add(addtobill,BorderLayout.EAST);
paneltop.add(scrollPane, BorderLayout.CENTER);
paneltop.add(dir);


panelbottom = new JPanel(new FlowLayout(FlowLayout.CENTER ));
panelbottom.setPreferredSize(new Dimension(1000, 305));
panelbottom.setBackground(Color.gray);
panelyourbill = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelyourbill.setPreferredSize(new Dimension(1000, 40));

bill = new JLabel("Your Bill");
bill.setFont(new Font("Times New Roman", Font.BOLD, 25));
panelyourbill.add(bill);
panelbottom.add(panelyourbill, BorderLayout.NORTH);
String[] columnName = {"No","seller", "Mobile","menuName","Type","FastingMenu", "Quantity", "Price","expireDate","TotaPrice"};
model = new DefaultTableModel(columnName, 0);
billtable = new JTable(model);
JScrollPane scrollPanebill = new JScrollPane(billtable);
scrollPanebill.setPreferredSize(new Dimension(920, 170));
panelbottom.add(scrollPanebill, BorderLayout.CENTER);

totalprice = new JLabel("TotalPrice");
totalprice.setFont(new Font("Times New Roman", Font.BOLD, 20));
totalpricetf = new JTextField(5);
totalpricetf.setFont(new Font("Times New Roman", Font.BOLD, 20));
//panelbottom.add(totalprice, BorderLayout.CENTER);
//panelbottom.add(totalpricetf, BorderLayout.CENTER);
print=new JButton("Print");
print.setFont(new Font("Times New Roman", Font.BOLD, 20));
print.setBounds(90, 180, 100, 30);
print.setPreferredSize(new Dimension(80, 40));
panelbottom.add(print, BorderLayout.CENTER);
mainpanel.add(panelbottom, BorderLayout.EAST);

back.addActionListener( this);
add.addActionListener( this);
display.addActionListener( this);
update.addActionListener( this);
refresh.addActionListener( this);
addtobill.addActionListener( this);
print.addActionListener( this);

add(mainpanel,BorderLayout.CENTER);
setContentPane(mainpanel);
setSize(1000,700);
setTitle("Restaurant Management System"); 
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            java.util.logging.Logger.getLogger(SellingItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellingItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellingItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellingItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
          /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
                new SellingItem().setVisible(false);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()==back){
           new Restaurant().setVisible(true);
        }
     
     // Assuming "add" is the name of your button

     if (e.getSource() == add) {
         
    String sellerName = sellertf.getText();
    String sellerMobile = mobiletf.getText();
    String menuName = menunametf.getText();
    String menuType = typecb.getSelectedItem().toString();
    
    String fastMenu=yes.isSelected()?"yes": "no";
    
    String Quantity = quantitytf.getText();
    String menuPrice = pricetf.getText();
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date expireDate=null;
            try {
                expireDate = dateFormat.parse(expiredatetf.getText());
            } catch (ParseException ex) {
                Logger.getLogger(PurchasedItem.class.getName()).log(Level.SEVERE, null, ex); 
            }

if (validatemenuDetails(sellerName, sellerMobile, menuName,menuType,fastMenu,Quantity,menuPrice, expireDate)) {
 String insertQuery = "INSERT INTO soldmenu (sellerName, sellerMobile, menuName, menuType, fastMenu, Quantity, price, expireDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
           conn = DriverManager.getConnection(url, dbun, pwd);
           pstm = conn.prepareStatement(insertQuery, pstm.RETURN_GENERATED_KEYS);
           pstm.setString(1, sellerName);
           pstm.setString(2, sellerMobile);
           pstm.setString(3, menuName);
           pstm.setString(4, menuType); 
           pstm.setString(5, fastMenu); 
           pstm.setString(6, Quantity); 
           pstm.setString(7, menuPrice);
           
           pstm.setDate(8, new java.sql.Date(expireDate.getTime()));
           pstm.executeUpdate();
           JOptionPane.showMessageDialog(this, "Menu Added Successfully");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
           ex.printStackTrace();
        }
    }

}
if (e.getSource() == display) {
    // Clear the existing table data
    model = (DefaultTableModel) selltable.getModel();
    model.setRowCount(0);

    String selectQuery = "SELECT menuNum,sellerName, sellerMobile, menuName, menuType, fastMenu, Quantity, Price, expireDate FROM soldmenu";
    try { 
         conn = DriverManager.getConnection(url, dbun, pwd);
         pstm = conn.prepareStatement(selectQuery);
         rs = pstm.executeQuery();
        // Iterate over the result set and populate the table model
            while (rs.next()) {
            Object[] rowData = new Object[9];
            rowData[0] = rs.getInt("menuNum");
            rowData[1] = rs.getString("sellerName");
            rowData[2] = rs.getString("sellerMobile");
            rowData[3] = rs.getString("menuName");
            rowData[4] = rs.getString("menuType");
            rowData[5] = rs.getString("fastMenu");
            rowData[6] = rs.getString("Quantity");
            rowData[7] = rs.getString("Price");
            rowData[8] = rs.getString("expireDate");
           // rowData[9] = rs.getString("totalPrice");
            model.addRow(rowData);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }
      }   
    if (e.getSource() == refresh) {
    // Clear the existing table data
    DefaultTableModel tableModel = (DefaultTableModel) selltable.getModel();
    tableModel.setRowCount(0);

    String selectQuery = "SELECT menuNum, sellerName, sellerMobile, menuName, menuType, fastMenu, Quantity, Price, expireDate FROM soldmenu";

    try { conn = DriverManager.getConnection(url, dbun, pwd);
          pstm = conn.prepareStatement(selectQuery);
          rs = pstm.executeQuery();
        // Iterate over the result set and populate the table model
        while (rs.next()) {
            Object[] rowData = new Object[9];
            rowData[0] = rs.getInt("menuNum");
            rowData[1] = rs.getString("sellerName");
            rowData[2] = rs.getString("sellerMobile");
            rowData[3] = rs.getString("menuName");
            rowData[4] = rs.getString("menuType");
            rowData[5] = rs.getString("fastMenu");
            rowData[6] = rs.getString("Quantity");
            rowData[7] = rs.getString("Price");
            rowData[8] = rs.getString("expireDate");
            tableModel.addRow(rowData);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}
     if (e.getSource() == update) {
    int selectedRow = selltable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "No item selected.");
        return;
    }
    
    String sellerName = seller.getText();
    String sellerMobile = mobiletf.getText();
    String menuName = menunametf.getText();
    String menuType = typecb.getSelectedItem().toString();
    String fastMenu=yes.isSelected()? "yes" : "no";
    String Quantity = quantitytf.getText();
    String menuPrice = pricetf.getText();
     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date expireDate=null;
            try {
                expireDate = dateFormat.parse(expiredatetf.getText());
            } catch (ParseException ex) {
                Logger.getLogger(PurchasedItem.class.getName()).log(Level.SEVERE, null, ex);   
            }

    if (ValidateMenuDetails( sellerName, sellerMobile, menuName, menuType, fastMenu, Quantity, menuPrice, expireDate)) {
        String updateQuery = "UPDATE soldmenu SET sellerName=?, sellerMobile=?, menuName=?, menuType=?, fastMenu=?, Quantity=?,  price=?, expireDate=? WHERE menuNum=?";

        try {
            conn = DriverManager.getConnection(url, dbun, pwd);
            pstm = conn.prepareStatement(updateQuery);

            // Set the updated values in the prepared statement
            pstm.setString(1, sellerName);
            pstm.setString(2, sellerMobile);
            pstm.setString(3, menuName);
            pstm.setString(4, menuType);
            pstm.setString(5, fastMenu);
            pstm.setString(6, Quantity);
            pstm.setString(7, menuPrice);
            pstm.setDate(8, new java.sql.Date(expireDate.getTime()));
            // Get the itemNum value from the selected row in the table
            int menuNum = (int) selltable.getValueAt(selectedRow, 0);
            pstm.setInt(9, menuNum);

            // Execute the update statement
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Item updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update item.");
            }
            conn.close();
           pstm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}    
        if (e.getSource() == addtobill) {
            int selectedRow = selltable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row from selltable.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] rowData = new Object[selltable.getColumnCount()];
                for (int i = 0; i < selltable.getColumnCount(); i++) {
                    rowData[i] = selltable.getValueAt(selectedRow, i);
                }
                 model = (DefaultTableModel) billtable.getModel();
                model.addRow(rowData);
            }
            
        }
        
        if (e.getSource() == print) {
            try {
                // Create a new Document
                Document document = new Document(PageSize.A4);
                // Specify the output file path
                String outputPath = "bill.pdf";
                // Create a PDF writer instance
                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                // Open the Document for writing
                document.open();
                // Create a PdfPTable to hold the table data
                PdfPTable table = new PdfPTable(billtable.getColumnCount());
                // Add table headers
                for (int i = 0; i < billtable.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(billtable.getColumnName(i)));
                    table.addCell(cell);
                }
                // Add table rows
                
                // Add table rows
for (int i = 0; i < billtable.getRowCount(); i++) {
    for (int j = 0; j < billtable.getColumnCount(); j++) {
        Object value = billtable.getValueAt(i, j);
        String cellValue = (value != null) ? value.toString() : "";
        PdfPCell cell = new PdfPCell(new Phrase(cellValue));
        table.addCell(cell);
    }
}
                // Add the table to the document
                document.add(table);
                // Close the Document
                document.close();
                // Print the PDF document
                if (Desktop.isDesktopSupported()) {
                    File file = new File(outputPath);
                    Desktop.getDesktop().print(file);
                } else {
                    JOptionPane.showMessageDialog(this, "Printing is not supported on this system.", "Printing Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
  }
      private boolean validatemenuDetails(String sellerName, String sellerMobile, String menuName, String menuType, String fastMenu, String Quantity, String menuPrice, Date expireDate) 
    {
      // Perform validation checks on the employee details
    if (sellerName.isEmpty() || sellerMobile.isEmpty() || menuName.isEmpty()
            || menuType.isEmpty() || fastMenu.isEmpty() || Quantity.isEmpty()||
            menuPrice.isEmpty() || expireDate.equals(0)) 
    {
        JOptionPane.showMessageDialog(this, "To ADD please fill the field.");
        return false;
    }
    // Add more specific validation checks if neede
    return true; 
    }

   private boolean ValidateMenuDetails(String sellerName, String sellerMobile, String menuName, 
String menuType, String fastMenu, String Quantity, String menuPrice, Date expireDate) {
    if (sellerName.isEmpty() || sellerMobile.isEmpty() || menuName.isEmpty()||menuType.isEmpty()||
            fastMenu.isEmpty()||Quantity.isEmpty() || menuPrice.isEmpty() || expireDate == null)
           {
        JOptionPane.showMessageDialog(this, "Fill all field with value.");
        return false;
    }
     return true;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
}
