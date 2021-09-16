/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
import DTO.Student;
import DTO.Class;
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
public class StudentDAO {
    public ArrayList<Student> getAllStudent() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * "
                + "FROM Student "
                + "ORDER BY Class_id ASC";

        ArrayList<Student> lst = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Student s=new Student();
                    s.setId(rs.getString("Student_id"));
                    s.setLastName(rs.getString("Student_Last_Name"));
                    s.setFirstName(rs.getString("Student_First_Name"));
                    s.setMajorId(rs.getString("Student_Major_id"));
                    s.setSemester(rs.getString("Student_Semester"));
                    s.setEmail(rs.getString("Student_Email"));
                    
                    ClassDAO cDAO= new ClassDAO();
                    Class c= cDAO.getClassById(rs.getString("Class_id"));
//                    c.setId(rs.getString("Class_id"));
//                    c.setName(rs.getString("Class_name"));
//                    c.setAttendants(rs.getString("Class_attendants"));
                    
                    s.setClasses(c);

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
    
    public Student getStudentById(String sid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Student WHERE Student_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, sid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id = rs.getString("Student_id");
                    String l = rs.getString("Student_Last_Name");
                    String f = rs.getString("Student_First_Name");
                    String m = rs.getString("Student_Major_id");
                    String ss = rs.getString("Student_Semester");
                    String e = rs.getString("Student_Email");
                    String cid= rs.getString("Class_id");
                    
                    ClassDAO cDAO = new ClassDAO();
                    Class cl = cDAO.getClassById(cid);
                    
                    Student s= new Student(id, l, f, m, ss, cl, e);
                    
                    return s;
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
    
    public ArrayList<Student> getStudentByClassId(String cid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Student WHERE Class_id=?";
        
        ArrayList<Student> lst = new ArrayList<>();
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, cid); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id = rs.getString("Student_id");
                    String l = rs.getString("Student_Last_Name");
                    String f = rs.getString("Student_First_Name");
                    String m = rs.getString("Student_Major_id");
                    String ss = rs.getString("Student_Semester");
                    String e = rs.getString("Student_Email");
                    String clid= rs.getString("Class_id");
                    
                    ClassDAO cDAO = new ClassDAO();
                    Class cl = cDAO.getClassById(clid);
                    
                    Student s= new Student(id, l, f, m, ss, cl, e);
                    
                    lst.add(s);
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
    
    public Student getStudentByEmail(String semail) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Student WHERE Student_Email=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, semail); //gan tham so 1 la bien truyen vao
                rs = ps.executeQuery();
                
                while (rs.next()){
                    String id = rs.getString("Student_id");
                    String l = rs.getString("Student_Last_Name");
                    String f = rs.getString("Student_First_Name");
                    String m = rs.getString("Student_Major_id");
                    String ss = rs.getString("Student_Semester");
                    String e = rs.getString("Student_Email");
                    String cid= rs.getString("Class_id");
                    
                    ClassDAO cDAO = new ClassDAO();
                    Class cl = cDAO.getClassById(cid);
                    
                    Student s= new Student(id, l, f, m, ss, cl, e);
                    
                    return s;
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
    
    public boolean CreateStudent(Student s) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Student (Student_id, "
                + "Student_Last_Name, Student_First_Name, Student_Major_id, Student_Semester, Class_id, Student_Email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, s.getId());
                ps.setString(2, s.getLastName());
                ps.setString(3, s.getFirstName());
                ps.setString(4, s.getMajorId());
                ps.setString(5, s.getSemester());
                ps.setString(6, s.getClasses().getId());
                ps.setString(7, s.getEmail());
                
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
    
    public boolean deleteStudent(String id) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Student WHERE Student_id=?";
        
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
    
    public boolean UpdateStudent(Student sd) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Student SET Student_Last_Name=?, Student_First_Name=?, Student_Major_id=?, Student_Semester=?, Class_id=?, Student_Email=? WHERE Student_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, sd.getLastName());
                ps.setString(2, sd.getFirstName());
                ps.setString(3, sd.getMajorId());
                ps.setString(4, sd.getSemester());
                ps.setString(5, sd.getClasses().getId());
                ps.setString(6, sd.getEmail());
                ps.setString(7, sd.getId());
                
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
