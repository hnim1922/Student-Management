/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassSubjectDAO;
import DAO.TeacherDAO;
import DTO.Teacher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class TeacherServlet extends HttpServlet {

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
            if (action == null){ //open the book list with add
                ArrayList<Teacher> TeacherList = new ArrayList<>();
                
                TeacherDAO dao = new TeacherDAO();
                TeacherList = dao.getAllTeacher();
                
                request.setAttribute("data", TeacherList);
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/teacherList.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("addteacherform")){
                Teacher t = new Teacher();
                
                request.setAttribute("tObject", t);
                request.setAttribute("msg", "add new Teacher");
                request.setAttribute("action", "Create_Teacher");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/teacherForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("updateteacherform")){
                String id = request.getParameter("tid");
                
                TeacherDAO tDAO = new TeacherDAO();
                
                Teacher t = tDAO.getTeacherById(id);
                
                request.setAttribute("tObject", t);
                request.setAttribute("msg", "update Teacher");
                request.setAttribute("action", "Update_Teacher");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/teacherForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deleteteacherconfirm")) { // xac nhan xoa
                String id = request.getParameter("tid");
                
                request.setAttribute("t", id);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/deleteteacherconfirm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deleteteacher")) { //xoa
                String id = request.getParameter("tid");
                
                ClassSubjectDAO csDAO = new ClassSubjectDAO();
                if (csDAO.SearchClassByTeacher(id)!=null){
                    request.setAttribute("ERROR", "This teacher has class");
                    RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/error.jsp");
                    rd.forward(request, response);
                }
                
                TeacherDAO dao = new TeacherDAO();
                
                dao.deleteTeacher(id);
                response.sendRedirect("TeacherServlet");
            } 
            
            else if (action.equals("Create_Teacher")) { //tao class trong db
                String id = request.getParameter("id");
                String ln = request.getParameter("lname");
                String fn = request.getParameter("fname");
                boolean valid = true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/teacher/error.jsp");
                rd.forward(request, response);
                }
                
                if (id.trim().isEmpty()){
                    request.setAttribute("errorID", "ID is empty");
                    valid=false;
                }
                if (ln.trim().isEmpty()){
                    request.setAttribute("errorLastName", "Last Name is empty");
                    valid=false;
                }
                if (fn.trim().isEmpty()){
                    request.setAttribute("errorFirstName", "First Name is empty");
                    valid=false;
                }
                TeacherDAO tDAO = new TeacherDAO();
                if (tDAO.getTeacherById(id)!=null){
                    request.setAttribute("errorID", "ID already existed");
                    valid=false;
                }
                if(valid){
                    Teacher t = new Teacher(id, ln, fn);

                    tDAO.CreateTeacher(t);

                    response.sendRedirect("TeacherServlet");
                }
                else {
                    request.getRequestDispatcher("TeacherServlet?action=addteacherform").forward(request, response);
                }
                
            }
            
            else if (action.equals("Update_Teacher")) { //update class trong db
                String id = request.getParameter("id");
                String ln = request.getParameter("lname");
                String fn = request.getParameter("fname");
                boolean valid = true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to update like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/error.jsp");
                rd.forward(request, response);
                }
                
                if (ln.trim().isEmpty()){
                    request.setAttribute("errorLastName", "Last Name is empty");
                    valid=false;
                }
                if (fn.trim().isEmpty()){
                    request.setAttribute("errorFirstName", "First Name is empty");
                    valid=false;
                }
                
                if (valid){
                    TeacherDAO tDAO = new TeacherDAO();
                
                    Teacher t = new Teacher(id, ln, fn);

                    tDAO.UpdateTeacher(t);

                    response.sendRedirect("TeacherServlet");
                }
                else{
                    request.getRequestDispatcher("TeacherServlet?action=updateteacherform&tid=" + id).forward(request, response);
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
