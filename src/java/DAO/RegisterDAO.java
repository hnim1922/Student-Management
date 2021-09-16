/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
import DTO.Register_Class;
import DTO.Score;
import DTO.Student;
import DTO.Subject;
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
public class RegisterDAO {
    public ArrayList<Register_Class> getAllRegister() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Register_Subject";
        
        ArrayList<Register_Class> lst = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Register_Class r = new Register_Class();
                    
                    StudentDAO sDAO=new StudentDAO();
                    Student s = sDAO.getStudentById(rs.getString("Student_id"));
                    
                    r.setStudent(s);
                    
                    SubjectDAO suDAO=new SubjectDAO();
                    Subject su = suDAO.getSubjectById(rs.getString("Subject_id"));
                    
                    r.setSubject(su);
                    
                    r.setSemester(rs.getString("Semester"));
                    
                    lst.add(r);
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
    
    public Register_Class getRegisterById(String sid, String suid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Register_Subject WHERE Student_id=? AND Subject_id=?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, sid); //gan tham so 1 la bien truyen vao
                pstm.setString(2, suid);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Register_Class r = new Register_Class();
                    
                    StudentDAO sDAO=new StudentDAO();
                    Student s = sDAO.getStudentById(rs.getString("Student_id"));
                    
                    r.setStudent(s);
                    
                    SubjectDAO suDAO=new SubjectDAO();
                    Subject su = suDAO.getSubjectById(rs.getString("Subject_id"));
                    
                    r.setSubject(su);
                    
                    r.setSemester(rs.getString("Semester"));
                    
                    return r;
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
    
    public ArrayList<Register_Class> getRegisterByStudentId(String sid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Register_Subject WHERE Student_id=?";
        
        ArrayList<Register_Class> lst = new ArrayList<>();
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, sid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    StudentDAO sDAO = new StudentDAO();
                    Student s=sDAO.getStudentById(sid);
                    
                    SubjectDAO suDAO = new SubjectDAO();
                    Subject su=suDAO.getSubjectById(rs.getString("Subject_id"));
                    
                    String se = rs.getString("Semester");
                    
                    Register_Class r= new Register_Class(s, su, se);
                    
                    lst.add(r);
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
        return lst;
    }
    
    public boolean CreateRegister(Register_Class r) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Register_Subject (Student_id, "
                + "Subject_id, Semester) "
                + "VALUES (?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, r.getStudent().getId());
                ps.setString(2, r.getSubject().getId());
                ps.setString(3, r.getSemester());
                
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
