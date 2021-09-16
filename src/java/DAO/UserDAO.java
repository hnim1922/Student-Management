/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import DBHelper.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class UserDAO {
    public boolean createUser(User u) throws SQLException, NamingException 
    {
        Connection con = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Log_in(Student_Email, Password) "
                + "VALUES (?, ?)";

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, u.getUsername());
                pstm.setString(2, u.getPassword());

                pstm.executeUpdate();
                return true;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public ArrayList<User> getUser() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Log_in";
        ArrayList<User> lst= new ArrayList<>();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String UN = rs.getString("Student_Email");
                    String pass = rs.getString("Password");

                    User u = new User(UN, pass);

                    lst.add(u);
                    
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lst;
    }
    
    public User getUserByEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Log_in WHERE Student_Email=?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String UN = rs.getString("Student_Email");
                    String pass = rs.getString("Password");
                    User u=new User(UN, pass);
                    
                    return u;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    public boolean deleteUser(String email) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Log_in WHERE Student_Email=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, email);
                
                ps.executeUpdate();
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (ps !=null){
                ps.close();
            }
            
            if (c!=null){
                c.close();
            }
        }
        return false;
    }
    
    public boolean UpdateUser(User u) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Log_in SET Password=? WHERE Student_Email=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, u.getPassword());
                ps.setString(2, u.getUsername());
                
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps!=null){
                ps.close();
            }
            if (c!=null) {
                c.close();
            }
        }
        return false;
    }
}
