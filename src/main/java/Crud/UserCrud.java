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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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

            String sql = "INSERT INTO USER (name, email, phone, job_position) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getPhone());
            ps.setString(4, model.getJobPosition());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
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

            String sql = "UPDATE USER SET name=?, email=?, phone=?, job_position=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getPhone());
            ps.setString(4, model.getJobPosition());
            ps.setInt(5, model.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public boolean Delete(int id) {
        Connection conn = getConnection();
        try {

            String sql = "DELETE FROM USER WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public User GetById(int id) {
        Connection conn = getConnection();
        try {
            User model = new User();
            String sql = "SELECT * FROM USER WHERE id=?";     
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                model.setId(Integer.parseInt(rs.getString("id")));
                model.setName(rs.getString("name"));
                model.setEmail(rs.getString("email"));
                model.setPhone(rs.getString("phone"));
                model.setJobPosition(rs.getString("job_position"));
            }

            return model;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public List<User> GetAll(String term) {
        Connection conn = getConnection();
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM USER";
            if( term != null ){
                sql = "SELECT * FROM USER WHERE email LIKE ? OR name LIKE ? OR job_position LIKE ?";
                ps = conn.prepareStatement(sql);
                ps.setString( 1, MessageFormat.format("%{0}%", term) );
                ps.setString( 2, MessageFormat.format("%{0}%", term) );
                ps.setString( 3, MessageFormat.format("%{0}%", term) );
            }
            else {
                ps = conn.prepareStatement(sql);
            }
            
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(
                        Integer.parseInt(rs.getString(1)), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            return users;
        }
    }

}
