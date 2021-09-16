/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
import DTO.Class_Subject;
import DTO.Subject;
import DTO.Teacher;
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
public class TeacherDAO {
    public ArrayList<Teacher> getAllTeacher() throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql= "SELECT * FROM Teacher";
        
        ArrayList<Teacher> lst = new ArrayList<>();
        try{
            c = DBConnect.makeConnection();
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id=rs.getString("Teacher_id");
                    String ln=rs.getString("Teacher_Last_Name");
                    String fn=rs.getString("Teacher_First_Name");
                    
                    Teacher t=new Teacher(id, ln, fn);
                    lst.add(t);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (ps!=null){
                ps.close();
            }
            if (c!=null) {
                c.close();
            }
        }
        return lst;
    }
    
    public Teacher getTeacherById(String tid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Teacher WHERE Teacher_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, tid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id = rs.getString("Teacher_id");
                    String ln = rs.getString("Teacher_Last_Name");
                    String fn = rs.getString("Teacher_First_Name");
                    Teacher t= new Teacher(id, ln, fn);
                    
                    return t;
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
    
    public boolean CreateTeacher(Teacher t) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Teacher (Teacher_id, "
                + "Teacher_Last_Name, Teacher_First_Name) "
                + "VALUES (?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, t.getId());
                ps.setString(2, t.getLn());
                ps.setString(3, t.getFn());
                
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
    
    public boolean deleteTeacher(String id) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Teacher WHERE Teacher_id=?";
        
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
    
    public boolean UpdateTeacher(Teacher td) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Teacher SET Teacher_Last_Name=?, Teacher_First_Name=? WHERE Teacher_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, td.getLn());
                ps.setString(2, td.getFn());
                ps.setString(3, td.getId());
                
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
