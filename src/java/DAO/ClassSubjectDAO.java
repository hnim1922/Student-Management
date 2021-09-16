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
public class ClassSubjectDAO {
    public ArrayList<Class_Subject> ViewClassSubjectC(String cid) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql= "SELECT * FROM Class_Subject WHERE Class_id=?";
        
        ArrayList<Class_Subject> lst= new ArrayList<>();
        
        try{
            c = DBConnect.makeConnection();
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    ClassDAO cDAO=new ClassDAO();
                    SubjectDAO sDAO=new SubjectDAO();
                    TeacherDAO tDAO= new TeacherDAO();
                    String suid = rs.getString("Subject_id");
                    String se = rs.getString("Semester");
                    String tid= rs.getString("Teacher_id");
                    
                    DTO.Class cl=cDAO.getClassById(cid);
                    Subject su = sDAO.getSubjectById(suid);
                    Teacher t= tDAO.getTeacherById(tid);
                    
                    Class_Subject cs= new Class_Subject(cl, su, se, t);
                    
                    lst.add(cs);
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
    
    public boolean AddSubjectToClass(String cl, String su, String se, String te) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Class_Subject (Class_id, "
                + "Subject_id, Semester, Teacher_id) "
                + "VALUES (?, ?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, cl);
                ps.setString(2, su);
                ps.setString(3, se);
                ps.setString(4, te);
                
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
    
    public boolean DeleteClassSubject(String cid, String suid) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "DELETE FROM Class_Subject WHERE Class_id=? AND Subject_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid);
                ps.setString(2, suid);
                
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
    
    public boolean DeleteClassSubjectByClassId(String cid) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "DELETE FROM Class_Subject WHERE Class_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid);
                
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
    
    public Class_Subject SearchClass(String cid, String suid) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql= "SELECT * FROM Class_Subject WHERE Class_id=? AND Subject_id=?";
        
        try{
            c = DBConnect.makeConnection();
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid); //gan tham so 1 la bien truyen vao
                ps.setString(2, suid);
                rs = ps.executeQuery();
                
                while (rs.next()){
                    ClassDAO cDAO=new ClassDAO();
                    SubjectDAO sDAO=new SubjectDAO();
                    TeacherDAO tDAO= new TeacherDAO();
                    String se = rs.getString("Semester");
                    String tid= rs.getString("Teacher_id");
                    
                    DTO.Class cl=cDAO.getClassById(cid);
                    Subject su = sDAO.getSubjectById(suid);
                    Teacher t= tDAO.getTeacherById(tid);
                    
                    Class_Subject cs= new Class_Subject(cl, su, se, t);
                    
                    return cs;
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
        return null;
    }
    
    public Class_Subject SearchClassByTeacher(String tid) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql= "SELECT * FROM Class_Subject WHERE Teacher_id=?";
        
        try{
            c = DBConnect.makeConnection();
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, tid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    ClassDAO cDAO=new ClassDAO();
                    SubjectDAO sDAO=new SubjectDAO();
                    TeacherDAO tDAO= new TeacherDAO();
                    String se = rs.getString("Semester");
                    String cid= rs.getString("Class_id");
                    String suid= rs.getString("Subject_id");
                    
                    DTO.Class cl=cDAO.getClassById(cid);
                    Subject su = sDAO.getSubjectById(suid);
                    Teacher t= tDAO.getTeacherById(tid);
                    
                    Class_Subject cs= new Class_Subject(cl, su, se, t);
                    
                    return cs;
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
        return null;
    }
}
