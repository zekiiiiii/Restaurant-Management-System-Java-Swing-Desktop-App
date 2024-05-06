package rms;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class Employee extends javax.swing.JFrame implements ActionListener {
JLabel title,name,degree,salary,age,empusername,emppassword,gender;
JTextField nametf,empusernametf,emppasswordtf;
JComboBox degreecb,gendercb;
JButton back, add,display,update,delete,refresh,search;
JPanel mainpanel,panelnorth,panelcenter,panelbottom,panelbottomtable;
JSpinner agesp,salarysp;
JTable tableEmployee;
java.sql.Connection conn=null;
String url="jdbc:mysql://localhost:3306/restuarant";
   String dbun="root";
   String pwd="";
   PreparedStatement pstm=null;
   DefaultTableModel model=null;
   ResultSet rs=null;
    public Employee() {
        initComponents();
mainpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//mainpanel.setBackground(Color.red);
panelnorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 0));//////////////
title = new JLabel("EMPLOYEE MANAGEMENT");
title.setFont(new Font("Times New Roman", Font.BOLD, 35));
back=new JButton("Back");
back.setFont(new Font("Times New Roman", Font.BOLD, 14));
back.setBounds(90, 180, 100, 30);
back.setLayout(new FlowLayout(FlowLayout.LEFT));
panelnorth.add(back, BorderLayout.WEST);
panelnorth.setBackground(Color.orange);
panelnorth.setPreferredSize(new Dimension(1000, 60));
panelnorth.add(title);
mainpanel.add(panelnorth, BorderLayout.NORTH);
panelcenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelcenter.setPreferredSize(new Dimension(1000, 70));
mainpanel.add(panelcenter, BorderLayout.CENTER);

name=new JLabel("Employee Name");
name.setHorizontalAlignment(SwingConstants.CENTER);
name.setFont(new Font("Times New Roman", Font.BOLD, 14));
name.setBounds(0, 0, 100, 30);
nametf = new JTextField(8);
nametf.setFont(new Font("Times New Roman", Font.BOLD, 14));
nametf.setBounds(30, 180, 100, 30);
degree =new JLabel("Degree");
degree.setFont(new Font("Times New Roman", Font.BOLD, 14));
degree.setBounds(40, 180, 100, 30);
degreecb=new JComboBox();
degreecb.setFont(new Font("Times New Roman", Font.BOLD, 14));
degreecb.setBounds(50, 180, 100, 30);

// Assuming 'list' is an array of strings
String[] list = {"BSc ", "MSc ", "PHD"};
degreecb = new JComboBox<>(list);
age=new JLabel("Age");
age.setFont(new Font("Times New Roman", Font.BOLD, 14));
SpinnerModel avalue = new SpinnerNumberModel(20, //initial value  
                18, //minimum value  
                65, //maximum value  
                1); //step 
agesp=new JSpinner(avalue);
agesp.setFont(new Font("Times New Roman", Font.BOLD, 14));
salary=new JLabel("Salary");
salary.setFont(new Font("Times New Roman", Font.BOLD, 14));
SpinnerModel svalue = new SpinnerNumberModel(2000, //initial value  
                2000, //minimum value  
                10000, //maximum value  
                1000); //step 
salary.setBounds(60, 180, 100, 30);
salarysp=new JSpinner(svalue);
salarysp.setFont(new Font("Times New Roman", Font.BOLD, 14));
salarysp.setBounds(70, 180, 100, 30);
empusername=new JLabel("empUsername");
empusername.setFont(new Font("Times New Roman", Font.BOLD, 14));
empusername.setBounds(10, 10, 100, 30);
empusernametf=new JTextField(7);
empusernametf.setFont(new Font("Times New Roman", Font.BOLD, 14));
empusernametf.setBounds(70, 180, 100, 30);
emppassword=new JLabel("empPassword");
emppassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
emppassword.setBounds(60, 180, 100, 30);
emppasswordtf=new JTextField(7);
emppasswordtf.setFont(new Font("Times New Roman", Font.BOLD, 14));
emppasswordtf.setBounds(70, 180, 100, 30);
gender=new JLabel("Gender");
gender.setFont(new Font("Times New Roman", Font.BOLD, 14));
String listgender[]={"Male", "Female"};
gendercb=new JComboBox(listgender);
gendercb.setFont(new Font("Times New Roman", Font.BOLD, 14));

