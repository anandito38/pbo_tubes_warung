/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tubes_warung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author anand
 */
public class Database {
    static final String DB_URL = "jdbc:mysql://localhost/pbo_tubes_warung";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public Database() throws SQLException, ClassNotFoundException{
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            stmt = conn.createStatement();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,""+e.getMessage(),"Connection Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ResultSet getData(String SQLString){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(SQLString);
            rs = preparedStatement.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error :"+e.getMessage(),"Communication Error",
            JOptionPane.WARNING_MESSAGE);
        }
        return rs; 
    }
    
    public int getQueryId(String SQLString, boolean returnGeneratedKeys) {
        int generatedId = 0;
        try {
            if (returnGeneratedKeys) {
                PreparedStatement statement = conn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Retrieve the auto-generated ID
                }
                generatedKeys.close();
                statement.close();
            } else {
                stmt.executeUpdate(SQLString);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
        }
        return generatedId;
    }
    
    public void query (String SQLString){
        try{
            stmt.executeUpdate(SQLString);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error :"+e.getMessage(),"Communication Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void close(){
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException err){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, err);
        }
    }

    ResultSet getData(String sql, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
