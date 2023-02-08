/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Crud;

import Model.DbConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Farango
 */
public class UserCrud extends DbConnection {
    
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean Insert(User model) {
        Connection conn = getConnection();
        try {
            
            String sql = "INSERT INTO USER (name, email) VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getEmail());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    public boolean Update(User model) {
        Connection conn = getConnection();
        try {
            
            String sql = "UPDATE USER SET name=?, email=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getEmail());
            ps.setInt(3, model.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    public boolean Delete(User model) {
        Connection conn = getConnection();
        try {
            
            String sql = "DELETE FROM USER WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, model.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }        
    }
    
    public boolean Search(User model) {
        Connection conn = getConnection();
        try {
            String sql = "SELECT * FROM USER WHERE email LIKE %?%";
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getEmail());
            rs = ps.executeQuery(sql);
            
            if( rs.next() ){
                model.setId( Integer.parseInt( rs.getString("id") ) );
                model.setName( rs.getString("name") );
            }
            
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }        
    }
    
    public DefaultTableModel GetAll(){
        Connection conn = getConnection();
        DefaultTableModel dataTable = new DefaultTableModel();
        dataTable.addColumn("Id");
        dataTable.addColumn("Nombre");
        dataTable.addColumn("Email");
        String[] data = new String[3];
        
        try {
            String sql = "SELECT * FROM USER";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            
            while( rs.next() ){
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                dataTable.addRow(data);
            }
            return dataTable;
            
        } catch (SQLException e) {
            return dataTable;
        }
    }
    
}
