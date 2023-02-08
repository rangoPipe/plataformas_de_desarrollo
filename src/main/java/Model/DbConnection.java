/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Farango
 */
public class DbConnection {
    
    private final String db = "plataformas_de_desarrollo";
    private final String host = "uniminutoserver.mysql.database.azure.com:3306";
    private final String user = "adminplatdev";
    private final String pass = "Estacion23";
    private final String url = String.format("jdbc:mysql://%s/%s?useSSL=true", this.host, this.db);
    private Connection conn = null;
    
    public Connection getConnection(){
       try {
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
            return this.conn;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return null;
    }
}