add=new JButton("Add");
add.setFont(new Font("Times New Roman", Font.BOLD, 14));
add.setBounds(80, 180, 100, 30);
display=new JButton("Display");
display.setFont(new Font("Times New Roman", Font.BOLD, 14));
display.setBounds(80, 180, 100, 30);
update=new JButton("Update");
update.setFont(new Font("Times New Roman", Font.BOLD, 14));
update.setBounds(90, 180, 100, 30);
delete=new JButton("Delete");
delete.setFont(new Font("Times New Roman", Font.BOLD, 14));
delete.setBounds(100, 180, 100, 30);
delete.setHorizontalAlignment(SwingConstants.LEFT);
refresh=new JButton("Refresh");
refresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
refresh.setBounds(100, 180, 100, 30);
refresh.setHorizontalAlignment(SwingConstants.LEFT);
search=new JButton("Search");
search.setFont(new Font("Times New Roman", Font.BOLD, 14));
search.setBounds(100, 180, 100, 30);
search.setHorizontalAlignment(SwingConstants.LEFT);

panelcenter.add(name);
panelcenter.add(nametf);
panelcenter.add(degree);
panelcenter.add(degreecb);
panelcenter.add(age);
panelcenter.add(agesp);
panelcenter.add(salary);
panelcenter.add(salarysp);
panelcenter.add(empusername);
panelcenter.add(empusernametf);
panelcenter.add(emppassword);
panelcenter.add(emppasswordtf);
panelcenter.add(gender);
panelcenter.add(gendercb);

panelcenter.add(add);
panelcenter.add(display);
panelcenter.add(update);
panelcenter.add(delete);
panelcenter.add(refresh);
//panelcenter.add(search);

panelbottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
panelbottom.setPreferredSize(new Dimension(950, 520));
panelbottom.setBackground(Color.gray);
mainpanel.add(panelbottom, BorderLayout.SOUTH);

panelbottomtable=new JPanel(new FlowLayout(FlowLayout.CENTER));
panelbottomtable.setPreferredSize(new Dimension(800, 510));
panelbottomtable.setBackground(Color.yellow);
panelbottom.add(panelbottomtable, BorderLayout.SOUTH);
JLabel dir = new JLabel("Click row to delete or update the data!");
dir.setFont(new Font("Times New Roman", Font.BOLD, 20));
dir.setBounds(100, 180, 100, 30);

String[] columnNames = {"empNum", "Employee_Name", "Degree","Age", "Salary","Username","Password","Gender"};
model = new DefaultTableModel(columnNames, 0);
tableEmployee = new JTable(model);
JScrollPane scrollPane = new JScrollPane(tableEmployee);
scrollPane.setPreferredSize(new Dimension(750, 450));
panelbottomtable.add(scrollPane, BorderLayout.CENTER);
panelbottomtable.add(dir, BorderLayout.CENTER);

