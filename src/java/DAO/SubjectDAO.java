/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
import DTO.Subject;
import java.io.Serializable;
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
public class SubjectDAO implements Serializable{
    public boolean setAvailableSubject(Subject sd) throws SQLException{
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
         String sql= "UPDATE Subject SET Subject_name=?, available=? ,url=? WHERE Subject_id=?";
           try {
                    con = DBConnect.makeConnection();
            
            if (con!=null){
                pstm=con.prepareStatement(sql);
                pstm.setString(1, sd.getName());
                pstm.setBoolean(2, sd.getAva());
                pstm.setString(3, sd.getUrl());
                pstm.setString(4, sd.getId());
                
                pstm.executeUpdate();
                return true;
           }
           } catch (Exception e) {
                  e.printStackTrace();
            return false;
        } finally {
            if (pstm!=null){
                pstm.close();
            }
            if (con!=null) {
                con.close();
            }
        }
           return false;
       }
    
    public ArrayList<Subject> getAllSubject() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Subject";
        
        ArrayList<Subject> lst = new ArrayList<>();

        try {
            
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Subject s=new Subject();
                    s.setId(rs.getString("Subject_id"));
                    s.setName(rs.getString("Subject_name"));
                    s.setAva(rs.getBoolean("available"));
                    s.setUrl(rs.getString("url"));
                    lst.add(s);
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
    public Subject getSubjectById(String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Subject WHERE Subject_id=?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Subject s=new Subject();
                    s.setId(rs.getString("Subject_id"));
                    s.setName(rs.getString("Subject_name"));
                    
                    return s;
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
    
    public ArrayList<Subject> getSubjectByClassId(String cid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Class_Subject WHERE Class_id=?";
        
        ArrayList<Subject> lst = new ArrayList<>();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, cid);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Subject s=getSubjectById(rs.getString("Subject_id"));
                    
                    lst.add(s);
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
    
    public boolean CreateSubject(Subject sub) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Subject (Subject_id, "
                + "Subject_name) "
                + "VALUES (?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, sub.getId());
                ps.setString(2, sub.getName());
                
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
    
    public boolean deleteSubject(String id) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Subject WHERE Subject_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, id);
                
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
    
    public boolean UpdateSubject(Subject sd) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Subject SET Subject_name=? WHERE Subject_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, sd.getName());
                ps.setString(2, sd.getId());
                
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
