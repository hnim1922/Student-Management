/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBHelper.DBConnect;
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
public class ScoreDAO {
    public ArrayList<Score> getAllScore() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM Score";
        
        ArrayList<Score> lst = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Score sc = new Score();
                    
                    StudentDAO sDAO=new StudentDAO();
                    Student s = sDAO.getStudentById(rs.getString("Student_id"));
                    
                    sc.setStudent(s);
                    
                    SubjectDAO suDAO=new SubjectDAO();
                    Subject su = suDAO.getSubjectById(rs.getString("Subject_id"));
                    
                    sc.setSubject(su);
                    
                    sc.setScore(rs.getString("Score"));
                    sc.setSemester(rs.getString("Semester"));
                    sc.setStatus(rs.getString("Status"));
                    
                    lst.add(sc);
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
    
    public Score getScoreById(String sid, String suid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Score WHERE Student_id=? AND Subject_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, sid); //gan tham so 1 la bien truyen vao
                ps.setString(2, suid);
                rs = ps.executeQuery();
                
                while (rs.next()){
                    StudentDAO sDAO = new StudentDAO();
                    Student s=sDAO.getStudentById(sid);
                    
                    SubjectDAO suDAO = new SubjectDAO();
                    Subject su=suDAO.getSubjectById(suid);
                    
                    String sc = rs.getString("Score");
                    String se = rs.getString("Semester");
                    String st = rs.getString("Status");
                    
                    Score si= new Score(s, su, sc, se, st);
                    
                    return si;
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
    
    public ArrayList<Score> getScoreByStudentId(String sid) throws NamingException, SQLException {
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        ResultSet rs = null;//doi tuong nhan ket qua
        
        String sql ="SELECT * FROM Score WHERE Student_id=?";
        
        ArrayList<Score> lst = new ArrayList<>();
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
                    
                    String sc = rs.getString("Score");
                    String se = rs.getString("Semester");
                    String st = rs.getString("Status");
                    
                    Score si= new Score(s, su, sc, se, st);
                    
                    lst.add(si);
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
    
    public boolean CreateScore(Score sc) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "INSERT INTO Score (Student_id, "
                + "Subject_id, Score, Semester, Status) "
                + "VALUES (?, ?, ?, ?, ?)";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, sc.getStudent().getId());
                ps.setString(2, sc.getSubject().getId());
                ps.setString(3, sc.getScore());
                ps.setString(4, sc.getSemester());
                ps.setString(5, sc.getStatus());
                
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
    
    public boolean deleteScore(String sid, String suid) throws NamingException, SQLException{
        Connection c = null; //doi tuong ket noi
        PreparedStatement ps = null; //doi tuong truy van
        
        String sql ="DELETE FROM Score WHERE Student_id=? AND Subject_id=?";
        
        try{
            c = DBConnect.makeConnection(); // tao doi tuong connection qua DBConnection
            
            if (c!=null){
                ps = c.prepareStatement(sql); // tao truy van
                ps.setString(1, sid);
                ps.setString(2, suid);
                
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
    
    public boolean UpdateScore(Score scd) throws NamingException, SQLException{
        Connection c= null;
        PreparedStatement ps = null;
        String sql= "UPDATE Score SET Score=?, Semester=?, Status=? WHERE Student_id=? AND Subject_id=?";
        try{
            c = DBConnect.makeConnection();
            
            if (c!=null){
                ps=c.prepareStatement(sql);
                
                ps.setString(1, scd.getScore());
                ps.setString(2, scd.getSemester());
                ps.setString(3, scd.getStatus());
                ps.setString(4, scd.getStudent().getId());
                ps.setString(5, scd.getSubject().getId());
                
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