/////////////////////////////////////////////
add.addActionListener( this);
display.addActionListener( this);
update.addActionListener( this);
delete.addActionListener( this);
refresh.addActionListener( this);
back.addActionListener( this);
/////////////////////////////////////////////
add(mainpanel,BorderLayout.CENTER);
setContentPane(mainpanel);
setSize(1000,700);
setTitle("Restaurant Management System"); 
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
validate();
repaint(); 
    }
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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
                new Employee().setVisible(false);
                
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==back){
           new Restaurant().setVisible(true);
        }
         if (e.getSource() == add) {
        String empName =nametf.getText();
        String empDegree = degreecb.getSelectedItem().toString();
        int empAge = (int) agesp.getValue();
        int empSalary = (int) salarysp.getValue();
        String empUsername = empusernametf.getText();
        String empPassword = emppasswordtf.getText();
        String empGender = gendercb.getSelectedItem().toString();

        if (validateEmployeeDetails(empName, empDegree, empAge, empSalary, empUsername, empPassword, empGender)) {
            String insertQuery = "INSERT INTO employee (empName, empDegree, empAge, empSalary, empUsername, empPassword,empGender) VALUES (?,?, ?, ?, ?, ?, ?)";
            
            try{ 
               // Class.forName("com.mysql.jdbc.Driver");
                conn =DriverManager.getConnection(url, dbun, pwd);
                pstm = conn.prepareStatement(insertQuery,pstm.RETURN_GENERATED_KEYS);
                pstm.setString(1, empName);
                pstm.setString(2, empDegree);
                pstm.setInt(3, empAge);
                pstm.setInt(4, empSalary);
                pstm.setString(5, empUsername);
                pstm.setString(6, empPassword);
                pstm.setString(7, empGender);
                pstm.executeUpdate();//for insert, delete and update
                JOptionPane.showMessageDialog(this, "Employee Added Successfully");
                pstm.close();
                conn.close();
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        
    } 
        if (e.getSource() == display) {
    // Clear the existing table data
    model = (DefaultTableModel) tableEmployee.getModel();
    model.setRowCount(0);

    String selectQuery = "SELECT empNum, empName, empDegree, empAge, empSalary, empUsername, empPassword,empGender FROM employee";
    try {
         //Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection(url, dbun, pwd);
         pstm = conn.prepareStatement(selectQuery);
         rs = pstm.executeQuery();//it is for Select
        // Iterate over the result set and populate the table model
            while (rs.next()) {
            Object[] rowData = new Object[8];
            rowData[0] = rs.getInt("empNum");
            rowData[1] = rs.getString("empName");
            rowData[2] = rs.getString("empDegree");
            rowData[3] = rs.getInt("empAge");
            rowData[4] = rs.getInt("empSalary");
            rowData[5] = rs.getString("empUsername");
            rowData[6] = rs.getString("empPassword");
            rowData[7] = rs.getString("empGender");
            model.addRow(rowData);
        }
            pstm.close();
            conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }        
}
if(e.getSource()==delete){
                int selectedRow = tableEmployee.getSelectedRow();
                int selectedRowIndex = tableEmployee.getSelectedRow();
                if (selectedRowIndex != -1) {
                // Remove the selected row from the table model
                 model.removeRow(selectedRowIndex);
                }
               else if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select employee row to delete.");
                return;
               }
             try {
                 pstm.close();
                 conn.close();
             }
             catch (SQLException ex) {
                 Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
             }
          }
if (e.getSource() == update) {
    int selectedRow = tableEmployee.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select employee row to Update.");
        return;
    }
    
    String empName = nametf.getText();
    String empDegree = degreecb.getSelectedItem().toString();
    int empAge = (int) agesp.getValue();
    int empSalary = (int) salarysp.getValue();
    String empUsername = empusernametf.getText();
    String empPassword = emppasswordtf.getText();
    String empGender = gendercb.getSelectedItem().toString();
    if (validateEmployeeDetails(empName, empDegree, empAge, empSalary, empUsername, empPassword, empGender)) {
        String updateQuery = "UPDATE employee SET empName=?, empDegree=?, empAge=?, empSalary=?, empUsername=?, empPassword=?,empGender=? WHERE empNum=?";
        try {
            conn = DriverManager.getConnection(url, dbun, pwd);
            pstm =conn.prepareStatement(updateQuery);
             // Set the updated values in the prepared statement
            pstm.setString(1, empName);
            pstm.setString(2, empDegree);
            pstm.setInt(3, empAge);
            pstm.setInt(4, empSalary);
            pstm.setString(5, empUsername);
            pstm.setString(6, empPassword);
            pstm.setString(7, empGender);
            // Get the empNum value from the selected row in the table
            int empNum = (int) tableEmployee.getValueAt(selectedRow, 0);
            pstm.setInt(8, empNum);

            // Execute the update statement
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee.");
            }
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
if (e.getSource() == refresh) {
    // Clear the existing table data
    DefaultTableModel tableModel = (DefaultTableModel) tableEmployee.getModel();
    tableModel.setRowCount(0);

    String selectQuery = "SELECT empNum, empName, empDegree, empAge, empSalary, empUsername, empPassword,empGender FROM employee";

    try { conn = DriverManager.getConnection(url, dbun, pwd);
          pstm = conn.prepareStatement(selectQuery);
          rs = pstm.executeQuery();
        // Iterate over the result set and populate the table model
        while (rs.next()) {
            Object[] rowData = new Object[8];
            rowData[0] = rs.getInt("empNum");
            rowData[1] = rs.getString("empName");
            rowData[2] = rs.getString("empDegree");
            rowData[3] = rs.getInt("empAge");
            rowData[4] = rs.getInt("empSalary");
            rowData[5] = rs.getString("empUsername");
            rowData[6] = rs.getString("empPassword");
            rowData[7] = rs.getString("empGender");
            tableModel.addRow(rowData);
        }
        pstm.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}
if (e.getSource() == search) {
    String empusername = empusernametf.getText();

    try {
        conn = DriverManager.getConnection(url, dbun, pwd);
        pstm = conn.prepareStatement("SELECT * FROM employee WHERE empUsername = ?");
        pstm.setString(1, empusername);
        rs = pstm.executeQuery();

        if (rs.next()) {
            // Populate the UI fields with the employee details
            nametf.setText(rs.getString("empName"));
            degreecb.setSelectedItem(rs.getString("empDegree"));
            agesp.setValue(rs.getInt("empAge"));
            salarysp.setValue(rs.getInt("empSalary"));
            empusernametf.setText(rs.getString("empUsername"));
            gendercb.setSelectedItem(rs.getString("empGender"));
        } else {
            // Handle case when employee is not found
            JOptionPane.showMessageDialog(null, "Employee not found.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}
private boolean validateEmployeeDetails(String empName, String empDegree,
        int empAge, int empSalary, String empUsername, String empPassword, String empGender) {
    // Perform validation checks on the employee details
    if (empName.isEmpty() || empDegree.isEmpty() || empAge <= 0 || 
        empSalary <= 0 || empUsername.isEmpty() || empPassword.isEmpty() || empGender.isEmpty()) 
    {
        JOptionPane.showMessageDialog(this, "Please fill in all the fields with valid values.");
        return false;
    }
    // Add more specific validation checks if neede
    return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
