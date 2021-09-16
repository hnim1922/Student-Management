/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassDAO;
import DAO.ScoreDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DTO.Score;
import DTO.Class;
import DTO.Student;
import DTO.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ScoreServlet", urlPatterns = {"/ScoreServlet"})
public class ScoreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().getAttribute("name");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("name");
        request.setAttribute("name", username);
        try{
            if(action==null){ //open the book list with add
                ArrayList<Score> ScoreList = new ArrayList<>();
                ArrayList<Class> ClassList = new ArrayList<>();
                ScoreDAO dao = new ScoreDAO();
                ScoreList = dao.getAllScore();
                ClassDAO cDao = new ClassDAO();
                ClassList = cDao.getAllClass();
                request.setAttribute("ScoreData", ScoreList);
                request.setAttribute("ClassData", ClassList);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/scoreList.jsp");
                rd.forward(request, response);
            }
            else if(action.equals("ListScoreByClass")){
                String formClassname=request.getParameter("classToList");
                ArrayList<Score> ScoreList = new ArrayList<>();
                ArrayList<Class> ClassList = new ArrayList<>();
                ScoreDAO dao = new ScoreDAO();
                ScoreList = dao.getAllScore();
                boolean founded = false;
                if (formClassname!=""){
                    ArrayList<Score> ScoreListSearch = new ArrayList<>();
                    for (Score std: ScoreList){
                        if (formClassname.equals(std.getStudent().getClasses().getName())){
                            ScoreListSearch.add(std);
                            founded=true;
                        }
                    }
                 ClassDAO cDao = new ClassDAO();
                 ClassList = cDao.getAllClass();
                 request.setAttribute("chekedClass",formClassname);
                 request.setAttribute("ClassData",ClassList);
                 request.setAttribute("ScoreData",ScoreListSearch);
                }    
                 RequestDispatcher rd = request.getRequestDispatcher("Admin/score/scoreList.jsp");
                rd.forward(request, response);
            }       
            else if (action.equals("addscoreform")){
                Score sc = new Score();
                
                SubjectDAO sDAO = new SubjectDAO();
                ArrayList<Subject> sList = sDAO.getAllSubject();
                
                request.setAttribute("scObject", sc);
                request.setAttribute("sList", sList);
                request.setAttribute("msg", "add new Score");
                request.setAttribute("action", "Create_Score");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/scoreForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("updatescoreform")){
                String sid = request.getParameter("sid");
                String suid = request.getParameter("suid");
                
                ScoreDAO scDAO = new ScoreDAO();
                
                Score sc = scDAO.getScoreById(sid, suid);
                
                request.setAttribute("scObject", sc);
                request.setAttribute("msg", "update Score");
                request.setAttribute("action", "Update_Score");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/scoreForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletescoreconfirm")) { // xac nhan xoa
                String sid = request.getParameter("sid");
                String suid = request.getParameter("suid");
                
                request.setAttribute("s", sid);
                request.setAttribute("su", suid);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/deletescoreconfirm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletescore")) { //xoa
                String sid = request.getParameter("sid");
                String suid = request.getParameter("suid");
                
                ScoreDAO dao = new ScoreDAO();
                
                dao.deleteScore(sid, suid);
                response.sendRedirect("ScoreServlet");
            } 
            
            else if (action.equals("Create_Score")) { //tao class trong db
                String sid = request.getParameter("student_id");
                String suid = request.getParameter("subject_id");
                String sc = request.getParameter("score");
                String se = request.getParameter("semester");
                String st = request.getParameter("status");
                boolean valid=true;
                
                System.out.println(sid);
                System.out.println(suid);
                System.out.println(sc);
                System.out.println(se);
                System.out.println(st);
                
                if (sid==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/error.jsp");
                rd.forward(request, response);
                }
                
                if (sc.trim().isEmpty()){
                    request.setAttribute("errorScore", "Score is empty");
                    valid=false;
                }
                if (se.trim().isEmpty()){
                    request.setAttribute("errorSemester", "Semester is empty");
                    valid=false;
                }
                if (st.trim().isEmpty()){
                    request.setAttribute("errorStatus", "Status is empty");
                    valid=false;
                }
                
                StudentDAO sDAO = new StudentDAO();
                Student s = sDAO.getStudentById(sid);
                if (sid.trim().isEmpty()){
                    request.setAttribute("errorID", "ID is empty");
                    valid=false;
                }
                else if (s==null){
                    request.setAttribute("errorID", "ID does not exist");
                    valid=false;
                }
                SubjectDAO suDAO = new SubjectDAO();
                Subject su = suDAO.getSubjectById(suid);
                
                ScoreDAO scDAO=new ScoreDAO();
                if (scDAO.getScoreById(sid, suid)!=null){
                    request.setAttribute("errorID", "This ID already have this subject score");
                    valid=false;
                }
                if (valid){
                    Score scl=new Score(s, su, sc, se, st);

                    scDAO.CreateScore(scl);

                    response.sendRedirect("ScoreServlet");
                }
                else {
                    request.getRequestDispatcher("ScoreServlet?action=addscoreform").forward(request, response);
                }
            }
            
            else if (action.equals("Update_Score")) { //update class trong db
                String sid = request.getParameter("student_id");
                String suid = request.getParameter("subject_id");
                String sc = request.getParameter("score");
                String se = request.getParameter("semester");
                String st = request.getParameter("status");
                boolean valid=true;
                
                if (sid==null){
                request.setAttribute("ERROR", "You not suppose to update like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/score/error.jsp");
                rd.forward(request, response);
                }
                
                if (sc.trim().isEmpty()){
                    request.setAttribute("errorScore", "Score is empty");
                    valid=false;
                }
                if (se.trim().isEmpty()){
                    request.setAttribute("errorSemester", "Semester is empty");
                    valid=false;
                }
                if (st.trim().isEmpty()){
                    request.setAttribute("errorStatus", "Status is empty");
                    valid=false;
                }
                if(valid){
                    StudentDAO sDAO = new StudentDAO();
                    Student s = sDAO.getStudentById(sid);

                    SubjectDAO suDAO = new SubjectDAO();
                    Subject su = suDAO.getSubjectById(suid);

                    ScoreDAO scDAO=new ScoreDAO();
                    Score scl=new Score(s, su, sc, se, st);

                    scDAO.UpdateScore(scl);

                    response.sendRedirect("ScoreServlet");
                } else {
                    request.getRequestDispatcher("ScoreServlet?action=updatescoreform&sid=" + sid + "&suid" + suid).forward(request, response);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
