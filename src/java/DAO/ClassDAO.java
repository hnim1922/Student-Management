/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
import DTO.Class;
import DTO.Class_Subject;
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
public class ClassDAO {
    public ArrayList<Class> getAllClass() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Class";
        
        ArrayList<Class> lst = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    DTO.Class c=new DTO.Class();
                    c.setId(rs.getString("Class_id"));
                    c.setName(rs.getString("Class_name"));
                    c.setAttendants(rs.getString("Class_attendants"));
                    
                    lst.add(c);
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
    
    public Class getClassById(String cid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Class WHERE Class_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id = rs.getString("Class_id");
                    String n = rs.getString("Class_Name");
                    String d = rs.getString("Class_attendants");
                    Class cl= new Class(id, n, d);
                    
                    return cl;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(rs !=null){
                rs.close();
            }
            
            if (ps !=null){
                ps.close();
            }
            
            if (c!=null){
                c.close();
            }
        }
        return null;
    }
    
    public boolean CreateClass(Class cl) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Class (Class_id, "
                + "Class_name, Class_attendants) "
                + "VALUES (?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, cl.getId());
                ps.setString(2, cl.getName());
                ps.setString(3, cl.getAttendants());
                
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
    
    public boolean deleteClass(String id) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Class WHERE Class_id=?";
        
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
    
    public boolean UpdateClass(Class cd) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Class SET Class_name=?, Class_attendants=? WHERE Class_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, cd.getName());
                ps.setString(2, cd.getAttendants());
                ps.setString(3, cd.getId());
                
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
    
    public boolean AddSubjectToClass(String cl, String su, String se) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Class_Subject (Class_id, "
                + "Subject_id, Semester) "
                + "VALUES (?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, cl);
                ps.setString(2, su);
                ps.setString(3, se);
                
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
